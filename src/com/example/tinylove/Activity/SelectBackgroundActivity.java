package com.example.tinylove.Activity;

import com.example.tinylove.R;
import com.example.tinylove.Adapter.MyBackgroundRecyclerAdapter;
import com.example.tinylove.Adapter.MyRecyclerAdapter;
import com.example.tinylove.Database.TinyAnni;
import com.example.tinylove.Interface.ItemClickListener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class SelectBackgroundActivity extends Activity {
	
	final int[] backgroundBigBlur={R.drawable.anni_bg_large_blur_1,R.drawable.anni_bg_large_blur_2,R.drawable.anni_bg_large_blur_3,R.drawable.anni_bg_large_blur_4,
			R.drawable.anni_bg_large_blur_5,R.drawable.anni_bg_large_blur_6,R.drawable.anni_bg_large_blur_7,R.drawable.anni_bg_large_blur_8,
			R.drawable.anni_bg_large_blur_9,R.drawable.anni_bg_large_blur_10,R.drawable.anni_bg_large_blur_11,R.drawable.anni_bg_large_blur_12,
			R.drawable.anni_bg_large_blur_13,R.drawable.anni_bg_large_blur_14,R.drawable.anni_bg_large_blur_15,R.drawable.anni_bg_large_blur_16,
			R.drawable.anni_bg_large_blur_17,R.drawable.anni_bg_large_blur_18,R.drawable.anni_bg_large_blur_19,R.drawable.anni_bg_large_blur_20};
	final int[] backgroundBig={R.drawable.anni_bg_large_1,R.drawable.anni_bg_large_2,R.drawable.anni_bg_large_3,R.drawable.anni_bg_large_4,R.drawable.anni_bg_large_5,
			R.drawable.anni_bg_large_6,R.drawable.anni_bg_large_7,R.drawable.anni_bg_large_8,R.drawable.anni_bg_large_9,R.drawable.anni_bg_large_10,
			R.drawable.anni_bg_large_11,R.drawable.anni_bg_large_12,R.drawable.anni_bg_large_13,R.drawable.anni_bg_large_14,R.drawable.anni_bg_large_15,
			R.drawable.anni_bg_large_16,R.drawable.anni_bg_large_17,R.drawable.anni_bg_large_18,R.drawable.anni_bg_large_19,R.drawable.anni_bg_large_20};
	
	
	private ImageView back;
	private TextView complete;
	
	private RelativeLayout bigFlurBackGround;
	
	private LinearLayout bigBackground;
	private TextView text1;
	private TextView text2;
	private TextView text3;
	
	private RecyclerView selectBackground;
	private RecyclerView.LayoutManager layoutManage;
	private MyBackgroundRecyclerAdapter adapter;	
	
	public static String anniID="";
	
	public static int IMAGE=0;
	
	private int lastImage=0;
	public static boolean isDetail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_background);
		
		initView();
		setOnListeners();
		
	}

	private void initView() {
		// TODO Auto-generated method stub
		back=(ImageView)findViewById(R.id.add_anni_background_back);
		complete=(TextView)findViewById(R.id.add_anni_background_next);
		
		bigFlurBackGround=(RelativeLayout)findViewById(R.id.add_anni_flur_background);
		bigBackground=(LinearLayout)findViewById(R.id.add_anni_background_big_picture);
		bigFlurBackGround.setBackgroundResource(backgroundBigBlur[IMAGE]);
		bigBackground.setBackgroundResource(backgroundBig[IMAGE]);
		
		text1=(TextView)findViewById(R.id.add_anni_background_big_picture_text1);
		text2=(TextView)findViewById(R.id.add_anni_background_big_picture_text2);
		text3=(TextView)findViewById(R.id.add_anni_background_big_picture_text3);
		
		selectBackground=(RecyclerView)findViewById(R.id.add_anni_recycler);
		selectBackground.setHasFixedSize(true);
		layoutManage=new LinearLayoutManager(SelectBackgroundActivity.this);
		((LinearLayoutManager) layoutManage).setOrientation(LinearLayoutManager.HORIZONTAL);
		selectBackground.setLayoutManager(layoutManage);
		
		adapter=new MyBackgroundRecyclerAdapter(IMAGE);
		selectBackground.setAdapter(adapter);
		
	}

	private void setOnListeners() {
		// TODO Auto-generated method stub
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(isDetail){
					Intent ii=new Intent(SelectBackgroundActivity.this, AnniDetailActivity.class);
					startActivity(ii);
					SelectBackgroundActivity.isDetail=false;
				}
				SelectBackgroundActivity.this.finish();
			}
		});
		
		complete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AnniDetailActivity.IMAGE=SelectBackgroundActivity.IMAGE;
				AddAnniActivity.IMAGE=SelectBackgroundActivity.IMAGE;
				TinyAnni anni=new TinyAnni();
				//anni.display();
				anni.setBackground(SelectBackgroundActivity.IMAGE, SelectBackgroundActivity.anniID);
				//anni.display();
				if(isDetail){
					Intent ii=new Intent(SelectBackgroundActivity.this, AnniDetailActivity.class);
					startActivity(ii);
					SelectBackgroundActivity.isDetail=false;
					SelectBackgroundActivity.this.finish();
				}
				finish();
				
//				Intent i=new Intent(SelectBackgroundActivity.this, SelectBackgroundActivity.class);
//				startActivity(i);
//				finish();
//				
			}
		});
		
		adapter.setOnClickListener(new ItemClickListener() {
			
			@Override
			public void onItemClickListener(int position) {
				// TODO Auto-generated method stub
				SelectBackgroundActivity.IMAGE=position;
				if(lastImage==position);
				else{
					bigBackground.setBackgroundResource(backgroundBig[position]);
					bigFlurBackGround.setBackgroundResource(backgroundBigBlur[position]);
					lastImage=position;
				}
//				View v1=layoutManage.findViewByPosition(position);
//				View v2=layoutManage.findViewByPosition(adapter.getSelectID());
//				
//				ImageView i1=(ImageView)v1.findViewById(R.id.add_anni_recycler_item_select);
//				i1.setVisibility(View.VISIBLE);
//				
//				ImageView i2=(ImageView)v2.findViewById(R.id.add_anni_recycler_item_select);
//				i2.setVisibility(View.GONE);
//				
//				adapter.setSelectID(position);
				

				
			}
		});
		
	}
	
}




