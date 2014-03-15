package edu.tc.ftcreader.connectivity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.URLEncodedUtils;

import android.util.Log;

/**
 * Class that handles HTPP calls
 * @author Yuan
 *
 */
public class HttpHandler {	
	public final static String GET = "GET";
	public final static String POST = "POST";
	
	/**
	 * Post to destination URL
	 * @param urlStr		URL to make request
	 * @param params		HTTP request parameters
	 * @return				If the post request is successful
	 */
	public boolean makePostHttpCall(String urlStr, List<NameValuePair> params) {
		URL url;
		HttpURLConnection connection;		
		boolean ifRequestSuccessful = false;
		
		try {
			url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setChunkedStreamingMode(0);
			connection.setRequestMethod(POST);
			
			OutputStream os = new BufferedOutputStream(connection.getOutputStream());				
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params); 
			formEntity.writeTo(os);			
			os.close();			
			outputRequestStatus(connection.getResponseCode());			
			ifRequestSuccessful = connection.getResponseCode() == HttpURLConnection.HTTP_OK ? true : false;
			connection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		}
		
		return ifRequestSuccessful;
	}
	
	/**
	 * Output the status of an HTTP request
	 * @param 	responseCode		Response code of HTTP request 
	 */
	private void outputRequestStatus(int responseCode) {
		if (responseCode != HttpURLConnection.HTTP_OK) {
			Log.e("HttpHandler: ", "HTTP request failed.");				
		}
		else {
			Log.d("HttpHandler: ", "HTTP request successful.");
		}
	}
	
	/**
	 * Get data from destination URL
	 * @param urlStr		URL to make request
	 * @param params		HTTP request parameters
	 * @return				Data received from HTTP Get call
	 */
	public String makeGetHttpCall(String urlStr, List<NameValuePair> params) {
		URL url;
		HttpURLConnection connection;
		String response = "";
		String paramStr = URLEncodedUtils.format(params, "utf-8");
		
		try {							
			url = new URL(urlStr + "?" + paramStr);
			Log.d("URL: ", url.toString());
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setChunkedStreamingMode(0);
			connection.setRequestMethod(GET);
			
			InputStream is = new BufferedInputStream(connection.getInputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
			response = getResponseString(reader);			
			is.close();
			outputRequestStatus(connection.getResponseCode());	
			connection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * Return response string from reader
	 * @param reader		BufferedReader
	 * @return				Response in string
	 */
	private String getResponseString(BufferedReader reader) {
		String responseStr = "";
		StringBuilder builder = new StringBuilder();		
		try {
			String line = reader.readLine();
			while (line != null) {
			    builder.append(line).append("\n");
			    line = reader.readLine();
			}		
			responseStr = builder.toString();	
		} catch (IOException e) { 
			e.printStackTrace();
		}
		
		return responseStr;
	}
}
