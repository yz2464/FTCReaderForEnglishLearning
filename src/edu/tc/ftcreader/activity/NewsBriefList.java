package edu.tc.ftcreader.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import edu.tc.ftcreader.R;
import edu.tc.ftcreader.connectivity.NewsBriefListLoader;
import edu.tc.ftcreader.connectivity.JsonParser;
import edu.tc.ftcreader.entity.NewsBrief;

public class NewsBriefList extends FragmentActivity implements LoaderManager.LoaderCallbacks<List<NewsBrief>> {						
	long before;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		before = System.currentTimeMillis();
		setContentView(R.layout.activity_news_brief_list);
				
		// Initiate loader to load news brief list
		getSupportLoaderManager().initLoader(0, null, this).forceLoad();												
	}
		
	@Override
	public Loader<List<NewsBrief>> onCreateLoader(int id, Bundle args) {
		NewsBriefListLoader loader = new NewsBriefListLoader(this);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<List<NewsBrief>> loader, List<NewsBrief> newsBriefList) {
		// Build list view when finished loading
		displayNewsBriefList(getNewsBriefsInMapList(newsBriefList));		
	}

	@Override
	public void onLoaderReset(Loader<List<NewsBrief>> loader) {
		// Clear list view
		ListView newsBriefListView = (ListView) findViewById(R.id.news_brief_list_view);
		newsBriefListView.setAdapter(null);		
	}	
	
	/**
	 * Convert a list of NewsBrief object into a list of maps
	 * @param newsBriefList
	 * @return
	 */
	private List<Map<String, String>> getNewsBriefsInMapList(List<NewsBrief> newsBriefList) {
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		
		// Add each news brief in map format to mapList
		for (int i = 0; i < newsBriefList.size(); i ++) {
			NewsBrief newsBrief = newsBriefList.get(i);
			mapList.add(newsBrief.getNewsBriefInMap());			
		}			
		
		return mapList;
	}
	
	/**
	 * Display new briefs in a list view
	 */
	private void displayNewsBriefList(List<Map<String, String>> newsBriefList) {			
		// Initialize adaptor to inflate news briefs
		SimpleAdapter simpleAdapter = new SimpleAdapter(
				this, newsBriefList, R.layout.news_brief, 
				new String[] { JsonParser.KEY_ID, JsonParser.KEY_HEADLINE }, 
				new int[] { R.id.news_brief_id, R.id.news_brief_headline });
		
		// Inflate list view for news briefs
		ListView newsBriefListView = (ListView) findViewById(R.id.news_brief_list_view);
		newsBriefListView.setAdapter(simpleAdapter);
		long after = System.currentTimeMillis();
		long result = after - before;
		Log.d("Total time: ", String.valueOf(result));
		
		// Set on item click listener
		newsBriefListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// Get the id of the selected news
				String newsId = ((TextView) view.findViewById(R.id.news_brief_id)).getText().toString();
				
				// Start News activity and display the selected news				
				Intent intent = new Intent(NewsBriefList.this, NewsPanel.class);
				intent.putExtra(JsonParser.KEY_ID, newsId);
				startActivity(intent);				
			}
        });
	}
}
