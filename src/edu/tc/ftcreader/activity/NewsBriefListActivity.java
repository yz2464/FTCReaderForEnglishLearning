package edu.tc.ftcreader.activity;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.SimpleAdapter;
import edu.tc.ftcreader.R;

public class NewsBriefListActivity extends Activity {
	private SimpleAdapter simpleAdapter;
	private ArrayList<HashMap<String, String>> newsBriefList;
	
	final static String KEY_HEADLINE = "headline";
	final static String KEY_ID = "id";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_brief_list);
		
		newsBriefList = getNewsBriefList();
		
		displayNewsBriefList();
	}
		
	private ArrayList<HashMap<String, String>> getNewsBriefList() {		
		ArrayList<HashMap<String, String>> newsBriefArrList = new ArrayList<HashMap<String, String>>();
		
		for (int i = 0; i < 10; i ++) {
			HashMap<String, String> newsBrief = new HashMap<String, String>();
			newsBrief.put(KEY_ID, Integer.toString(i));
			newsBrief.put(KEY_HEADLINE, "Random headline " + i);		
			newsBriefArrList.add(newsBrief);
		}
		
		return newsBriefArrList;
	}
	
	/**
	 * Display new briefs in a list view
	 */
	private void displayNewsBriefList() {
		// Initialize adaptor to inflate news briefs
		simpleAdapter = new SimpleAdapter(
				this, newsBriefList, R.layout.news_brief, 
				new String[] { KEY_ID, KEY_HEADLINE }, 
					new int[] { R.id.new_brief_id, R.id.new_brief_headline });
		
		// Inflate list view for news briefs
		ListView newsBriefListView = (ListView) findViewById(R.id.news_brief_list_view);
		newsBriefListView.setAdapter(simpleAdapter);
		
		// Set on item click listener
		newsBriefListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// Get the id of the selected news article
				String articleId = ((TextView) view.findViewById(R.id.new_brief_id)).getText().toString();
				
				// Start NewsArticle activity and display the selected new article
				/*
				Intent intent = new Intent(this, NewsArticle.class);
				intent.putExtra(KEY_ID, articleId);
				startActivity(intent);
				*/
			}

        });
	}

}
