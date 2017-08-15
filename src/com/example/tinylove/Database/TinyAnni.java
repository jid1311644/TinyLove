package com.example.tinylove.Database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import com.example.tinylove.Activity.AnniActivity;
import com.example.tinylove.Activity.MainActivity;
import com.example.tinylove.Activity.SplashActivity;

import android.content.ContentValues;
import android.database.Cursor;

public class TinyAnni {
	
	public final static String CREATE_TABLE_TINYANNI=
			"CREATE TABLE `tiny_anni` ("
			+ "`user_name` varchar(50) NOT NULL,"
			+ "`anni_id` varchar(10) NOT NULL,"
			+ "`anni_content` varchar(100) NOT NULL,"
			+ "`anni_year` varchar(4) DEFAULT NULL,"
			+ "`anni_month` varchar(2) DEFAULT NULL,"
			+ "`anni_day` varchar(2) DEFAULT NULL,"
			+ "`anni_time_type` int(1) NOT NULL,"
			+ "`anni_color` varchar(2) NOT NULL,"
			+ "`anni_frequent` varchar(4) NOT NULL,"
			+ "`anni_background` varchar(2) DEFAULT NULL,"
			+ "PRIMARY KEY (`user_name`,`anni_id`),"
			+ "FOREIGN KEY (`user_name`) REFERENCES `tiny_user` (`user_name`)"
			+ ")";
	
	public String userName;
	public String anniID;
	public String anniContent;
	public String anniYear;
	public String anniMonth;
	public String anniDay;
	public int anniTimeType;	//0公历 1农历
	public String anniColor;
	public String anniFrequent;
	public String anniBackground;
	
	
	public void init(String name){
		
		ContentValues values=new ContentValues();
		values.put("user_name", name);
		values.put("anni_id", "0001");
		values.put("anni_content", "我们在一起");
		values.put("anni_year", "");
		values.put("anni_month", "");
		values.put("anni_day", "");
		values.put("anni_time_type", 0);
		values.put("anni_color", AnniActivity.ANNI_BACK_TOGETHER);
		values.put("anni_frequent", "无");
		values.put("anni_background", "");
		SplashActivity.dbHelper.getReadableDatabase().insert("tiny_anni", null, values);
		
		values=new ContentValues();
		values.put("user_name", name);
		values.put("anni_id", "0002");
		values.put("anni_content", "TA的生日");
		values.put("anni_year", "");
		values.put("anni_month", "");
		values.put("anni_day", "");
		values.put("anni_time_type", 0);
		values.put("anni_color", AnniActivity.ANNI_BACK_BIRTHDAY);
		values.put("anni_frequent", "每年");
		values.put("anni_background", "");
		SplashActivity.dbHelper.getReadableDatabase().insert("tiny_anni", null, values);
		
		values=new ContentValues();
		values.put("user_name", name);
		values.put("anni_id", "0003");
		values.put("anni_content", "我的生日");
		values.put("anni_year", "");
		values.put("anni_month", "");
		values.put("anni_day", "");
		values.put("anni_time_type", 0);
		values.put("anni_color", AnniActivity.ANNI_BACK_BIRTHDAY);
		values.put("anni_frequent", "每年");
		values.put("anni_background", "");
		SplashActivity.dbHelper.getReadableDatabase().insert("tiny_anni", null, values);
		
		values=new ContentValues();
		values.put("user_name", name);
		values.put("anni_id", "0004");
		values.put("anni_content", "第一次拥抱");
		values.put("anni_year", "");
		values.put("anni_month", "");
		values.put("anni_day", "");
		values.put("anni_time_type", 0);
		values.put("anni_color", AnniActivity.ANNI_BACK_HUG);
		values.put("anni_frequent", "无");
		values.put("anni_background", "");
		SplashActivity.dbHelper.getReadableDatabase().insert("tiny_anni", null, values);
		
		values=new ContentValues();
		values.put("user_name", name);
		values.put("anni_id", "0005");
		values.put("anni_content", "第一次接吻");
		values.put("anni_year", "");
		values.put("anni_month", "");
		values.put("anni_day", "");
		values.put("anni_time_type", 0);
		values.put("anni_color", AnniActivity.ANNI_BACK_TOGETHER);
		values.put("anni_frequent", "无");
		values.put("anni_background", "");
		SplashActivity.dbHelper.getReadableDatabase().insert("tiny_anni", null, values);
		
		values=new ContentValues();
		values.put("user_name", name);
		values.put("anni_id", "0006");
		values.put("anni_content", "第一次一起去旅行");
		values.put("anni_year", "");
		values.put("anni_month", "");
		values.put("anni_day", "");
		values.put("anni_time_type", 0);
		values.put("anni_color", AnniActivity.ANNI_BACK_TRAVEL);
		values.put("anni_frequent", "无");
		values.put("anni_background", "");
		SplashActivity.dbHelper.getReadableDatabase().insert("tiny_anni", null, values);
		
		values=new ContentValues();
		values.put("user_name", name);
		values.put("anni_id", "0007");
		values.put("anni_content", "我们的结婚纪念日");
		values.put("anni_year", "");
		values.put("anni_month", "");
		values.put("anni_day", "");
		values.put("anni_time_type", 0);
		values.put("anni_color", AnniActivity.ANNI_BACK_MARRY);
		values.put("anni_frequent", "每年");
		values.put("anni_background", "");
		SplashActivity.dbHelper.getReadableDatabase().insert("tiny_anni", null, values);
	}
	

