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
	
	//������
	private TextView guideT1;
	private TextView guideT2;
		
	//�ײ�dot
	private ImageView[] dots;
	
	//��¼ע��button
	private Button btnLogin;
	private Button btnSignin;
	
	//ѡ�е�λ��
	private int currentIndex;
	
	//������
	private String[] guideText1={"����ר��","˽������","��¼����","������Ϸ"};
	private String[] guideText2={"ֻ������������һ���˽�ܿռ�","��ƽ��������˵����˵�����","��¼�����ճ������е�ÿһ��ʱ��","���ֱ˴ˣ���������µ�"};
	
	public static Activity activity;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slide_viewpager);
		
		activity=this;
		
		LayoutInflater inflater=LayoutInflater.from(this);
		
		//��ʼ��������ؼ�
		guideT1=(TextView)findViewById(R.id.text1);
		guideT2=(TextView)findViewById(R.id.text2);
		
		//��ʼ������ͼƬ
		views=new ArrayList<View>();
		views.add(inflater.inflate(R.layout.guide_one, null));
		views.add(inflater.inflate(R.layout.guide_two, null));
		views.add(inflater.inflate(R.layout.guide_three, null));
		views.add(inflater.inflate(R.layout.guide_four, null));
		
		//��ʼ��ViewPager�ؼ���Adapter
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
		
		//��ʼ���ײ���dots
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
		
		//��¼ע��button
		btnLogin=(Button)findViewById(R.id.login);
		btnSignin=(Button)findViewById(R.id.signin);
		btnLogin.setOnTouchListener(this);
		btnSignin.setOnTouchListener(this);
		btnLogin.setOnClickListener(this);
		btnSignin.setOnClickListener(this);
		
	}

	//�ײ�dot�ı任
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
