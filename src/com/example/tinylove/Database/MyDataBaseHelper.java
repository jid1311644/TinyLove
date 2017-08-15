package com.example.tinylove.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBaseHelper extends SQLiteOpenHelper{

	public MyDataBaseHelper(Context context, String name, int version) {
		super(context, name, null, version);
		// TODO Auto-generated constructor stub
		System.out.println("Create Database!");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(TinyUser.CREATE_TABLE_TINYUSER);
		db.execSQL(TinyAnni.CREATE_TABLE_TINYANNI);
		db.execSQL(TinyWish.CREATE_TABLE_TINYWISH);
		db.execSQL(TinyCheck.CREATE_TABLE_TINYCHECK);
		db.execSQL(TinyTimePicture.CREATE_TABLE_TINYTIME);
		System.out.println("Create Table!");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		System.out.println("onUpdate Called!    "+oldVersion+"->"+newVersion);
	}
	
}
