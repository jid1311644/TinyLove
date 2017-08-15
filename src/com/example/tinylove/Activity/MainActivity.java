package com.example.tinylove.Activity;


import java.util.ArrayList;

import com.example.tinylove.R;
import com.example.tinylove.Adapter.MyFragmentPagerAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener, OnPageChangeListener {
	
	public static String currentUser="";
	
	private Fragment[] fragments;
	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;

	private LinearLayout llHome;
	private Button rbFBHome;
	private TextView textFBHome;
	private LinearLayout llTime;
	private Button rbFBTime;
	private TextView textFBTime;
	private LinearLayout llSet;
	private Button rbFBSet;
	private TextView textFBSet;
	
	private ViewPager mainVP;
	private ArrayList<Fragment> fragmentArrayList;
	private int currentIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
		
	}

	private void init() {
		// TODO Auto-generated method stub
		//��ʼ�������ռ�
		//�ײ���HOME��  ���岼��ll��ͼƬ��ťrbFB��ͼƬ������textFB
		llHome=(LinearLayout)findViewById(R.id.main_rgl_home);
		rbFBHome=(Button)findViewById(R.id.main_rb_home);
		textFBHome=(TextView)findViewById(R.id.main_rbtext_home);
		//�ײ���TIME��  ���岼��ll��ͼƬ��ťrbFB��ͼƬ������textFB
		llTime=(LinearLayout)findViewById(R.id.main_rgl_time);
		rbFBTime=(Button)findViewById(R.id.main_rb_time);
		textFBTime=(TextView)findViewById(R.id.main_rbtext_time);
		//�ײ���SET��  ���岼��ll��ͼƬ��ťrbFB��ͼƬ������textFB
		llSet=(LinearLayout)findViewById(R.id.main_rgl_set);
		rbFBSet=(Button)findViewById(R.id.main_rb_set);
		textFBSet=(TextView)findViewById(R.id.main_rbtext_set);
		
		//��ʼ��fragment
		fragments=new Fragment[3];
//		fragments[0]=fragmentManager.findFragmentById(R.id.fragment_home);
//		fragments[1]=fragmentManager.findFragmentById(R.id.fragment_time);
//		fragments[2]=fragmentManager.findFragmentById(R.id.fragment_set);
		fragmentArrayList=new ArrayList<Fragment>();
		fragmentArrayList.add(new HomepageFragment());//������fragment���뵽����
		fragmentArrayList.add(new TimeFragment());
		fragmentArrayList.add(new SettingFragment());
		fragmentManager=getSupportFragmentManager();
		//fragment�¼�
//		fragmentTransaction=fragmentManager.beginTransaction()
//				.hide(fragments[0]).hide(fragments[1]).hide(fragments[2]);
//		fragmentTransaction.show(fragments[0]).commit();
		
		//��ʼ����������
		mainVP=(ViewPager)findViewById(R.id.main_viewpager);
		mainVP.setAdapter(new MyFragmentPagerAdapter(fragmentManager, fragmentArrayList));
		mainVP.setCurrentItem(0);
		
		setOnListener();
	}

	private void setOnListener() {
		// TODO Auto-generated method stub

		llHome.setOnClickListener(this);
		llTime.setOnClickListener(this);
		llSet.setOnClickListener(this);
		rbFBHome.setOnClickListener(this);
		rbFBTime.setOnClickListener(this);
		rbFBSet.setOnClickListener(this);
		
		mainVP.setOnPageChangeListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
//		fragmentTransaction=fragmentManager.beginTransaction()
//				.hide(fragments[0]).hide(fragments[1]).hide(fragments[2]);
		
		switch (v.getId()) {
		case R.id.main_rb_home:
		case R.id.main_rgl_home:
			//fragmentTransaction.show(fragments[0]).commit();
			if(currentIndex!=0)
				mainVP.setCurrentItem(0);
			
			rbFBHome.setBackgroundResource(R.drawable.homepage_press);
			textFBHome.setTextColor(getResources().getColor(R.color.themeColor));
			rbFBTime.setBackgroundResource(R.drawable.time_nopress);
			textFBTime.setTextColor(getResources().getColor(R.color.textColor));
			rbFBSet.setBackgroundResource(R.drawable.set_nopress);
			textFBSet.setTextColor(getResources().getColor(R.color.textColor));
			currentIndex=0;
			break;

		case R.id.main_rb_time:
		case R.id.main_rgl_time:
			//fragmentTransaction.show(fragments[1]).commit();
			if(currentIndex!=1)
				mainVP.setCurrentItem(1);
			rbFBHome.setBackgroundResource(R.drawable.homepage_nopress);
			textFBHome.setTextColor(getResources().getColor(R.color.textColor));
			rbFBTime.setBackgroundResource(R.drawable.time_press);
			textFBTime.setTextColor(getResources().getColor(R.color.themeColor));
			rbFBSet.setBackgroundResource(R.drawable.set_nopress);
			textFBSet.setTextColor(getResources().getColor(R.color.textColor));
			currentIndex=1;
			break;

		case R.id.main_rb_set:
		case R.id.main_rgl_set:
			//fragmentTransaction.show(fragments[2]).commit();
			if(currentIndex!=2)
				mainVP.setCurrentItem(2);
			rbFBHome.setBackgroundResource(R.drawable.homepage_nopress);
			textFBHome.setTextColor(getResources().getColor(R.color.textColor));
			rbFBTime.setBackgroundResource(R.drawable.time_nopress);
			textFBTime.setTextColor(getResources().getColor(R.color.textColor));
			rbFBSet.setBackgroundResource(R.drawable.set_press);
			textFBSet.setTextColor(getResources().getColor(R.color.themeColor));
			currentIndex=2;
			break;

		default:
			break;
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
//		fragmentTransaction=fragmentManager.beginTransaction()
//				.hide(fragments[0]).hide(fragments[1]).hide(fragments[2]);
		switch(position){
		//�����˵�һҳ
		case 0:
				//fragmentTransaction.show(fragments[0]).commit();
				
				rbFBHome.setBackgroundResource(R.drawable.homepage_press);
				textFBHome.setTextColor(getResources().getColor(R.color.themeColor));
				rbFBTime.setBackgroundResource(R.drawable.time_nopress);
				textFBTime.setTextColor(getResources().getColor(R.color.textColor));
				rbFBSet.setBackgroundResource(R.drawable.set_nopress);
				textFBSet.setTextColor(getResources().getColor(R.color.textColor));
				currentIndex=0;
			break;
		//�����˵ڶ�ҳ
		case 1:
			//fragmentTransaction.show(fragments[1]).commit();
			
			rbFBHome.setBackgroundResource(R.drawable.homepage_nopress);
			textFBHome.setTextColor(getResources().getColor(R.color.textColor));
			rbFBTime.setBackgroundResource(R.drawable.time_press);
			textFBTime.setTextColor(getResources().getColor(R.color.themeColor));
			rbFBSet.setBackgroundResource(R.drawable.set_nopress);
			textFBSet.setTextColor(getResources().getColor(R.color.textColor));
			currentIndex=1;
			break;
		//�����˵���ҳ
		case 2:
			//fragmentTransaction.show(fragments[2]).commit();
			
			rbFBHome.setBackgroundResource(R.drawable.homepage_nopress);
			textFBHome.setTextColor(getResources().getColor(R.color.textColor));
			rbFBTime.setBackgroundResource(R.drawable.time_nopress);
			textFBTime.setTextColor(getResources().getColor(R.color.textColor));
			rbFBSet.setBackgroundResource(R.drawable.set_press);
			textFBSet.setTextColor(getResources().getColor(R.color.themeColor));
			currentIndex=2;
			break;
		default:
			break;
		}
	}
	
}
