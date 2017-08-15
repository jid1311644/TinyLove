package com.example.tinylove.Activity;

import com.example.tinylove.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class PromptDialog extends Activity {
	
	public static String title="±êÌâ";
	public static String content="ÄÚÈÝ";
	
	private TextView textTitle;
	private TextView textContent;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_prompt);

		textTitle=(TextView)findViewById(R.id.dialog_title);
		textContent=(TextView)findViewById(R.id.dialog_content);
		
		textTitle.setText(title);
		textContent.setText(content);
		
	}

}
