package edu.tc.ftcreader.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import edu.tc.ftcreader.connectivity.GlobalVariable;

/**
 * A class containing brief news information
 * @author Yuan
 *
 */
public class NewsBrief {		
	private String id; // news id
	private String headline; // news headline
	private Date dateTime; // news date
		
	public NewsBrief() {
		this.id = GlobalVariable.DEFAULT_STRING;
		this.headline = GlobalVariable.DEFAULT_STRING;
		this.dateTime = new Date();		
	}
	
	public NewsBrief(String id, String headline, Date dateTime) {		
		this.id = id;
		this.headline = headline;
		this.dateTime = dateTime;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getHeadline() {
		return headline;
	}
	
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	
	public Date getDateTime() {
		return dateTime;
	}
	
	public String getDateTimeString() {
		return dateTime.toString();
	}
	
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}	
	
	public Map<String, String> getNewsBriefInMap() {		
		Map<String, String> map = new HashMap<String, String>();
		map.put(GlobalVariable.KEY_ID, id);
		map.put(GlobalVariable.KEY_HEADLINE, headline);
		map.put(GlobalVariable.KEY_DATE, dateTime.toString());
		
		return map;
	}
}
