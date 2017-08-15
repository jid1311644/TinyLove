package com.example.tinylove.Activity;

import java.io.File;
import java.io.FileNotFoundException;

import com.example.tinylove.R;
import com.example.tinylove.Database.TinyUser;
import com.example.tinylove.View.RoundImageView;

import android.support.v4.app.Fragment;
import android.text.method.MovementMethod;
import android.util.Log;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HomepageFragment extends Fragment implements OnClickListener {
	
	private RoundImageView imageMyHead;
	private RoundImageView imageYouHead;
	
	private ImageView imageMyWeather;
	private TextView textMyWeather;
	
	private ImageView imageYouWeather;
	private TextView textYouWeather;
	
	public static TextView textDays;
	
	private ImageView imageChangeBack;
	private LinearLayout llBackGround;
	private Bitmap mBitmap;
	protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    protected static Uri tempUri;
    private static final int CROP_SMALL_PICTURE = 2;
	
	private LinearLayout llAnni,llWish,llPhone,llCheck;
	private ImageView imageAnni,imageWish,imagePhone,imageCheck;
	
	private TinyUser user;
	
	public static int DAY=0;
	
	public static String PATH="";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_homepage, container, false);
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		initView(view);
		
		setOnListeners();
		
	}

	private void initView(View v) {
		// TODO Auto-generated method stub
		imageMyHead=(RoundImageView)v.findViewById(R.id.home_my_head);
		imageYouHead=(RoundImageView)v.findViewById(R.id.home_mycouple_head);
		user=new TinyUser();
		int[] sexs=new int[2];
		sexs=user.getSexs(MainActivity.currentUser);
		if(sexs[0]==0){
			imageMyHead.setImageResource(R.drawable.new_head_photo_female);
		}
		else{
			imageMyHead.setImageResource(R.drawable.new_head_photo_male);
		}
		if(sexs[1]==0){
			imageYouHead.setImageResource(R.drawable.new_head_photo_female);
		}
		else{
			imageYouHead.setImageResource(R.drawable.new_head_photo_male);
		}
		
		imageMyWeather=(ImageView)v.findViewById(R.id.home_my_weather_picture);
		textMyWeather=(TextView)v.findViewById(R.id.home_my_weather_text);
		
		imageYouWeather=(ImageView)v.findViewById(R.id.home_mycouple_weather_picture);
		textYouWeather=(TextView)v.findViewById(R.id.home_mycouple_weather_text);
		
		textDays=(TextView)v.findViewById(R.id.home_text_days);
		textDays.setText(DAY+"");
		
		imageChangeBack=(ImageView)v.findViewById(R.id.home_change_back);
		llBackGround=(LinearLayout)v.findViewById(R.id.home_main_background);
		String path="";
		if(!(path=user.getBackground()).equals("")){
			Uri uri=Uri.fromFile(new File(path));
			Bitmap bitmap = decodeUriBitmap(uri);
			llBackGround.setBackground(new BitmapDrawable(bitmap));
		}
		
		llAnni=(LinearLayout)v.findViewById(R.id.home_ll_anni);
		llWish=(LinearLayout)v.findViewById(R.id.home_ll_wish);
		llPhone=(LinearLayout)v.findViewById(R.id.home_ll_phone);
		llCheck=(LinearLayout)v.findViewById(R.id.home_ll_check);
		
		imageAnni=(ImageView)v.findViewById(R.id.home_btn_anni);
		imageWish=(ImageView)v.findViewById(R.id.home_btn_wish);
		imagePhone=(ImageView)v.findViewById(R.id.home_btn_phone);
		imageCheck=(ImageView)v.findViewById(R.id.home_btn_check);
		
	}
	
	private Bitmap decodeUriBitmap(Uri uri) {
		// TODO Auto-generated method stub
		Bitmap bitmap=null;
		try {
			bitmap=BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return bitmap;
	}
	
	//private Bitmap decodeUriBitmap(Uri uri) {
//		Bitmap bitmap = null;
//		try {
//			bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			return null;
//		}
//		return bitmap;
	//}

	private void setOnListeners() {
		// TODO Auto-generated method stub

		
		//imageMyHead.setOnClickListener(this);
		//imageMyWeather.setOnClickListener(this);
		imageMyWeather.setVisibility(View.GONE);
		//textMyWeather.setOnClickListener(this);
		textMyWeather.setVisibility(View.VISIBLE);
		textMyWeather.setText(user.getNames(MainActivity.currentUser)[0]);
		//imageYouHead.setOnClickListener(this);
		//imageYouWeather.setOnClickListener(this);
		imageYouWeather.setVisibility(View.GONE);
		//textYouWeather.setOnClickListener(this);
		textYouWeather.setVisibility(View.VISIBLE);
		textYouWeather.setText(user.getNames(MainActivity.currentUser)[1]);
		
		imageChangeBack.setOnClickListener(this);

		llAnni.setOnClickListener(this);
		llWish.setOnClickListener(this);
		llPhone.setOnClickListener(this);
		llCheck.setOnClickListener(this);
		imageAnni.setOnClickListener(this);
		imageWish.setOnClickListener(this);
		imagePhone.setOnClickListener(this);
		imageCheck.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		case R.id.home_my_head:
			Intent intentMyHead=new Intent(getActivity(), GetPictureDialog.class);
			startActivity(intentMyHead);
			break;
			
		case R.id.home_mycouple_head:
			Intent intentYouHead=new Intent(getActivity(), GetPictureDialog.class);
			startActivity(intentYouHead);
			break;
			
		case R.id.home_my_weather_picture:
		case R.id.home_my_weather_text:
			Toast.makeText(getActivity(), "myW", Toast.LENGTH_SHORT).show();
			
			break;
			
		case R.id.home_mycouple_weather_picture:
		case R.id.home_mycouple_weather_text:
			Toast.makeText(getActivity(), "youW", Toast.LENGTH_SHORT).show();
			
			break;

		case R.id.home_change_back:
			GetPictureDialog.isHomePage=true;
			GetPictureDialog.imageView=llBackGround;
			GetPictureDialog.fileName="mainImage.png";
			Intent i=new Intent(getActivity(), GetPictureDialog.class);
			startActivityForResult(i,0);
			
			break;

		case R.id.home_ll_anni:
		case R.id.home_btn_anni:
			Intent intentAnni=new Intent(getActivity(), AnniActivity.class);
			startActivity(intentAnni);
			break;

		case R.id.home_ll_wish:
		case R.id.home_btn_wish:
			Intent intentWish=new Intent(getActivity(), WishActivity.class);
			startActivity(intentWish);
			break;

		case R.id.home_ll_phone:
		case R.id.home_btn_phone:
			Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"));  
		    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
		    startActivity(intent);
			break;

		case R.id.home_ll_check:
		case R.id.home_btn_check:
			Intent intentCheck=new Intent(getActivity(), CheckActivity.class);
			startActivity(intentCheck);
			break;
	
		default:
			break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==0&&requestCode==0){
			if(GetPictureDialog.isSelect){
				TinyUser user=new TinyUser();
				user.setBackground(HomepageFragment.PATH);
			}
		}
	}
	
	
	
	
    
}
