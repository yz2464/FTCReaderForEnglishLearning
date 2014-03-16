package edu.tc.ftcreader.entity;

import edu.tc.ftcreader.connectivity.GlobalVariable;

/**
 * A class representing HTTP response of a piece of news
 * @author Yuan
 *
 */
public class NewsResponse {
	private int status; // status of HTTP request
	private String id; // id of the news requested
	private News news; // the piece of news requested
	
	public NewsResponse() {
		this.status = GlobalVariable.DEFAULT_INT;
		this.id = GlobalVariable.DEFAULT_STRING;
		this.news = new News();
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public News getNews() {
		return news;
	}
	
	public void setNews(News news) {
		this.news = news;
	}
	
	
}
