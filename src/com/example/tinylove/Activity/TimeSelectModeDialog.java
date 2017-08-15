package com.example.tinylove.Activity;

import com.example.tinylove.R;
import com.example.tinylove.View.PictureView.TransitionEffect;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class TimeSelectModeDialog extends Activity implements OnClickListener {
	
	private TextView[] textMode=new TextView[12];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_time_select_mode);

		textMode[0]=(TextView)findViewById(R.id.mode_0);
		textMode[1]=(TextView)findViewById(R.id.mode_1);
		textMode[2]=(TextView)findViewById(R.id.mode_2);
		textMode[3]=(TextView)findViewById(R.id.mode_3);
		textMode[4]=(TextView)findViewById(R.id.mode_4);
		textMode[5]=(TextView)findViewById(R.id.mode_5);
		textMode[6]=(TextView)findViewById(R.id.mode_6);
		textMode[7]=(TextView)findViewById(R.id.mode_7);
		textMode[8]=(TextView)findViewById(R.id.mode_8);
		textMode[9]=(TextView)findViewById(R.id.mode_9);
		textMode[10]=(TextView)findViewById(R.id.mode_10);
		textMode[11]=(TextView)findViewById(R.id.mode_11);

		textMode[0].setOnClickListener(this);
		textMode[1].setOnClickListener(this);
		textMode[2].setOnClickListener(this);
		textMode[3].setOnClickListener(this);
		textMode[4].setOnClickListener(this);
		textMode[5].setOnClickListener(this);
		textMode[6].setOnClickListener(this);
		textMode[7].setOnClickListener(this);
		textMode[8].setOnClickListener(this);
		textMode[9].setOnClickListener(this);
		textMode[10].setOnClickListener(this);
		textMode[11].setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.mode_0:
			TimeFragment.effect=TransitionEffect.Standard;
			finish();
			break;
		case R.id.mode_1:
			TimeFragment.effect=TransitionEffect.Tablet;
			finish();
			break;
		case R.id.mode_2:
			TimeFragment.effect=TransitionEffect.CubeIn;
			finish();
			break;
		case R.id.mode_3:
			TimeFragment.effect=TransitionEffect.CubeOut;
			finish();
			break;
		case R.id.mode_4:
			TimeFragment.effect=TransitionEffect.FlipVertical;
			finish();
			break;
		case R.id.mode_5:
			TimeFragment.effect=TransitionEffect.FlipHorizontal;
			finish();
			break;
		case R.id.mode_6:
			TimeFragment.effect=TransitionEffect.Stack;
			finish();
			break;
		case R.id.mode_7:
			TimeFragment.effect=TransitionEffect.ZoomIn;
			finish();
			break;
		case R.id.mode_8:
			TimeFragment.effect=TransitionEffect.ZoomOut;
			finish();
			break;
		case R.id.mode_9:
			TimeFragment.effect=TransitionEffect.RotateUp;
			finish();
			break;
		case R.id.mode_10:
			TimeFragment.effect=TransitionEffect.RotateDown;
			finish();
			break;
		case R.id.mode_11:
			TimeFragment.effect=TransitionEffect.Accordion;
			finish();
			break;
		}
	}

}
