package edu.tc.ftcreader.activity;

import android.graphics.Typeface;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;


public class ViewUtil {
	/**
	 * Set type face for an EditText view 
	 * @param editText		The EditText view to be set
	 */
	public static void setInputTypeFace(EditText editText) {
		editText.setTypeface(Typeface.DEFAULT);
		editText.setTransformationMethod(new PasswordTransformationMethod());
	}
}
