package com.example.tinylove.Activity;

import java.util.LinkedList;

import com.example.tinylove.R;
import com.example.tinylove.Adapter.MyWishRecyclerAdapter;
import com.example.tinylove.Database.TinyWish;
import com.example.tinylove.Interface.ItemClickListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class WishActivity extends Activity {
	
	private ImageView back;
	private ImageView more;
	
	private RecyclerView wishList;
	private RecyclerView.LayoutManager layoutManage;
	private MyWishRecyclerAdapter adapter;
	
	public static Activity activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wish);
		activity=this;
		initView();
		onSetListeners();
		
	}

	private LinkedList<String> wishIDs;
	private LinkedList<String> wishTimes;
	private LinkedList<String> wishContents;
	private LinkedList<Integer> wishStates;

	private void initView() {
		// TODO Auto-generated method stub
		back=(ImageView)findViewById(R.id.wish_back);
		more=(ImageView)findViewById(R.id.wish_more);
		
		wishList=(RecyclerView)findViewById(R.id.wish_recycler);
		wishList.setHasFixedSize(true);
		layoutManage=new LinearLayoutManager(WishActivity.this);
		wishList.setLayoutManager(layoutManage);
		
		TinyWish wish=new TinyWish();
		wish.getData(MainActivity.currentUser);
		wishIDs=wish.getWids();
		wishTimes=wish.getWts();
		wishContents=wish.getWcs();
		wishStates=wish.getWss();
		
		adapter=new MyWishRecyclerAdapter(wishContents, wishStates);
		wishList.setAdapter(adapter);
		
	}

	private void onSetListeners() {
		// TODO Auto-generated method stub
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				WishActivity.this.finish();
			}
		});
		
		more.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intentMore=new Intent(WishActivity.this, WishAddDialog.class);
				startActivity(intentMore);
			}
		});
		
		adapter.setOnClickListener(new ItemClickListener() {
			
			@Override
			public void onItemClickListener(int position) {
				// TODO Auto-generated method stub
				WishDetailDialog.wishID=wishIDs.get(position);
				WishDetailDialog.wishCon=wishContents.get(position);
				WishDetailDialog.wishTime=wishTimes.get(position);
				WishDetailDialog.wishState=wishStates.get(position);
				Intent intent=new Intent(WishActivity.this, WishDetailDialog.class);
				startActivity(intent);
			}
		});
	}

}
