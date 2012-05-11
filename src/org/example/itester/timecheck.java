package org.example.itester;

//import org.example.itester.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.Time;
import android.view.KeyEvent;
import android.widget.TextView;


public class timecheck extends Activity{

    
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timecheck);
        
        handler.postDelayed(runnable, 1000);  //delay 1000ms=1s
        
	}
	
	Handler handler=new Handler();
	Runnable runnable=new Runnable(){
	   public void run() {
	    // TODO Auto-generated method stub
	        TextView timeV = (TextView)findViewById(R.id.timeView);
	        Time tm = new Time();

	        tm.setToNow();  //Get local time

	        int year = tm.year;
	        int mon = tm.month + 1;
	        int day = tm.monthDay;
	        int hour = tm.hour;
	        int min = tm.minute;
	        int sec = tm.second;
	        
	        timeV.setTextSize(39);
	        timeV.setTextColor(Color.BLUE);
	        timeV.setText("Now Time is: \n    "+ year + "-" + mon + "-" 
	        				+ day + " " + hour + ":" + min + ":" + sec);        
	        handler.postDelayed(this, 500); //delay 500ms=0.5s
	   }
	};

	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) { 
    	if (keyCode == KeyEvent.KEYCODE_BACK 
    			&& event.getRepeatCount() == 0)
    	{
    		finish();
    	}
    	return true;
    }

}