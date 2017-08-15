package com.example.tinylove.Activity;

import com.example.tinylove.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class SettingFragment extends Fragment implements OnClickListener {
	
	private LinearLayout llUser;
	private LinearLayout llAboutUs;
	private LinearLayout llGitHub;
	private LinearLayout llLogout;
	private LinearLayout llExit;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_setting, container, false);
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		llUser=(LinearLayout)view.findViewById(R.id.setting_edit_user);
		llAboutUs=(LinearLayout)view.findViewById(R.id.setting_about_us);
		llGitHub=(LinearLayout)view.findViewById(R.id.setting_github);
		llLogout=(LinearLayout)view.findViewById(R.id.setting_logout);
		llExit=(LinearLayout)view.findViewById(R.id.setting_exit);
		
		llUser.setOnClickListener(this);
		llAboutUs.setOnClickListener(this);
		llGitHub.setOnClickListener(this);
		llLogout.setOnClickListener(this);
		llExit.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.setting_edit_user){
			
			Intent iu=new Intent(getActivity(), UserEditActivity.class);
			startActivity(iu);
			
		}
		else if(v.getId()==R.id.setting_about_us){
			
			AboutUsDialog.content="    这是一款针对情侣设计的应用软件,致力于让情侣的生活更加美好。\n"
					+ "  【情侣纪念日】：记录情侣之间每一个值得纪念的日子。\n"
					+ "  【情侣愿望】：足下愿望，两个人一起去实现它。\n"
					+ "  【联系TA】：想TA？还等什么快去给TA打电话吧。\n"
					+ "  【情侣打卡】：共同完成甜蜜的小日常。\n"
					+ "  【微爱时光】：用照片记录下爱的时光。\n";
			Intent ip=new Intent(getActivity(), AboutUsDialog.class);
			startActivity(ip);
			
		}
		else if(v.getId()==R.id.setting_github){
			
			Intent ib=new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/jid1311644/TinyLove"));
			startActivity(ib);
			
		}
		else if(v.getId()==R.id.setting_logout){
			
			ConfirmDialog.content="确认注销？";
			Intent ic=new Intent(getActivity(), ConfirmDialog.class);
			startActivityForResult(ic, 11);
			
		}
		else if(v.getId()==R.id.setting_exit){
			
			ConfirmDialog.content="确认退出？";
			Intent ic0=new Intent(getActivity(), ConfirmDialog.class);
			startActivityForResult(ic0, 12);
			
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==11&&resultCode==0){
			if(ConfirmDialog.OK){
				Intent ig=new Intent(getActivity(), GuideActivity.class);
				startActivity(ig);
				getActivity().finish();
			}
		}
		if(requestCode==12&&resultCode==0){
			if(ConfirmDialog.OK){
				System.exit(0); 
			}
		}
		
	}
	
	
	
	
}