	LinkedList<String> ids=new LinkedList<String>();
	LinkedList<String> events=new LinkedList<String>();
	LinkedList<String> days=new LinkedList<String>();
	LinkedList<String> y=new LinkedList<String>();
	LinkedList<String> m=new LinkedList<String>();
	LinkedList<String> d=new LinkedList<String>();
	LinkedList<Integer> back=new LinkedList<Integer>();
	
	public void getData(String name){
		String sql="select * from tiny_anni where user_name='"+name+"'";
		Cursor cursor=SplashActivity.dbHelper.getReadableDatabase().rawQuery(sql, null);
		while(cursor.moveToNext()){
			ids.add(cursor.getString(1));
			events.add(cursor.getString(2));
			String y0=cursor.getString(3);
			String m0=cursor.getString(4);
			String d0=cursor.getString(5);
			y.add(y0);
			m.add(m0);
			d.add(d0);
			back.add(Integer.valueOf(cursor.getString(7)));
			
			if(!y0.equals("")&&!m0.equals("")&&!d0.equals("")){
				if(m0.length()==1)m0="0"+m0;
				if(d0.length()==1)d0="0"+d0;
				String date=y0+"-"+m0+"-"+d0;
				String fre=cursor.getString(8);
				days.add(getDays(date, fre)+"");
			}
			else{
				days.add("9999");
			}
		}
	}
	
	public String getFrequent(String name,String id){
		String sql="select anni_frequent from tiny_anni "
				+ " where user_name='"+name+"' "
				+ " and anni_id='"+id+"' ";
		Cursor cursor=SplashActivity.dbHelper.getReadableDatabase().rawQuery(sql, null);
		String f="";
		while(cursor.moveToNext()){
			f=cursor.getString(0);
		}
		return f;
	}
	
	public String getBackgroundImage(String name,String id){
		String sql="select anni_background from tiny_anni "
				+ " where user_name='"+name+"' "
				+ " and anni_id='"+id+"' ";
		Cursor cursor=SplashActivity.dbHelper.getReadableDatabase().rawQuery(sql, null);
		String f="";
		while(cursor.moveToNext()){
			f=cursor.getString(0);
		}
		return f;
	}
	
	public LinkedList<String> getIds() {
		return ids;
	}

	public void setIds(LinkedList<String> ids) {
		this.ids = ids;
	}
	
	public LinkedList<String> getEvents() {
		return events;
	}

	public void setEvents(LinkedList<String> events) {
		this.events = events;
	}

	public LinkedList<String> getDays() {
		return days;
	}

	public void setDays(LinkedList<String> days) {
		this.days = days;
	}

	public LinkedList<String> getY() {
		return y;
	}

	public void setY(LinkedList<String> y) {
		this.y = y;
	}

