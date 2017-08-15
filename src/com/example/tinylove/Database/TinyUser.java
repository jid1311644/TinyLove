package com.example.tinylove.Database;

import com.example.tinylove.Activity.MainActivity;
import com.example.tinylove.Activity.SplashActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v4.widget.SlidingPaneLayout;

public class TinyUser {

	public final static String CREATE_TABLE_TINYUSER=
			"CREATE TABLE `tiny_user` ("
			+ "`user_name` varchar(50) NOT NULL,"
			+ "`user_psw` varchar(50) NOT NULL,"
			+ "`user_sex` int(1) NOT NULL,"
			+ "`you_name` varchar(50) NOT NULL,"
			+ "`you_sex` int(1) NOT NULL,"
			+ "`user_background` varchar(300) NOT NULL,"
			+ "PRIMARY KEY (`user_name`)"
			+ ")";
	
	public String userName;
	public String userPsw;
	public int userSex;	//0Å® 1ÄÐ
	public String youName;
	public int youSex;
	public String mainBackgroundPath;
	
	public boolean login(String name,String psw){
		
		String sql="select user_psw from tiny_user where user_name='"+name+"'";
		Cursor cursor=SplashActivity.dbHelper.getReadableDatabase().rawQuery(sql, null);
		if(cursor.getCount()==0){
			return false;
		}
		else{
			String p="";
			while(cursor.moveToNext()){
				p=cursor.getString(0);
			}
			if(p.equals(psw)){
				userName=name;
				return true;
			}
			else{
				return false;
			}
		}
	}
	
	public boolean isExist(String name){
		String sql="select user_psw from tiny_user where user_name='"+name+"'";
		Cursor cursor=SplashActivity.dbHelper.getReadableDatabase().rawQuery(sql, null);
		if(cursor.getCount()!=0){
			return true;
		}
		return false;
	}
	
	public boolean signUp(){
		String sql="select user_psw from tiny_user where user_name='"+userName+"'";
		Cursor cursor=SplashActivity.dbHelper.getReadableDatabase().rawQuery(sql, null);
		if(cursor.getCount()!=0){
			return false;
		}
		else{
			ContentValues values=new ContentValues();
			values.put("user_name", userName);
			values.put("user_psw", userPsw);
			values.put("user_sex", userSex);
			values.put("you_name", youName);
			values.put("you_sex", youSex);
			values.put("user_background", mainBackgroundPath);
			SplashActivity.dbHelper.getReadableDatabase().insert("tiny_user", null, values);
			return true;
		}
	}
	
	public boolean changePsw(String name,String psw){
		String sql="update tiny_user set user_psw ='"+psw+"' where user_name='"+MainActivity.currentUser+"'";
		SplashActivity.dbHelper.getReadableDatabase().execSQL(sql);
		return true;
	}
	
	public int[] getSexs(String name){
		int[] sexs=new int[2];
		
		String sql="select user_sex,you_sex from tiny_user where user_name='"+name+"'";
		Cursor cursor=SplashActivity.dbHelper.getReadableDatabase().rawQuery(sql, null);
		while(cursor.moveToNext()){
			sexs[0]=cursor.getInt(0);
			sexs[1]=cursor.getInt(1);
		}
		
		return sexs;
	}
	
	public String[] getNames(String name){
		String[] ns=new String[2];
		
		String sql="select user_name,you_name from tiny_user where user_name='"+name+"'";
		Cursor cursor=SplashActivity.dbHelper.getReadableDatabase().rawQuery(sql, null);
		while(cursor.moveToNext()){
			ns[0]=cursor.getString(0);
			ns[1]=cursor.getString(1);
		}
		
		return ns;
	}
	
	public void setBackground(String path){
		
		String sql="update tiny_user set user_background ='"+path+"' where user_name='"+MainActivity.currentUser+"'";
		SplashActivity.dbHelper.getReadableDatabase().execSQL(sql);
		
	}
	
	public String getBackground(){
		String path="";
		String sql="select user_background from tiny_user where user_name='"+MainActivity.currentUser+"'";
		Cursor cursor=SplashActivity.dbHelper.getReadableDatabase().rawQuery(sql, null);
		while(cursor.moveToNext()){
			path=cursor.getString(0);
		}
		return path;
	}
	
	public void display(){
		String sql="select * from tiny_user";
		Cursor cursor=SplashActivity.dbHelper.getReadableDatabase().rawQuery(sql, null);
		int count=0;
		while(cursor.moveToNext()){
			System.out.println((count++)+"    ;"+cursor.getString(0)+"    ;"+cursor.getString(1)+"    ;"+cursor.getInt(2)+"    ;"
					+cursor.getString(3)+"    ;"+cursor.getInt(4)+"    ;"+cursor.getString(5));
		}
	}
	
}
