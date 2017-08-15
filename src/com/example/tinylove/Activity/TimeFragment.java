package com.example.tinylove.Activity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import com.example.tinylove.R;
import com.example.tinylove.Database.TinyTimePicture;
import com.example.tinylove.View.OutlineContainer;
import com.example.tinylove.View.PictureView;
import com.example.tinylove.View.PictureView.TransitionEffect;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TimeFragment extends Fragment implements OnClickListener {
	
	private PictureView pictureView;
	
	private TextView mode;
	private ImageView add;
	private ImageView delete;
	
	public static TransitionEffect effect=TransitionEffect.Tablet;
	
	private View v;
	
	public static String PATH="";
	
	private int position=0;
	
	private LinkedList<String> pictures=new LinkedList<String>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_time, container, false);
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		this.v=view;
		
		mode=(TextView)view.findViewById(R.id.time_picture_mode);
		add=(ImageView)view.findViewById(R.id.time_picture_add);
		delete=(ImageView)view.findViewById(R.id.time_picture_delete);
		
		setPlayMode(view, effect);
		
		mode.setOnClickListener(this);
		add.setOnClickListener(this);
		delete.setOnClickListener(this);
		
	}
	
	private void setPlayMode(View v,TransitionEffect effect){
		
		TinyTimePicture time=new TinyTimePicture();
		time.getData(MainActivity.currentUser);
		pictures=time.getPictures();
		
//		for(int i=0;i<pictures.size();i++)
//			System.out.println(i+"   "+pictures.get(i));
//		System.out.println(effect);
		
		pictureView = (PictureView)v.findViewById(R.id.time_picture);
		pictureView.setTransitionEffect(effect);
		pictureView.setAdapter(new MyPictureAdapter(pictures));
		pictureView.setPageMargin(30);
		
		pictureView.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				position=arg0;
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
		
	}
	
	
	private class MyPictureAdapter extends PagerAdapter{
		
		LinkedList<String> pictures;
		
		public MyPictureAdapter(LinkedList<String> pictures) {
			// TODO Auto-generated constructor stub
			this.pictures=pictures;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return pictures.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object arg1) {
			// TODO Auto-generated method stub
			if (view instanceof OutlineContainer)
			{
				return ((OutlineContainer) view).getChildAt(0) == arg1;
			} else
			{
				return view == arg1;
			}
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView(pictureView.findViewFromObject(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			
			ImageView image=new ImageView(getActivity());
			Bitmap bitmap=getLoacalBitmap(pictures.get(position));
			image.setImageBitmap(bitmap);
			container.addView(image, LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);
			
			pictureView.setObjectForPosition(image, position);
			return image;
		}

	}
	
	public static Bitmap getLoacalBitmap(String url) {
        try {
             FileInputStream fis = new FileInputStream(url);
             return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片        

          } catch (FileNotFoundException e) {
             e.printStackTrace();
             return null;
        }
   }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.time_picture_add:
			GetPictureDialog.isHomePage=false;
			View vt=new View(getActivity());
			GetPictureDialog.imageView=vt;
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			String name=sdf.format(new Date(System.currentTimeMillis()));
			name="image"+name+".png";
			GetPictureDialog.fileName=name;
			Intent i=new Intent(getActivity(), GetPictureDialog.class);
			startActivityForResult(i,8);
			break;

		case R.id.time_picture_delete:
			if(pictures.size()>0){
				ConfirmDialog.content="确认删除这张图片吗？";
				Intent ic=new Intent(getActivity(), ConfirmDialog.class);
				startActivityForResult(ic,10);
			}
			break;
			
		case R.id.time_picture_mode:
			Intent intent=new Intent(getActivity(), TimeSelectModeDialog.class);
			startActivityForResult(intent, 9);
			break;
			
		default:
			break;
		}
		
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==9&&resultCode==0){
			setPlayMode(v, effect);
		}
		if(requestCode==8&&resultCode==0){
			if(GetPictureDialog.isSelect){
				TinyTimePicture picture=new TinyTimePicture();
				picture.addPath(MainActivity.currentUser, TimeFragment.PATH);
				setPlayMode(v, TransitionEffect.Tablet);
			}
		}
		if(requestCode==10&&resultCode==0){
			if(ConfirmDialog.OK){
				TinyTimePicture time=new TinyTimePicture();
				time.getData(MainActivity.currentUser);
				time.deletePath(MainActivity.currentUser, time.getPictures().get(position));
				setPlayMode(v, TransitionEffect.Tablet);
			}
		}
	}
	
	

}



