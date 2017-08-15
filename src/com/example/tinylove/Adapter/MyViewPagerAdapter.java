package com.example.tinylove.Adapter;

import java.util.List;

import android.app.Activity;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

public class MyViewPagerAdapter extends PagerAdapter{
	
	private List<View> views;
	private Activity activity;
	
	private static final String SHAREDPREFERENCES_NAME = "first_pref";
	
	public MyViewPagerAdapter(List<View> views,Activity activity) {
		// TODO Auto-generated constructor stub
		this.views=views;
		this.activity=activity;
	}
	
	//初始化arg1位置的界面
	@Override
	public Object instantiateItem(View arg0, int arg1) {
		// TODO Auto-generated method stub
		((ViewPager)arg0).addView(views.get(arg1), 0);
		return views.get(arg1);
	}
	
	//销毁arg1位置的界面
	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		// TODO Auto-generated method stub
		((ViewPager)arg0).removeView(views.get(arg1));
	}

	//判断是否由对象生成界面
	@Override
	public boolean isViewFromObject(View arg0,Object arg1){
		return (arg0==arg1);
	}

	//获取页面数
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(views!=null){
			return views.size();
		}
		
		return 0;
	}

	@Override
	public void finishUpdate(View container) {
		// TODO Auto-generated method stub
	}

	@Override
	public void restoreState(Parcelable state, ClassLoader loader) {
		// TODO Auto-generated method stub
	}

	@Override
	public Parcelable saveState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void startUpdate(View container) {
		// TODO Auto-generated method stub
	}
	
	
	

}
