package edu.tc.ftcreader.entity;

/**
 * NewsBrief object containing news headline, to be displayed in the NewsBriefList activity
 * @author Yuan
 *
 */
public class NewsBrief {
	private String id;
	private String headline;
	
	public NewsBrief(String id, String headline) {
		this.id = id;
		this.headline = headline;
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
}
