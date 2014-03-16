package edu.tc.ftcreader.connectivity;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import edu.tc.ftcreader.entity.NewsBrief;
import edu.tc.ftcreader.entity.NewsBriefListResponse;

/**
 * Loader to load news brief list asynchronously 
 * @author Yuan
 *
 */
public class NewsBriefListLoader extends AsyncTaskLoader<List<NewsBrief>>  {	
	final static String VALUE_COUNTRY = "china";
	final static String VALUE_ARTICLE_DATE = "[2014-01-15 TO 2014-01-18]";
	
	public NewsBriefListLoader(Context context) {
		super(context);
	}	

	@Override
	public List<NewsBrief> loadInBackground() {
		List<NewsBrief> newsBriefList = new ArrayList<NewsBrief>();
		
		// Sample request URL for news brief list
		// https://api.pearson.com/v2/ft/articles?country=china&article_date=%5B2014-01-01+TO+2014-01-15%5D&apikey=w7q8yrNGEZD25x4Ty8Ge8oewGSpTjuHP
		
		// Set request parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair(GlobalVariable.KEY_APIKEY, GlobalVariable.VALUE_APIKEY));
		params.add(new BasicNameValuePair(GlobalVariable.KEY_COUNTRY, VALUE_COUNTRY));
		params.add(new BasicNameValuePair(GlobalVariable.KEY_ARTICLE_DATE, VALUE_ARTICLE_DATE));
		
		long before = System.currentTimeMillis();
		// Make get request
		HttpHandler httpHandler = new HttpHandler();		
		String jsonResponseStr = httpHandler.makeGetHttpCall(GlobalVariable.FT_URL, params);
		long after = System.currentTimeMillis();
		long result = after - before;
		Log.d("HTTP request time: ", String.valueOf(result));
		
		// Parse response from JSON string to a NewsBriefListResponse object
		NewsBriefListResponse response = JsonParser.parseNewsBriefList(jsonResponseStr);
		if (response.getStatus() != HttpURLConnection.HTTP_OK) {
			// TODO tell user that update failed, try later
		}
		else {
			// Get a list of news briefs
			newsBriefList = response.getNewsBriefList();
		}				
				
		return newsBriefList;
	}
}
