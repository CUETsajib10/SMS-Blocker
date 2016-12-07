package com.karun.smsblocker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main extends Activity {
	EditText tnumber;
	Button taddnumber;
	Button tviewnumber;
	Database newdatabase;
	String checknumber;
	AudioManager audiomanager;
    Context context;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mylayout);
		tnumber=(EditText)findViewById(R.id.tnumber);
		taddnumber=(Button)findViewById(R.id.taddnumber);
		tviewnumber=(Button)findViewById(R.id.tview);
		try{
		newdatabase=new Database(this);
		}
		catch(Exception e){
			Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();	
		}
	}
	public void addnumber(View view){
		try{
	String phonenumber=tnumber.getText().toString().trim();
    if(phonenumber.length()==0){
			Toast.makeText(getApplicationContext(),"Please enter the Number",Toast.LENGTH_LONG).show();
			InputMethodManager ipmanager=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			ipmanager.hideSoftInputFromWindow(tnumber.getWindowToken(),0);

		}
		else{
				checknumber=newdatabase.checkpresentnumber(phonenumber);
				if(checknumber.length()>0)
	    		{
	    		Toast.makeText(getApplicationContext(),"The number is already exist in BlockList",Toast.LENGTH_LONG).show();
	    		InputMethodManager ipmanager=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				ipmanager.hideSoftInputFromWindow(tnumber.getWindowToken(),0);
	    		}				
	    		else{
	    			if(phonenumber.length()==10)
	    			phonenumber="+91"+phonenumber;
	    			long data=newdatabase.insertnumber(phonenumber);
	if(data>0){
		Toast.makeText(getApplicationContext(),"Number is added to BlockList",Toast.LENGTH_LONG).show();
		tnumber.setText("");
		InputMethodManager ipmanager=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		ipmanager.hideSoftInputFromWindow(tnumber.getWindowToken(),0);
	}
	else{
		Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
	}
	   }
	    		}
		
		}
    catch(Exception e){
    	Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
         }
		}
		
	public void viewnumber(View view){
		try{
	Intent openintent=new Intent("com.karun.smsblocker.CURSORADAPTERVIEW");
	startActivity(openintent);
		}
		catch(Exception e){
			Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();	
		}
	}
}