	public LinkedList<String> getM() {
		return m;
	}

	public void setM(LinkedList<String> m) {
		this.m = m;
	}

	public LinkedList<String> getD() {
		return d;
	}

	public void setD(LinkedList<String> d) {
		this.d = d;
	}

	public LinkedList<Integer> getBack() {
		return back;
	}

	public void setBack(LinkedList<Integer> back) {
		this.back = back;
	}
	
	public int getLastID(){
		String sql="select anni_id from tiny_anni";
		Cursor cursor=SplashActivity.dbHelper.getReadableDatabase().rawQuery(sql, null);
		String id="";
		while(cursor.moveToNext()){
			id=cursor.getString(0);
		}
		return Integer.valueOf(id);
	}
	
	public int getDays(String date,String frequent){
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date lastday=sdf.parse(date);
			Date today=sdf.parse(sdf.format(new Date()));
			if(frequent.equals("无")){
				int d=daysBetween(lastday, today);
				if(d<0)d=-d;
				return d;
			}
			else if(frequent.equals("每周")){
				int weekOfD1=dayForWeek(lastday);
				int weekOfD2=dayForWeek(today);
				if(weekOfD2>weekOfD1){
					return weekOfD1-weekOfD2+7;
				}
				else{
					return weekOfD1-weekOfD2;
				}
			}
			else if(frequent.equals("每月")){
				int dL=lastday.getDate();
				int dT=today.getDate();
				if(dT<=dL){
					return dL-dT;
				}
				else{
					lastday.setMonth(today.getMonth()+1);
					return daysBetween(today, lastday);
				}
			}
			else if(frequent.equals("每年")){
				lastday.setYear(today.getYear());
				int d=daysBetween(today, lastday);
				if(d>0){
					return d;
				}
				else{
					lastday.setYear(today.getYear()+1);
					return daysBetween(today, lastday);
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public int getDays(String name){
		
		String sql=" select anni_year,anni_month,anni_day from tiny_anni "
				+ " where user_name='"+name+"' "
				+ " and anni_id='0001' ";
		Cursor cursor=SplashActivity.dbHelper.getReadableDatabase().rawQuery(sql, null);
		String date="";
		while(cursor.moveToNext()){
			if(!cursor.getString(0).equals("")&&!cursor.getString(1).equals("")&&!cursor.getString(2).equals("")){
				date=cursor.getString(0)+"-"+cursor.getString(1)+"-"+cursor.getString(2);
			}
		}
		if(!date.equals("")){
			return getDays(date,"无");
		}
		return 0;
		
	}

	public String getTargetDate(String date,String frequent){
		
		String[] weeks={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date lastday=sdf.parse(date);
			Date today=sdf.parse(sdf.format(new Date()));
			if(frequent.equals("无")){
				return date+"  "+weeks[dayForWeek(lastday)];
			}
			else if(frequent.equals("每周")){
				int weekOfD1=dayForWeek(lastday);
				int weekOfD2=dayForWeek(today);
				if(weekOfD2>weekOfD1){
					today.setDate(today.getDate()+weekOfD1-weekOfD2+7);
					return sdf.format(today)+"  "+weeks[dayForWeek(lastday)];
				}
				else{
					today.setDate(today.getDate()+weekOfD1-weekOfD2);
					return sdf.format(today)+"  "+weeks[dayForWeek(lastday)];
				}
			}
			else if(frequent.equals("每月")){
				int dL=lastday.getDate();
				int dT=today.getDate();
				if(dT<=dL){
					lastday.setMonth(today.getMonth());
					return sdf.format(lastday)+"  "+weeks[dayForWeek(lastday)];
				}
				else{
					lastday.setMonth(today.getMonth()+1);
					return sdf.format(lastday)+"  "+weeks[dayForWeek(lastday)];
				}
			}
			else if(frequent.equals("每年")){
				lastday.setYear(today.getYear());
				int d=daysBetween(today, lastday);
				if(d>0){
					return sdf.format(lastday)+"  "+weeks[dayForWeek(lastday)];
				}
				else{
					lastday.setYear(today.getYear()+1);
					return sdf.format(lastday)+"  "+weeks[dayForWeek(lastday)];
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String getAnniContent(String id){
		String c="";
		
		String sql="select anni_content from tiny_anni  where user_name='"+MainActivity.currentUser+"' and anni_id='"+id+"'";
		Cursor cursor=SplashActivity.dbHelper.getReadableDatabase().rawQuery(sql, null);
		while(cursor.moveToNext()){
			c=cursor.getString(0);
		}
		
		return c;
	}
	
	public String getAnniDate(String id){
		String c="";
		
		String sql="select anni_year,anni_month,anni_day from tiny_anni  where user_name='"+MainActivity.currentUser+"' and anni_id='"+id+"'";
		Cursor cursor=SplashActivity.dbHelper.getReadableDatabase().rawQuery(sql, null);
		while(cursor.moveToNext()){
			c=cursor.getString(0)+"-"+cursor.getString(1)+"-"+cursor.getString(2);
		}
		
		return c;
	}
	
	public int getBackground(String id){
		int i=0;
		
		String sql="select anni_background from tiny_anni  where user_name='"+MainActivity.currentUser+"' and anni_id='"+id+"'";
		Cursor cursor=SplashActivity.dbHelper.getReadableDatabase().rawQuery(sql, null);
		while(cursor.moveToNext()){
			i=Integer.valueOf(cursor.getString(0));
		}
		return i;
		
	}
	
	public boolean setBackground(int b,String id){
		
		String sql="update tiny_anni set anni_background='"+b+"' where user_name='"+MainActivity.currentUser+"' and anni_id='"+id+"'";
		SplashActivity.dbHelper.getReadableDatabase().execSQL(sql);
		return true;
	}
	
	public int daysBetween(Date smdate,Date bdate){
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        try {
			smdate=sdf.parse(sdf.format(smdate));
	        bdate=sdf.parse(sdf.format(bdate)); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
        return Integer.parseInt(String.valueOf(between_days));
	}
	
	public int dayForWeek(Date date){
		int[] weekOfDays = {0, 1, 2, 3, 4, 5, 6};        
	    Calendar calendar = Calendar.getInstance();      
	    if(date != null){        
	         calendar.setTime(date);      
	    }        
	    int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;      
	    if (w < 0){        
	        w = 0;      
	    }      
	    return weekOfDays[w];
	}
	
	public boolean addSystemAnni(){
		String[] sql=new String[5];

		sql[0]="update tiny_anni set anni_year='"+anniYear+"' where user_name='"+MainActivity.currentUser+"' and anni_id='"+anniID+"'";
		sql[1]="update tiny_anni set anni_month='"+anniMonth+"' where user_name='"+MainActivity.currentUser+"' and anni_id='"+anniID+"'";
		sql[2]="update tiny_anni set anni_day='"+anniDay+"' where user_name='"+MainActivity.currentUser+"' and anni_id='"+anniID+"'";
		sql[3]="update tiny_anni set anni_color='"+anniColor+"' where user_name='"+MainActivity.currentUser+"' and anni_id='"+anniID+"'";
		sql[4]="update tiny_anni set anni_background='"+anniBackground+"' where user_name='"+MainActivity.currentUser+"' and anni_id='"+anniID+"'";
		
		for(int i=0;i<5;i++){
			SplashActivity.dbHelper.getReadableDatabase().execSQL(sql[i]);
		}
		
		return true;
	}
	
	public boolean updateSystemAnni(){
		
		String[] sql=new String[4];

		sql[0]="update tiny_anni set anni_year='"+anniYear+"' where user_name='"+MainActivity.currentUser+"' and anni_id='"+anniID+"'";
		sql[1]="update tiny_anni set anni_month='"+anniMonth+"' where user_name='"+MainActivity.currentUser+"' and anni_id='"+anniID+"'";
		sql[2]="update tiny_anni set anni_day='"+anniDay+"' where user_name='"+MainActivity.currentUser+"' and anni_id='"+anniID+"'";
		sql[3]="update tiny_anni set anni_color='"+anniColor+"' where user_name='"+MainActivity.currentUser+"' and anni_id='"+anniID+"'";
		
		for(int i=0;i<4;i++){
			SplashActivity.dbHelper.getReadableDatabase().execSQL(sql[i]);
		}
		
		return true;
		
	}
	
	public boolean addAnni(){
		
		ContentValues values=new ContentValues();
		values.put("user_name", userName);
		values.put("anni_id", anniID);
		values.put("anni_content", anniContent);
		values.put("anni_year", anniYear);
		values.put("anni_month", anniMonth);
		values.put("anni_day", anniDay);
		values.put("anni_time_type", 0);
		values.put("anni_color", anniColor);
		values.put("anni_frequent", anniFrequent);
		values.put("anni_background", anniBackground);
		SplashActivity.dbHelper.getReadableDatabase().insert("tiny_anni", null, values);
		
		return true;
	}
	
	public boolean updateAnni(){
		
		String[] sql=new String[6];

		sql[0]="update tiny_anni set anni_year='"+anniYear+"' where user_name='"+MainActivity.currentUser+"' and anni_id='"+anniID+"'";
		sql[1]="update tiny_anni set anni_month='"+anniMonth+"' where user_name='"+MainActivity.currentUser+"' and anni_id='"+anniID+"'";
		sql[2]="update tiny_anni set anni_day='"+anniDay+"' where user_name='"+MainActivity.currentUser+"' and anni_id='"+anniID+"'";
		sql[3]="update tiny_anni set anni_color='"+anniColor+"' where user_name='"+MainActivity.currentUser+"' and anni_id='"+anniID+"'";
		sql[4]="update tiny_anni set anni_content='"+anniContent+"' where user_name='"+MainActivity.currentUser+"' and anni_id='"+anniID+"'";
		sql[5]="update tiny_anni set anni_frequent='"+anniFrequent+"' where user_name='"+MainActivity.currentUser+"' and anni_id='"+anniID+"'";
		
		for(int i=0;i<6;i++){
			SplashActivity.dbHelper.getReadableDatabase().execSQL(sql[i]);
		}
		
		return true;
		
	}
	
	public boolean deleteSystemAnni(String id){
		
		String[] sql=new String[5];
		int a;
		if((a=Integer.valueOf(id))<=6){
			sql[0]="update tiny_anni set anni_year='' where user_name='"+MainActivity.currentUser+"' and anni_id='"+id+"'";
			sql[1]="update tiny_anni set anni_month='' where user_name='"+MainActivity.currentUser+"' and anni_id='"+id+"'";
			sql[2]="update tiny_anni set anni_day='' where user_name='"+MainActivity.currentUser+"' and anni_id='"+id+"'";
			sql[3]="update tiny_anni set anni_color='"+(a+6)+"' where user_name='"+MainActivity.currentUser+"' and anni_id='"+id+"'";
			sql[4]="update tiny_anni set anni_background='' where user_name='"+MainActivity.currentUser+"' and anni_id='"+id+"'";
			for(int i=0;i<5;i++){
				SplashActivity.dbHelper.getReadableDatabase().execSQL(sql[i]);
			}
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public boolean deleteAnni(String id){
		int a;
		if((a=Integer.valueOf(id))>6){
			String sql="delete from tiny_anni where user_name='"+MainActivity.currentUser+"' and anni_id='"+id+"'";
			SplashActivity.dbHelper.getReadableDatabase().execSQL(sql);
			return true;
		}
		else{
			return false;
		}
	}
	
	public void display(){
		String sql="select * from tiny_anni";
		Cursor cursor=SplashActivity.dbHelper.getReadableDatabase().rawQuery(sql, null);
		int count=0;
		while(cursor.moveToNext()){
			System.out.println((count++)+"    ;"+cursor.getString(0)+"    ;"+cursor.getString(1)+"    ;"+cursor.getString(2)+"    ;"
					+cursor.getString(3)+"    ;"+cursor.getInt(4)+"    ;"+cursor.getString(5)+"    ;"+cursor.getInt(6)
					+"    ;"+cursor.getString(7)+"    ;"+cursor.getString(8)+"   ;"+cursor.getString(9));
		}
	}

}
