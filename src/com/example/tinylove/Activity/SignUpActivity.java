package com.example.tinylove.Activity;

import com.example.tinylove.R;
import com.example.tinylove.Database.TinyUser;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class SignUpActivity extends Activity implements OnClickListener, OnCheckedChangeListener {
	
	private ImageView back;
	private TextView next;
	
	private ImageView head;
	private RadioGroup rgSex;
	private RadioButton rbMale;
	private RadioButton rbFemale;
	
	private EditText editName;
	private EditText editPsw;
	
	public TinyUser user;
	
	public static Activity activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		activity=this;
		init();
		
	}

	private void init() {
		// TODO Auto-generated method stub
		back=(ImageView)findViewById(R.id.sign_iv_back);
		next=(TextView)findViewById(R.id.signup_next);
		head=(ImageView)findViewById(R.id.signup_change_head);
		rgSex=(RadioGroup)findViewById(R.id.signup_rg_sex);
		rbMale=(RadioButton)findViewById(R.id.male);
		rbFemale=(RadioButton)findViewById(R.id.female);
		editName=(EditText)findViewById(R.id.name);
		editPsw=(EditText)findViewById(R.id.psw);
		back.setOnClickListener(this);
		next.setOnClickListener(this);
		//head.setOnClickListener(this);
		rgSex.setOnCheckedChangeListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.sign_iv_back:
			finish();
			break;

		case R.id.signup_next:
			String n=editName.getText().toString();
			String p=editPsw.getText().toString();
			int sex=0;
			if(rbMale.isChecked()){
				sex=1;
			}
			if(rbFemale.isChecked()){
				sex=0;
			}
			if(n!=null&&!n.equals("")&&p!=null&&!p.equals("")){
				user=new TinyUser();
				if(user.isExist(n)){
					PromptDialog.title="Error";
					PromptDialog.content="该用户名已经存在！";
					Intent iP=new Intent(SignUpActivity.this, PromptDialog.class);
					startActivity(iP);
				}
				else{
					SupplyActivity.mainName=n;
					SupplyActivity.mainPsw=p;
					SupplyActivity.mainSex=sex;
					Intent intentSupply=new Intent(SignUpActivity.this,SupplyActivity.class);
					startActivity(intentSupply);
				}
			}
			break;
			
		case R.id.signup_change_head:
			Intent intent=new Intent(SignUpActivity.this, GetPictureDialog.class);
			startActivity(intent);
			break;
			
		default:
			break;
		}
		
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		if(checkedId==R.id.male){
			rbMale.setBackgroundResource(R.drawable.reg_male_hover);
			rbMale.setChecked(true);
			rbFemale.setBackgroundResource(R.drawable.reg_female);
			rbFemale.setChecked(false);
			head.setImageResource(R.drawable.user_head_male);
			
		}
		else if(checkedId==R.id.female){
			rbMale.setBackgroundResource(R.drawable.reg_male);
			rbMale.setChecked(false);
			rbFemale.setBackgroundResource(R.drawable.reg_female_hover);
			rbFemale.setChecked(true);
			head.setImageResource(R.drawable.user_head_female);
		}
	}
	
}
