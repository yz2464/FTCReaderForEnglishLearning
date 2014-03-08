package edu.tc.ftcreader.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import edu.tc.ftcreader.R;

public class SignUp extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		
		// Set password font style to be default
		ViewUtil.setInputTypeFace((EditText)findViewById(R.id.input_signup_pwd));		
	}

}
