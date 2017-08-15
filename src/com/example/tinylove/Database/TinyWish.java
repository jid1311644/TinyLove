package com.example.tinylove.Database;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import com.example.tinylove.Activity.MainActivity;
import com.example.tinylove.Activity.SplashActivity;

import android.content.ContentValues;
import android.database.Cursor;

public class TinyWish {
	
	public final static String CREATE_TABLE_TINYWISH=
			"CREATE TABLE `tiny_wish` ("
			+ "`user_name` varchar(50) NOT NULL,"
			+ "`wish_id` varchar(10) NOT NULL,"
			+ "`wish_time` varchar(30) NOT NULL,"
			+ "`wish_content` varchar(300) NOT NULL,"
			+ "`wish_state` int(1) NOT NULL,"
			+ "PRIMARY KEY (`user_name`,`wish_id`),"
			+ "FOREIGN KEY (`user_name`) REFERENCES `tiny_user` (`user_name`)"
			+ ")";
	
	public String name;
	public String wishID;
	public String wishTime;
	public String wishContent;
	public int wishState;	//0未实现 1已实现

	LinkedList<String> wids=new LinkedList<String>();
	LinkedList<String> wts=new LinkedList<String>();
	LinkedList<String> wcs=new LinkedList<String>();
	LinkedList<Integer> wss=new LinkedList<Integer>();
	
	public void getData(String name){
		String sql="select * from tiny_wish where user_name='"+name+"'";
		Cursor cursor=SplashActivity.dbHelper.getReadableDatabase().rawQuery(sql, null);
		while(cursor.moveToNext()){
			wids.add(cursor.getString(1));
			wts.add(cursor.getString(2));
			wcs.add(cursor.getString(3));
			wss.add(cursor.getInt(4));
		}
	}

	public LinkedList<String> getWids() {
		return wids;
	}

	public LinkedList<String> getWts() {
		return wts;
	}

	public LinkedList<String> getWcs() {
		return wcs;
	}

	public LinkedList<Integer> getWss() {
		return wss;
	}
	
	public void setState(String id,int state){
		String sql="update tiny_wish set wish_state="+state+" "
				+ " where user_name='"+MainActivity.currentUser+"' and wish_id='"+id+"'";
		SplashActivity.dbHelper.getReadableDatabase().execSQL(sql);
	}

	public void delete(String id){
		String sql="delete from tiny_wish where user_name='"+MainActivity.currentUser+"' and wish_id='"+id+"'";
		SplashActivity.dbHelper.getReadableDatabase().execSQL(sql);
	}
	
	public int getLastID(){
		String sql="select wish_id from tiny_wish";
		Cursor cursor=SplashActivity.dbHelper.getReadableDatabase().rawQuery(sql, null);
		String id="";
		while(cursor.moveToNext()){
			id=cursor.getString(0);
		}
		if(id.equals("")){
			return 0;
		}
		else{
			return Integer.valueOf(id);
		}
	}
	
	public void addWish(String content){
		ContentValues values=new ContentValues();
		values.put("user_name", MainActivity.currentUser);
		String id=(getLastID()+1)+"";
		if(id.length()==1)id="000"+id;
		if(id.length()==2)id="00"+id;
		if(id.length()==3)id="0"+id;
		values.put("wish_id", id);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		values.put("wish_time",sdf.format(new Date()));
		values.put("wish_content", content);
		values.put("wish_state", 0);
		SplashActivity.dbHelper.getReadableDatabase().insert("tiny_wish", null, values);
	}
	
	public void display(){
		getData(MainActivity.currentUser);
		for(int i=0;i<wids.size();i++){
			System.out.println(";"+wids.get(i)+"   ;"+wcs.get(i)+"   ;"+wts.get(i)+"   ;"+wss.get(i));
		}
	}
	
}
