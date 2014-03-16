package edu.tc.ftcreader.connectivity;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import edu.tc.ftcreader.entity.News;
import edu.tc.ftcreader.entity.NewsResponse;

/**
 * Loader to load news asynchronously
 * @author Yuan
 *
 */
public class NewsLoader extends AsyncTaskLoader<News> {
	private String newsId; // id of the news selected by the user
	
	public NewsLoader(Context context, String newsId) {
		super(context);		
		this.newsId = newsId;
	}

	@Override
	public News loadInBackground() {
		News news = new News();
		
		// Sample request URL for news brief list
		// https://api.pearson.com/v2/ft/articles/cqDZzf1wN8?apikey=w7q8yrNGEZD25x4Ty8Ge8oewGSpTjuHP
		
		// Set request parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair(GlobalVariable.KEY_APIKEY, GlobalVariable.VALUE_APIKEY));
		
		long before = System.currentTimeMillis();
		// Make get request
		HttpHandler httpHandler = new HttpHandler();		
		String urlStr = GlobalVariable.FT_URL + "/" + newsId;
		String jsonResponseStr = httpHandler.makeGetHttpCall(urlStr, params);
		long after = System.currentTimeMillis();
		long result = after - before;
		Log.d("HTTP request time: ", String.valueOf(result));
		
		// Parse response from JSON string to a News object
		NewsResponse response = JsonParser.parseNews(jsonResponseStr);
		if (response.getStatus() != HttpURLConnection.HTTP_OK) {
			// TODO tell user that update failed, try later
		}
		else {
			// Get news
			news = response.getNews();
		}	
				
		return news;
	}	
}
