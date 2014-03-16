package edu.tc.ftcreader.entity;

import java.util.ArrayList;
import java.util.List;

import edu.tc.ftcreader.connectivity.GlobalVariable;

/**
 * A class representing HTTP response of a list of news briefs
 * @author Yuan
 *
 */
public class NewsBriefListResponse {	
	private int status; // status of HTTP request
	private int count; // the number of news returned 
	private int total; // the total number of news 
	private List<NewsBrief> newsBriefList; // a list of news briefs
	
	public NewsBriefListResponse() {
		this.status = GlobalVariable.DEFAULT_INT;
		this.count = GlobalVariable.DEFAULT_INT;
		this.total = GlobalVariable.DEFAULT_INT;
		this.newsBriefList = new ArrayList<NewsBrief>();
	}
	
	public NewsBriefListResponse(int status, int count, int total,
			List<NewsBrief> newsBriefList) {	
		this.status = status;
		this.count = count;
		this.total = total;
		this.newsBriefList = newsBriefList;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<NewsBrief> getNewsBriefList() {
		return newsBriefList;
	}	

	public void setNewsBriefList(List<NewsBrief> newsBriefList) {
		this.newsBriefList = newsBriefList;
	}
	
}
