package com.example.tinylove.Activity;

import java.util.LinkedList;

import com.example.tinylove.R;
import com.example.tinylove.Adapter.MyRecyclerAdapter;
import com.example.tinylove.Database.TinyAnni;
import com.example.tinylove.Interface.ItemClickListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class AnniActivity extends Activity {

	public static final int ANNI_BACK_TOGETHER=7;
	public static final int ANNI_BACK_BIRTHDAY=8;
	public static final int ANNI_BACK_HUG=10;
	public static final int ANNI_BACK_KISS=9;
	public static final int ANNI_BACK_MARRY=11;
	public static final int ANNI_BACK_TRAVEL=12;
	
	private ImageView back;
	private ImageView more;
	
	private RelativeLayout rl;
	private TextView textBig;
	private TextView textSmall;
	private TextView textDays;
	
	private RecyclerView recyclerView;
	private RecyclerView.LayoutManager layoutManage;
	
	private MyRecyclerAdapter adapter;
	
	private TinyAnni anni;
	
	public static boolean isEmpty;
	public static Activity activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_anni);
		
		activity=this;
		
		initView();
		setOnListeners();
		
	}

	public LinkedList<String> ids;
	public LinkedList<String> events;
	public LinkedList<String> days;
	public LinkedList<String> Y;
	public LinkedList<String> M;
	public LinkedList<String> D;
	public LinkedList<Integer> back0;
	
	private void initView() {
		// TODO Auto-generated method stub
		back=(ImageView)findViewById(R.id.anni_back);
		more=(ImageView)findViewById(R.id.anni_more);

		rl=(RelativeLayout)findViewById(R.id.anni_rl_top);
		textBig=(TextView)findViewById(R.id.anni_text1);
		textSmall=(TextView)findViewById(R.id.anni_text2);
		textDays=(TextView)findViewById(R.id.anni_days);
		
		recyclerView=(RecyclerView)findViewById(R.id.anni_recycler);
		recyclerView.setHasFixedSize(true);
		layoutManage=new LinearLayoutManager(AnniActivity.this);
		recyclerView.setLayoutManager(layoutManage);
		
		anni=new TinyAnni();
		anni.display();
		anni.getData(MainActivity.currentUser);
		ids=anni.getIds();
		events=anni.getEvents();
		days=anni.getDays();
		Y=anni.getY();
		M=anni.getM();
		D=anni.getD();
		back0=anni.getBack();
		
		order();
		if(AnniActivity.isEmpty){
			rl.setVisibility(View.GONE);
		}
		else{
			if(ids.get(0).equals("0001")){
				HomepageFragment.DAY=Integer.valueOf(days.get(0));
				HomepageFragment.textDays.setText(days.get(0));
			}
			
			rl.setVisibility(View.VISIBLE);
			String fre=anni.getFrequent(MainActivity.currentUser, ids.get(0));
			if(fre.equals("无")){
				textBig.setText(events.get(0)+"已经");
			}
			else{
				textBig.setText(events.get(0)+"还有");
			}
			String date=Y.get(0)+"-"+M.get(0)+"-"+D.get(0);
			textSmall.setText("目标日："+anni.getTargetDate(date, fre));
			textDays.setText(days.get(0));
			
		}
		
		adapter=new MyRecyclerAdapter(events, days, Y, M, D,back0);
		recyclerView.setAdapter(adapter);
		
	}
	
	private void order() {
		// TODO Auto-generated method stub
		
		LinkedList<String> _ids=new LinkedList<String>();
		LinkedList<String> _events=new LinkedList<String>();
		LinkedList<String> _days=new LinkedList<String>();
		LinkedList<String> _Y=new LinkedList<String>();
		LinkedList<String> _M=new LinkedList<String>();
		LinkedList<String> _D=new LinkedList<String>();
		LinkedList<Integer> _back0=new LinkedList<Integer>();
		int count=0;
		for(int i=0;i<ids.size();i++){
			if(!Y.get(i).equals("")&&!M.get(i).equals("")&&!D.get(i).equals("")){
				count++;
				_ids.add(ids.get(i));
				_events.add(events.get(i));
				_days.add(days.get(i));
				_Y.add(Y.get(i));
				_M.add(M.get(i));
				_D.add(D.get(i));
				_back0.add(back0.get(i));
			}
		}
		for(int i=0;i<ids.size();i++){
			if(Y.get(i).equals("")||M.get(i).equals("")||D.get(i).equals("")){
				_ids.add(ids.get(i));
				_events.add(events.get(i));
				_days.add(days.get(i));
				_Y.add(Y.get(i));
				_M.add(M.get(i));
				_D.add(D.get(i));
				_back0.add(back0.get(i));
			}
		}
		ids=_ids;
		events=_events;
		days=_days;
		Y=_Y;
		M=_M;
		D=_D;
		back0=_back0;
		if(count==0){
			AnniActivity.isEmpty=true;
		}
		else{
			AnniActivity.isEmpty=false;;
		}
	}

	private void setOnListeners() {
		// TODO Auto-generated method stub
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		more.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String id=(anni.getLastID()+1)+"";
				if(id.length()==1)id="000"+id;
				else if(id.length()==2)id="00"+id;
				else if(id.length()==3)id="0"+id;
				else if(id.length()==4);
				AddAnniActivity.anniID=id;
				Intent intent1=new Intent(AnniActivity.this, AddAnniActivity.class);
				startActivity(intent1);
				finish();
			}
		});
		
		adapter.setOnClickListener(new ItemClickListener() {
			
			@Override
			public void onItemClickListener(int position) {
				// TODO Auto-generated method stub
				if(!ids.get(position).equals("0001")&&!ids.get(position).equals("0002")&&!ids.get(position).equals("0003")&&!ids.get(position).equals("0004")&&
						!ids.get(position).equals("0005")&&!ids.get(position).equals("0006")&&!ids.get(position).equals("0007")){
					AnniDetailActivity.anniID=ids.get(position);
					Intent intent0=new Intent(AnniActivity.this, AnniDetailActivity.class);
					startActivity(intent0);
					finish();
				}
				else if(Y.get(position).equals("")||M.get(position).equals("")||D.get(position).equals("")){
					AddAnniActivity.anniID=ids.get(position);
					Intent intent1=new Intent(AnniActivity.this, AddAnniActivity.class);
					startActivity(intent1);
					finish();
				}
				else{
					AnniDetailActivity.anniID=ids.get(position);
					Intent intent2=new Intent(AnniActivity.this, AnniDetailActivity.class);
					startActivity(intent2);
					finish();
				}
				
			}
		});
		
	}

}
