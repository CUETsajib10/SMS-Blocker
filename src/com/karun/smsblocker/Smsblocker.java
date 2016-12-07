package com.karun.smsblocker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class Smsblocker extends BroadcastReceiver {
	SmsMessage[] messages;
    String receivedphonenumber;
    Database mynewdatabase;
    Context context;
    String numberid;
	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle=intent.getExtras();
		if(bundle!=null)
		{
		try{
		Object []pdus=(Object[])bundle.get("pdus");
		messages=new SmsMessage[pdus.length];
		for(int i=0;i<messages.length;i++)
		{
		messages[i]=SmsMessage.createFromPdu((byte[])pdus[i]);
		receivedphonenumber=messages[i].getOriginatingAddress();
		mynewdatabase=new Database(context);
		}
		}
		catch(Exception e)
		{
			Toast.makeText(context,""+e,Toast.LENGTH_LONG).show();
		}
		try{
			numberid=mynewdatabase.checknumber(receivedphonenumber);
            if(numberid.length()>0||numberid.length()!=0)    		
            {	
        		abortBroadcast();
				Toast.makeText(context,"SMS Bolocked From:"+receivedphonenumber,Toast.LENGTH_LONG).show();
				
    		}
    		else
    		{
    			Toast.makeText(context,"SMS Received From:"+receivedphonenumber,Toast.LENGTH_LONG).show();
    		}
			
		}
		  
			catch(Exception e){
				Toast.makeText(context,""+e,Toast.LENGTH_LONG).show();
			}
		}
		}
	
}
		

