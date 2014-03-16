package edu.tc.ftcreader.activity;

import edu.tc.ftcreader.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class NewsPanel extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_panel);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.news_panel, menu);
		return true;
	}

}
