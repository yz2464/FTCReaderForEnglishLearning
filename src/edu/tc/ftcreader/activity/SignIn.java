package edu.tc.ftcreader.activity;

import edu.tc.ftcreader.R;
import android.os.Bundle;
import android.app.Activity;
import android.widget.EditText;

public class SignIn extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signin);
		
		// Set password font style to be default
		ViewUtil.setInputTypeFace((EditText)findViewById(R.id.input_signin_pwd));
	}
}
