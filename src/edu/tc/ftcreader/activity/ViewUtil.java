package edu.tc.ftcreader.activity;

import android.graphics.Typeface;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;


public class ViewUtil {
	public static void setInputTypeFace(EditText editText) {
		editText.setTypeface(Typeface.DEFAULT);
		editText.setTransformationMethod(new PasswordTransformationMethod());
	}
}
