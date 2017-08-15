package com.example.tinylove.Activity;

import com.example.tinylove.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class AboutUsDialog extends Activity {
	
	public static String content="";
	
	private TextView textAboutUs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_about_us);
		
		textAboutUs=(TextView)findViewById(R.id.about_us_content);
		
		textAboutUs.setText(content);
		
	}


}
