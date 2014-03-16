package edu.tc.ftcreader.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Article class containing all information of a piece of news
 * @author Yuan
 * 
 */
public class News {	
	private NewsBrief newsBrief; // a newsBrief object
	private List<String> text; // text of the news	
	
	public News() {
		this.newsBrief = new NewsBrief();
		this.text = new ArrayList<String>();
	}
	
	public News(NewsBrief newsBrief, List<String> text) {		
		this.newsBrief = newsBrief;
		this.text = text;
	}
	
	public News(String id, String headline, Date dateTime, List<String> text) {
		this.newsBrief = new NewsBrief(id, headline, dateTime);
		this.text = text;
	}

	public NewsBrief getNewsBrief() {
		return newsBrief;
	}
	
	public void setNewsBrief(NewsBrief newsBrief) {
		this.newsBrief = newsBrief;
	}
	
	public List<String> getText() {
		return text;
	}
	
	public String getTextString() {
		StringBuilder sb = new StringBuilder();
		String lineBreak = "\n\n";
		for (int i = 0; i < text.size(); i ++) {
			sb.append(text.get(i)).append(lineBreak);
		}
		return sb.toString();
	}
	
	public void setText(List<String> text) {
		this.text = text;
	}	
}
