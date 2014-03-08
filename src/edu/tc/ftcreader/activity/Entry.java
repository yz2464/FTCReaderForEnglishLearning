package edu.tc.ftcreader.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import edu.tc.ftcreader.R;

public class Entry extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.entry);		
	}
	
	/**
	 * Start sign up activity when the registration button is clicked
	 * @param view		A reference to the registration button
	 */
	public void startSignUpActivity(View view) {
		Intent intent = new Intent(this, SignUp.class);	    	    
	    startActivity(intent);		
	}
	
	/**
	 * Start sign in activity when the login button is clicked
	 * @param view		A reference to the login button
	 */
	public void startSignInActivity(View view) {
		Intent intent = new Intent(this, SignIn.class);
		startActivity(intent);
	}

}
