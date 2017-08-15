package com.example.tinylove.Activity;

import java.util.ArrayList;
import java.util.List;

import com.example.tinylove.R;
import com.example.tinylove.Adapter.MyViewPagerAdapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.method.MovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GuideActivity extends Activity implements OnTouchListener, OnClickListener{
	
	private ViewPager vp;
	private MyViewPagerAdapter vpAdapter;
	private List<View> views;
	
	//引导语
	private TextView guideT1;
	private TextView guideT2;
		
	//底部dot
	private ImageView[] dots;
	
	//登录注册button
	private Button btnLogin;
	private Button btnSignin;
	
	//选中的位置
	private int currentIndex;
	
	//引导语
	private String[] guideText1={"情侣专属","私密聊天","记录生活","情侣游戏"};
	private String[] guideText2={"只属于你和你的另一半的私密空间","用平凡的文字说出最动人的语言","记录你们日常生活中的每一个时刻","快乐彼此，不再让你孤单"};
	
	public static Activity activity;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slide_viewpager);
		
		activity=this;
		
		LayoutInflater inflater=LayoutInflater.from(this);
		
		//初始化引导语控件
		guideT1=(TextView)findViewById(R.id.text1);
		guideT2=(TextView)findViewById(R.id.text2);
		
		//初始化引导图片
		views=new ArrayList<View>();
		views.add(inflater.inflate(R.layout.guide_one, null));
		views.add(inflater.inflate(R.layout.guide_two, null));
		views.add(inflater.inflate(R.layout.guide_three, null));
		views.add(inflater.inflate(R.layout.guide_four, null));
		
		//初始化ViewPager控件和Adapter
		vpAdapter=new MyViewPagerAdapter(views,this);
		vp=(ViewPager)findViewById(R.id.viewpager);
		vp.setAdapter(vpAdapter);
		vp.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				setCurrentDot(arg0);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//初始化底部的dots
		LinearLayout ll=(LinearLayout)findViewById(R.id.ll_guide);
		dots=new ImageView[4];
		for(int i=0;i<4;i++){
			dots[i]=(ImageView)ll.getChildAt(i);
			dots[i].setEnabled(true);
		}
		currentIndex=0;
		dots[currentIndex].setEnabled(false);
		guideT1.setText(guideText1[currentIndex]);
		guideT2.setText(guideText2[currentIndex]);
		
		//登录注册button
		btnLogin=(Button)findViewById(R.id.login);
		btnSignin=(Button)findViewById(R.id.signin);
		btnLogin.setOnTouchListener(this);
		btnSignin.setOnTouchListener(this);
		btnLogin.setOnClickListener(this);
		btnSignin.setOnClickListener(this);
		
	}

	//底部dot的变换
	private void setCurrentDot(int position) {
		if (position < 0 || position > views.size() - 1
				|| currentIndex == position) {
			return;
		}

		dots[position].setEnabled(false);
		dots[currentIndex].setEnabled(true);

		currentIndex = position;
		guideT1.setText(guideText1[currentIndex]);
		guideT2.setText(guideText2[currentIndex]);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		
		if(v.getId()==R.id.login){
			
			switch (event.getAction()) {
			case KeyEvent.ACTION_UP:
				btnLogin.setTextColor(Color.rgb(168, 168, 168));
				break;
			case KeyEvent.ACTION_DOWN:
				btnLogin.setTextColor(Color.rgb(138, 138, 138));
				break;
			default:
				break;
			}
		}
		
		else if(v.getId()==R.id.signin){
			switch (event.getAction()) {
			case KeyEvent.ACTION_UP:
				btnSignin.setTextColor(Color.rgb(255, 255, 255));
				break;
			case KeyEvent.ACTION_DOWN:
				btnSignin.setTextColor(Color.rgb(138, 138, 138));
				break;
				
			default:
				break;
			}
		}
		
		return false;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.login){
			Intent intent=new Intent(GuideActivity.this,LoginActivity.class);
			startActivity(intent);
		}
		else if(v.getId()==R.id.signin){
			Intent intent=new Intent(GuideActivity.this,SignUpActivity.class);
			startActivity(intent);
		}
	}


}
