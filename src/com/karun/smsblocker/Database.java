package com.karun.smsblocker;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Database{
	private static final String DATABASE_NAME="mydata";
	private static final String TABLE_NAME="mytable";
	static final String ID="_id";
	static final String NUMBER="number";
	private static final int DATABASE_VERSION=76;
	Numberbase mydata;
	SQLiteDatabase db;
	long data;
	Context context;
	public Database(Context context){
		mydata=new Numberbase(context);
		db=mydata.getWritableDatabase();
		}
	
public class Numberbase extends SQLiteOpenHelper {
private Context context;
public Numberbase(Context context){
	super(context,DATABASE_NAME,null,DATABASE_VERSION);
	this.context=context;
}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NUMBER+" VARCHAR);");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS"+" "+TABLE_NAME);
		onCreate(db);
	}

}
public long insertnumber(String phonenumber){
	db=mydata.getWritableDatabase();
	ContentValues contentvalues=new ContentValues();
	contentvalues.put(NUMBER,phonenumber);
	data=db.insert(TABLE_NAME,null,contentvalues);
	return data;
}
public Cursor getallnumbers(){
	String[] columns={Database.ID,Database.NUMBER};
	db=mydata.getWritableDatabase();
	Cursor mycursor=db.query(TABLE_NAME,columns, null, null, null, null, null);
	return mycursor;
}
public String checknumber(String receivedphonenumber)
{
	db=mydata.getWritableDatabase();
	String[] columns={Database.ID};
	String selectionArgs[]={receivedphonenumber};
	Cursor cursor=db.query(Database.TABLE_NAME, columns,Database.NUMBER +"=?",selectionArgs,null, null, null,null);
	StringBuffer buffer=new StringBuffer();
	while(cursor.moveToNext())
	{
		int index0=cursor.getColumnIndex(Database.ID);
		int mynumber=cursor.getInt(index0);
		buffer.append(mynumber);
	}
	return buffer.toString();
}
public String checkpresentnumber(String phonenumber)
{
	db=mydata.getWritableDatabase();
	String[] columns={Database.ID};
	String[] selectionArgs={phonenumber};
	Cursor cursor=db.query(Database.TABLE_NAME, columns,Database.NUMBER +"=?",selectionArgs,null, null, null,null);
	StringBuffer buffer=new StringBuffer();
	while(cursor.moveToNext())
	{
		int index0=cursor.getColumnIndex(Database.ID);
		int checknumber=cursor.getInt(index0);
		buffer.append(checknumber);
	}
	return buffer.toString();
}
public void deletenumber(String selected) {
	try{
	db=mydata.getWritableDatabase();
	String newselected[]={selected};
	db.delete(TABLE_NAME,NUMBER +"=?",newselected);
	}
	catch(Exception e){
		Toast.makeText(context,""+e,Toast.LENGTH_LONG).show();
	}
}
public void updatenumber(String Selected,String editnumber) {
	db=mydata.getWritableDatabase();
	ContentValues contentvalues=new ContentValues();
	contentvalues.put(Database.NUMBER,editnumber);
	String whereArgs[]={Selected};
	db.update(Database.TABLE_NAME,contentvalues,Database.NUMBER +"=?",whereArgs);
}
}