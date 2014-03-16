package edu.tc.ftcreader.connectivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import edu.tc.ftcreader.entity.News;
import edu.tc.ftcreader.entity.NewsBrief;
import edu.tc.ftcreader.entity.NewsBriefListResponse;
import edu.tc.ftcreader.entity.NewsResponse;

public class JsonParser {	
	public static NewsBriefListResponse parseNewsBriefList(String newsBriefResponseStr) {
		NewsBriefListResponse newsBriefListResponse = new NewsBriefListResponse();		
		
		if (newsBriefResponseStr != null) {
			try {				
				// Create news brief response object from a string  
				JSONObject newsBriefResponseObj = new JSONObject(newsBriefResponseStr);
				
				// Get news brief info out of the JSON obejct
				newsBriefListResponse.setStatus(Integer.parseInt(newsBriefResponseObj.getString(GlobalVariable.KEY_STATUS)));
				newsBriefListResponse.setCount(Integer.parseInt(newsBriefResponseObj.getString(GlobalVariable.KEY_COUNT)));
				newsBriefListResponse.setTotal(Integer.parseInt(newsBriefResponseObj.getString(GlobalVariable.KEY_TOTAL)));

				// Get an array of news briefs
				JSONArray newsBriefs = newsBriefResponseObj.getJSONArray(GlobalVariable.KEY_RESULTS);
				
				// Loop through the array of news briefs 
				List<NewsBrief> newsBriefList = new ArrayList<NewsBrief>();
				for (int i = 0; i < newsBriefs.length(); i ++) {					
					JSONObject newsBirefObj = newsBriefs.getJSONObject(i);
					
					// Get news brief info out of the JSON obejct
					String id = newsBirefObj.getString(GlobalVariable.KEY_ID);
					String headline = newsBirefObj.getString(GlobalVariable.KEY_HEADLINE);
					String dateTimeStr = newsBirefObj.getString(GlobalVariable.KEY_DATE);								
					
					// Convert the date time string to Date object
					Date dateTime = getDateFromString(dateTimeStr); 											
					
					// Create a NewsBrief object with returned id, headline and date
					NewsBrief newsBrief = new NewsBrief(id, headline, dateTime);
					
					// Add the NewsBrief object into newsBriefList
					newsBriefList.add(newsBrief);
				}
				
				// Set NewsBriefList object of newsBriefListResponse 
				newsBriefListResponse.setNewsBriefList(newsBriefList);
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		else {
			Log.e("HttpHandler", "Couldn't get any data from the url.");
		}
				
		return newsBriefListResponse;
	}
	
	public static NewsResponse parseNews(String newsResponseStr) {
		NewsResponse newsResponse = new NewsResponse();		
		
		if (newsResponseStr != null) {
			try {				
				// Create news brief response object from a string  
				JSONObject newsResponseObj = new JSONObject(newsResponseStr);
				
				// Set news response with info returned in JSON object
				newsResponse.setStatus(Integer.parseInt(newsResponseObj.getString(GlobalVariable.KEY_STATUS)));
				newsResponse.setId(newsResponseObj.getString(GlobalVariable.KEY_ID));				

				// Get news info object
				JSONObject newsInfo = newsResponseObj.getJSONObject(GlobalVariable.KEY_RESULT);
				String id = newsInfo.getString(GlobalVariable.KEY_ID);
				String headline = newsInfo.getString(GlobalVariable.KEY_HEADLINE);
				String dateTimeStr = newsInfo.getString(GlobalVariable.KEY_DATE);
				JSONArray textArr = newsInfo.getJSONArray(GlobalVariable.KEY_TEXT);
				
				// Convert the date time string to Date object
				Date dateTime = getDateFromString(dateTimeStr); 											
				
				// Create a NewsBrief object with returned id, headline and date
				NewsBrief newsBrief = new NewsBrief(id, headline, dateTime);

				// Loop through the array of news text				
				List<String> text = new ArrayList<String>();
				for (int i = 0; i < textArr.length(); i ++) {
					text.add(textArr.getString(i));
				}
				
				// Create a News object with returned info
				News news = new News(newsBrief, text);
				
				// Set news response with News object 
				newsResponse.setNews(news);
				
			} catch (JSONException e) {
				e.printStackTrace();
			} 
		}
		else {
			Log.e("HttpHandler", "Couldn't get any data from the url.");
		}
				
		return newsResponse;		
	}
	
	private static Date getDateFromString(String dateTimeStr) {
		Date dateTime = new Date();
		
		// Clean up the date time string "2014-01-02T00:00:00Z" 
		int tStartIndex = 10, tEndIndex = 11; // index range of "T"
		StringBuilder dtStrBuilder = new StringBuilder(dateTimeStr);
		dtStrBuilder.replace(tStartIndex, tEndIndex, " "); // replace "T" with space
		String dateTimeStrResult = dtStrBuilder.substring(0, dtStrBuilder.length() - 1); // remove "Z" at the end
		
		try {
			dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).parse(dateTimeStrResult);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return dateTime;
	}
}

