package com.example.tinylove.Database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import com.example.tinylove.Activity.MainActivity;
import com.example.tinylove.Activity.SplashActivity;

import android.content.ContentValues;
import android.database.Cursor;

public class TinyCheck {

	public final static String CREATE_TABLE_TINYCHECK=
			"CREATE TABLE `tiny_check` ("
			+ "`user_name` varchar(50) NOT NULL,"
			+ "`check_id` varchar(10) NOT NULL,"
			+ "`check_state` int(1) NOT NULL,"
			+ "`check_times` int(4) NOT NULL,"
			+ "`check_last_day` varchar(20) NOT NULL,"
			+ "PRIMARY KEY (`user_name`,`check_id`),"
			+ "FOREIGN KEY (`user_name`) REFERENCES `tiny_user` (`user_name`)"
			+ ")";
	
	public String userName;
	public String checkID;
	public int checkState;	//0Î´´ò¿¨ 1ÒÑ´ò¿¨
	public int checkTimes;
	public String checkLastDay;
	
	LinkedList<String> cids=new LinkedList<String>();
	LinkedList<Integer> css=new LinkedList<Integer>();
	LinkedList<Integer> ctss=new LinkedList<Integer>();
	LinkedList<String> clds=new LinkedList<String>();
	
	public void init(String name){
		String date="1900-1-1";
		
		ContentValues values=new ContentValues();
		values.put("user_name", name);
		values.put("check_id", "0001");
		values.put("check_state", 0);
		values.put("check_times", 0);
		values.put("check_last_day", date);
		SplashActivity.dbHelper.getReadableDatabase().insert("tiny_check", null, values);
		
		values=new ContentValues();
		values.put("user_name", name);
		values.put("check_id", "0002");
		values.put("check_state", 0);
		values.put("check_times", 0);
		values.put("check_last_day", date);
		SplashActivity.dbHelper.getReadableDatabase().insert("tiny_check", null, values);
		
		values=new ContentValues();
		values.put("user_name", name);
		values.put("check_id", "0003");
		values.put("check_state", 0);
		values.put("check_times", 0);
		values.put("check_last_day", date);
		SplashActivity.dbHelper.getReadableDatabase().insert("tiny_check", null, values);
		
		values=new ContentValues();
		values.put("user_name", name);
		values.put("check_id", "0004");
		values.put("check_state", 0);
		values.put("check_times", 0);
		values.put("check_last_day", date);
		SplashActivity.dbHelper.getReadableDatabase().insert("tiny_check", null, values);
		
		values=new ContentValues();
		values.put("user_name", name);
		values.put("check_id", "0005");
		values.put("check_state", 0);
		values.put("check_times", 0);
		values.put("check_last_day", date);
		SplashActivity.dbHelper.getReadableDatabase().insert("tiny_check", null, values);
		
		values=new ContentValues();
		values.put("user_name", name);
		values.put("check_id", "0006");
		values.put("check_state", 0);
		values.put("check_times", 0);
		values.put("check_last_day", date);
		SplashActivity.dbHelper.getReadableDatabase().insert("tiny_check", null, values);
		
		values=new ContentValues();
		values.put("user_name", name);
		values.put("check_id", "0007");
		values.put("check_state", 0);
		values.put("check_times", 0);
		values.put("check_last_day", date);
		SplashActivity.dbHelper.getReadableDatabase().insert("tiny_check", null, values);
		
		values=new ContentValues();
		values.put("user_name", name);
		values.put("check_id", "0008");
		values.put("check_state", 0);
		values.put("check_times", 0);
		values.put("check_last_day", date);
		SplashActivity.dbHelper.getReadableDatabase().insert("tiny_check", null, values);
		
	}
	
	public void getData(String name){
		String sql="select * from tiny_check where user_name='"+name+"'";
		Cursor cursor=SplashActivity.dbHelper.getReadableDatabase().rawQuery(sql, null);
		while(cursor.moveToNext()){
			cids.add(cursor.getString(1));
			css.add(cursor.getInt(2));
			ctss.add(cursor.getInt(3));
			clds.add(cursor.getString(4));
		}
	}

	public LinkedList<String> getCids() {
		return cids;
	}

	public LinkedList<Integer> getCss() {
		return css;
	}

	public LinkedList<Integer> getCtss() {
		return ctss;
	}

	public LinkedList<String> getClds() {
		return clds;
	}
	
	public boolean check(String id){
		
		if(isChecked(id)){
			return false;
		}
		else{
			
			String sql="update tiny_check set check_times="+(getCheckTimes(id)+1)+" "
					+ "where user_name='"+MainActivity.currentUser+"' and check_id='"+id+"'";
			SplashActivity.dbHelper.getReadableDatabase().execSQL(sql);
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			
			String sql1="update tiny_check set check_last_day='"+sdf.format(new Date(System.currentTimeMillis()))+"' "
					+ "where user_name='"+MainActivity.currentUser+"' and check_id='"+id+"'";
			SplashActivity.dbHelper.getReadableDatabase().execSQL(sql1);
			
			return true;
		}
		
	}
	
	public boolean isChecked(String id){
		String date="";
		String sql="select check_last_day from tiny_check where user_name='"+MainActivity.currentUser+"' and check_id='"+id+"'";
		Cursor cursor=SplashActivity.dbHelper.getReadableDatabase().rawQuery(sql, null);
		while(cursor.moveToNext()){
			date=cursor.getString(0);
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date0=sdf.format(new Date(System.currentTimeMillis()));
		return date.equals(date0);
	}
	
	public int getCheckTimes(String id){
		int times=0;
		String sql="select check_times from tiny_check where user_name='"+MainActivity.currentUser+"' and check_id='"+id+"'";
		Cursor cursor=SplashActivity.dbHelper.getReadableDatabase().rawQuery(sql, null);
		while(cursor.moveToNext()){
			times=cursor.getInt(0);
		}
		return times;
	}
	
	public void display(){
		getData(MainActivity.currentUser);
		for(int i=0;i<cids.size();i++){
			System.out.println(";"+cids.get(i)+"   ;"+css.get(i)+"   ;"+ctss.get(i)+"   ;"+clds.get(i));
		}
	}
	
}
