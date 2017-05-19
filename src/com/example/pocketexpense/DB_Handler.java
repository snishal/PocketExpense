package com.example.pocketexpense;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import android.content.ContentValues;
import android.content.Context;

public class DB_Handler extends SQLiteOpenHelper{

	private static final int db_version=1;
	private static final String db_name="Database.db";
	public static final String table_budget="Budget";
	public static final String column_name="name";
	public static final String column_username="username";
	public static final String column_amount="amount";
	public static final String column_password="password";
	public static final String column_date="date";
	public static final String column_expenses="expense";
	public static final String column_personal="Personal";
	public static final String column_sharing="Sharing";	
	
	public DB_Handler(Context context, String name, CursorFactory factory, int version) {
		super(context, db_name, factory, db_version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String query="create table "+table_budget+"("+
						column_name +" text, "+
						column_username+" text, "+
						column_password+" text, "+
						column_amount+" integer"+
						");";
		db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists" + table_budget);
		onCreate(db);
	}
	
	public boolean checkitem(String table,String field,String value){
		SQLiteDatabase db=getWritableDatabase();
		Cursor c=db.rawQuery("select * from "+table+" where "+field+"='"+value+"';", null);
		if(c.getCount()<=0){
			c.close();
			return false;
		}
		c.close();
		return true;
	}
	
	public boolean addUser(User u){
		if(checkitem(table_budget,column_username,u.getUsername())){
			return false;
		}
		ContentValues c=new ContentValues();
		c.put(column_name, u.getName());
		c.put(column_username, u.getUsername());
		c.put(column_password, u.getPassword());
		c.put(column_amount, u.getAmount());
		SQLiteDatabase db=getWritableDatabase();
		db.insert(table_budget, null, c);
		String query="create table "+u.getUsername()+"("+
				column_date +" text, "+
				column_personal+" integer,"+
				column_sharing+" integer,"+
				column_expenses+" integer"+
				");";
		db.execSQL(query);
		return true;
	}

	public boolean verifyUser(String username,String password){
		SQLiteDatabase db=getWritableDatabase();
		String query="select * from "+table_budget+" where "+column_username+"='"+username+"' and "+column_password+"='"+password+"';";
		Cursor c=db.rawQuery(query,null);
		if(c.getCount()<=0){
			c.close();
			return false;
		}
		c.close();
		return true;
	}
	
	public String getName(String username){
		String name;
		String query="select * from "+table_budget+" where "+column_username+"='"+username+"';";
		SQLiteDatabase db=getWritableDatabase();
		Cursor c=db.rawQuery(query, null);
		c.moveToFirst();
		name=c.getString(c.getColumnIndex(column_name));
		c.close();
		return name;
	}
	
	public void addExpense(String username,Expense e){
		if(checkitem(username,column_date,e.getDate())){
			SQLiteDatabase db=getWritableDatabase();
			String query="select * from "+username+" where "+column_date+"='"+e.getDate()+"';";
			Cursor c=db.rawQuery(query,null);
			c.moveToFirst();
			ContentValues content=new ContentValues();
			content.put(column_expenses, c.getInt(c.getColumnIndex(column_expenses))+e.getExpense());
			if(e.getType().equals(column_personal)){
				content.put(column_personal, c.getInt(c.getColumnIndex(column_personal))+e.getExpense());
			}
			else{
				content.put(column_sharing, c.getInt(c.getColumnIndex(column_sharing))+e.getExpense());
			}
			String where=column_date+"=?";
			String[] whereArgs=new String[]{e.getDate()};
			db.update(username, content, where, whereArgs);
		}
		else{
			ContentValues content=new ContentValues();
			content.put(column_expenses, e.getExpense());
			if(e.getType().equals(column_personal)){
				content.put(column_personal, e.getExpense());
				content.put(column_sharing, 0);
			}
			else{
				content.put(column_sharing, e.getExpense());
				content.put(column_personal, 0);
			}
			content.put(column_date, e.getDate());
			SQLiteDatabase db=getWritableDatabase();
			db.insert(username, null,content);
		}
	}
	
	public int inhand(String username){
		int inhand;
		SQLiteDatabase db=getWritableDatabase();
		String q1="select * from "+table_budget+" where "+column_username+"='"+username+"'";
		String q2="select * from "+username+" where 1;";
		Cursor c=db.rawQuery(q1,null);
		c.moveToFirst();
		inhand=c.getInt(c.getColumnIndex(column_amount));
		c=db.rawQuery(q2,null);
		if(c.moveToFirst())
		do{
			inhand=inhand-c.getInt(c.getColumnIndex(column_expenses));
		}while(c.moveToNext());
		return inhand;
	}
	
	public int personal(String username){
		int inhand=0;
		SQLiteDatabase db=getWritableDatabase();
		String q2="select * from "+username+" where 1;";
		Cursor c=db.rawQuery(q2,null);
		if(c.moveToFirst())
		do{
			inhand=inhand+c.getInt(c.getColumnIndex(column_personal));
		}while(c.moveToNext());
		return inhand;
	}
	
	public int sharing(String username){
		int inhand=0;
		SQLiteDatabase db=getWritableDatabase();
		String q2="select * from "+username+" where 1;";
		Cursor c=db.rawQuery(q2,null);
		if(c.moveToFirst())
		do{
			inhand=inhand+c.getInt(c.getColumnIndex(column_sharing));
		}while(c.moveToNext());
		return inhand;
	}
	
	public String getexpense(String username,String date){
		String exp="";
		SQLiteDatabase db=getWritableDatabase();
		String q2="select * from "+username+" where "+column_date+"='"+date+"';";
		Cursor c=db.rawQuery(q2,null);
		if(c.moveToFirst()){
			exp+="Expense : "+c.getInt(c.getColumnIndex(column_expenses))+"\n"
					+"Personal : "+c.getInt(c.getColumnIndex(column_personal))+"\n"
					+"Sharing : "+c.getInt(c.getColumnIndex(column_sharing));
		}
		else{
			exp="No Such Record!!!!";
		}
		return exp;
	}
	
	public void add_amount(String username,int amount){
		SQLiteDatabase db=getWritableDatabase();
		String query="select * from "+table_budget+" where "+column_username+"='"+username+"';";
		Cursor c=db.rawQuery(query,null);
		c.moveToFirst();
		ContentValues content = new ContentValues();
		content.put(column_amount, c.getInt(c.getColumnIndex(column_amount))+amount);
		Log.d("Amount", ((Integer)amount).toString());
		String where=column_username+"=?";
		String[] whereArgs=new String[]{username};
		db.update(table_budget, content, where, whereArgs);
		
	}
	
}
