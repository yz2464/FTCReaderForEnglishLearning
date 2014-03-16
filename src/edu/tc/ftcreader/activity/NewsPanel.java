package edu.tc.ftcreader.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import edu.tc.ftcreader.R;
import edu.tc.ftcreader.connectivity.GlobalVariable;
import edu.tc.ftcreader.connectivity.NewsLoader;
import edu.tc.ftcreader.entity.News;

public class NewsPanel extends FragmentActivity implements LoaderManager.LoaderCallbacks<News> {
	long before;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		before = System.currentTimeMillis();
		setContentView(R.layout.activity_news_panel);
		
		// Get the id of the news selected by the user
		Bundle bundle = getIntent().getExtras();		
		
		// Initiate loader to load news
		getSupportLoaderManager().initLoader(0, bundle, this).forceLoad();;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.news_panel, menu);
		return true;
	}

	@Override
	public Loader<News> onCreateLoader(int id, Bundle args) {
		// Get id of the selected news
		String newsId = args.getString(GlobalVariable.KEY_ID);
		// Create news loader with selected news id
		NewsLoader loader = new NewsLoader(this, newsId);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<News> loader, News news) {
		// Build news view when finished loading news		
		displayNews(news);
	}

	@Override
	public void onLoaderReset(Loader<News> arg0) {
		// Clear news panel		
		((TextView) findViewById(R.id.news_id)).setText(GlobalVariable.DEFAULT_STRING);
		((TextView) findViewById(R.id.news_headline)).setText(GlobalVariable.DEFAULT_STRING);
		((TextView) findViewById(R.id.news_date_time)).setText(GlobalVariable.DEFAULT_STRING);
		((TextView) findViewById(R.id.news_text)).setText(GlobalVariable.DEFAULT_STRING);
	}
	
	/**
	 * Display selected news in news panel
	 * @param news
	 */
	private void displayNews(News news) {
		// Set news id, headline, date and text
		TextView newsId = (TextView) findViewById(R.id.news_id);
		newsId.setText(news.getNewsBrief().getId());
		
		TextView newsHeadline = (TextView) findViewById(R.id.news_headline);
		newsHeadline.setText(news.getNewsBrief().getHeadline());
		
		TextView newsDateTime = (TextView) findViewById(R.id.news_date_time);
		newsDateTime.setText(news.getNewsBrief().getDateTimeString());
		
		TextView newsText = (TextView) findViewById(R.id.news_text);
		newsText.setText(news.getTextString());
		long after = System.currentTimeMillis();
		long result = after - before;
		Log.d("Total time: ", String.valueOf(result));
	}
}
