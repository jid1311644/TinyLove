package com.example.tinylove.Database;

import java.util.LinkedList;

import com.example.tinylove.Activity.SplashActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v4.widget.SlidingPaneLayout;

public class TinyTimePicture {

	public final static String CREATE_TABLE_TINYTIME=
			"CREATE TABLE `tiny_time` ("
			+ "`user_name` varchar(50) NOT NULL,"
			+ "`time_picture` varchar(300) NOT NULL,"
			+ "PRIMARY KEY (`user_name`,`time_picture`),"
			+ "FOREIGN KEY (`user_name`) REFERENCES `tiny_user` (`user_name`)"
			+ ")";
	
	public String userName;
	public String timePicture;
	
	LinkedList<String> pathPictures=new LinkedList<String>();;
	
	public void getData(String name){
		String sql="select time_picture from tiny_time where user_name='"+name+"'";
		Cursor cursor=SplashActivity.dbHelper.getReadableDatabase().rawQuery(sql, null);
		while(cursor.moveToNext()){
			pathPictures.add(cursor.getString(0));
		}
	}
	
	public LinkedList<String> getPictures(){
		return pathPictures;
	}
	
	public void addPath(String name,String path){
		ContentValues values=new ContentValues();
		values.put("user_name", name);
		values.put("time_picture", path);
		SplashActivity.dbHelper.getReadableDatabase().insert("tiny_time", null, values);
	}
	
	public void deletePath(String name,String path){
		String sql="delete from tiny_time where user_name='"+name+"' and time_picture='"+path+"'";
		SplashActivity.dbHelper.getReadableDatabase().execSQL(sql);
	}
	
}
