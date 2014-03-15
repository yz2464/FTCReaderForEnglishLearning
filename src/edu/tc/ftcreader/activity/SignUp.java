package edu.tc.ftcreader.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import edu.tc.ftcreader.R;

public class SignUp extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		
		// Set password font style to be default
		ViewUtil.setInputTypeFace((EditText)findViewById(R.id.input_signup_pwd));		
	}

	public void startNewsBriefListActivity(View view) {
		Intent intent = new Intent(this, NewsBriefList.class);
		startActivity(intent);
	}
}
