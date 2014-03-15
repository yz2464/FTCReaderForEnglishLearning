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
import edu.tc.ftcreader.entity.NewsBrief;
import edu.tc.ftcreader.entity.NewsBriefListResponse;

public class JsonParser {
	public final static String KEY_RESULTS = "results";
	public final static String KEY_STATUS = "status";
	public final static String KEY_COUNT = "count";
	public final static String KEY_TOTAL = "total";
	public final static String KEY_ID = "id";
	public final static String KEY_HEADLINE = "headline";	
	public final static String KEY_DATE = "article_date";
	
	public static NewsBriefListResponse parseNewsBriefList(String newsBriefResponseStr) {
		NewsBriefListResponse newsBriefListResponse = new NewsBriefListResponse();		
		
		if (newsBriefResponseStr != null) {
			try {				
				// Create news brief response object from a string  
				JSONObject newsBriefResponseObj = new JSONObject(newsBriefResponseStr);
				
				// Get news brief info out of the JSON obejct
				newsBriefListResponse.setStatus(Integer.parseInt(newsBriefResponseObj.getString(KEY_STATUS)));
				newsBriefListResponse.setCount(Integer.parseInt(newsBriefResponseObj.getString(KEY_COUNT)));
				newsBriefListResponse.setTotal(Integer.parseInt(newsBriefResponseObj.getString(KEY_TOTAL)));

				// Get an array of news briefs
				JSONArray newsBriefs = newsBriefResponseObj.getJSONArray(KEY_RESULTS);
				
				// Loop through the array of news briefs 
				List<NewsBrief> newsBriefList = new ArrayList<NewsBrief>();
				for (int i = 0; i < newsBriefs.length(); i ++) {					
					JSONObject newsBirefObj = newsBriefs.getJSONObject(i);
					
					// Get news brief info out of the JSON obejct
					String id = newsBirefObj.getString(KEY_ID);
					String headline = newsBirefObj.getString(KEY_HEADLINE);
					String dateTimeStr = newsBirefObj.getString(KEY_DATE);
					
					// Clean up the date time string "2014-01-02T00:00:00Z" 
					int tStartIndex = 10, tEndIndex = 11; // index range of "T"
					StringBuilder dtStrBuilder = new StringBuilder(dateTimeStr);
					dtStrBuilder.replace(tStartIndex, tEndIndex, " "); // replace "T" with space
					String dateTimeStrResult = dtStrBuilder.substring(0, dtStrBuilder.length() - 1); // remove "Z" at the end
					
					// Convert the date time string to Date object
					Date dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).parse(dateTimeStrResult);					
					
					// Create a NewsBrief object with returned id, headline and date
					NewsBrief newsBrief = new NewsBrief(id, headline, dateTime);
					
					// Add the NewsBrief object into newsBriefList
					newsBriefList.add(newsBrief);
				}
				
				// Set NewsBriefList object of newsBriefListResponse 
				newsBriefListResponse.setNewsBriefList(newsBriefList);
				
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (ParseException e) { 
				e.printStackTrace();
			}
		}
		else {
			Log.e("HttpHandler", "Couldn't get any data from the url.");
		}
				
		return newsBriefListResponse;
	}
}

