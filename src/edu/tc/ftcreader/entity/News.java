package edu.tc.ftcreader.entity;

import java.util.Date;

/**
 * Article class containing all information of a piece of news
 * @author Yuan
 * 
 */
public class News {	
	private NewsBrief newsBrief; // a newsBrief object
	private String[] text; // text of the news	
	
	public News(NewsBrief newsBrief, String[] text) {		
		this.newsBrief = newsBrief;
		this.text = text;
	}
	
	public News(String id, String headline, Date dateTime, String[] text) {
		this.newsBrief = new NewsBrief(id, headline, dateTime);
		this.text = text;
	}

	public NewsBrief getNewsBrief() {
		return newsBrief;
	}
	
	public void setNewsBrief(NewsBrief newsBrief) {
		this.newsBrief = newsBrief;
	}
	
	public String[] getText() {
		return text;
	}
	
	public void setText(String[] text) {
		this.text = text;
	}
	
}
