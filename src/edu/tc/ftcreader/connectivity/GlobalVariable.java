package edu.tc.ftcreader.connectivity;

/**
 * A class containing variables that are used globally
 * @author Yuan
 *
 */
public class GlobalVariable {
	// FT server request URL
	public final static String FT_URL = "https://api.pearson.com/v2/ft/articles";
	
	// API key to access FT server
	public final static String VALUE_APIKEY = "w7q8yrNGEZD25x4Ty8Ge8oewGSpTjuHP";
	
	// Variables used in JSON response passed back by FT server
	public final static String KEY_APIKEY = "apikey";
	public final static String KEY_COUNTRY = "country";
	public final static String KEY_ARTICLE_DATE = "article_date";
	public final static String KEY_RESULTS = "results"; // for requesting news brief list
	public final static String KEY_RESULT = "result"; // for requesting news
	public final static String KEY_STATUS = "status";
	public final static String KEY_COUNT = "count";
	public final static String KEY_TOTAL = "total";
	public final static String KEY_ID = "id";
	public final static String KEY_HEADLINE = "headline";	
	public final static String KEY_DATE = "article_date";
	public final static String KEY_TEXT = "text";			
	
	// Default values
	public final static int DEFAULT_INT = -1;
	public final static String DEFAULT_STRING = "";
	

}
