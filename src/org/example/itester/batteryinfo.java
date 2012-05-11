package org.example.itester;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class batteryinfo extends Activity{

	private TextView batTxt;
	
	private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
		@Override
		public void onReceive(Context arg0, Intent intent) {
			String action = intent.getAction();
			if (Intent.ACTION_BATTERY_CHANGED.equals(action)){
			
				int status = intent.getIntExtra("status", 0);
				int health = intent.getIntExtra("health", 1);
//				boolean present = intent.getBooleanExtra("present", false);
				int level = intent.getIntExtra("level", 0);
				int scale = intent.getIntExtra("scale", 0);
				int plugged = intent.getIntExtra("plugged", 0);
				int voltage = intent.getIntExtra("voltage", 0);
				int temperature = intent.getIntExtra("temperature", 0);
				String technology = intent.getStringExtra("technology");
		      
				String statusString = "unknown";
			          
			switch (status) {
				case BatteryManager.BATTERY_STATUS_UNKNOWN:
					statusString = "unknown";
					break;
				case BatteryManager.BATTERY_STATUS_CHARGING:
					statusString = "charging";
					break;
				case BatteryManager.BATTERY_STATUS_DISCHARGING:
					statusString = "discharging";
					break;
				case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
					statusString = "not charging";
					break;
				case BatteryManager.BATTERY_STATUS_FULL:
					statusString = "full";
					break;
			}
			        
			String healthString = "unknown";
			        
			switch (health) {
				case BatteryManager.BATTERY_HEALTH_UNKNOWN:
					healthString = "unknown";
					break;
				case BatteryManager.BATTERY_HEALTH_GOOD:
					healthString = "good";
					break;
				case BatteryManager.BATTERY_HEALTH_OVERHEAT:
					healthString = "overheat";
					break;
				case BatteryManager.BATTERY_HEALTH_DEAD:
					healthString = "dead";
					break;
				case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
					healthString = "voltage";
					break;
				case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
					healthString = "unspecified failure";
					break;
			}
			        
			String acString = "Unknown";
			        
			switch (plugged) {
				case BatteryManager.BATTERY_PLUGGED_AC:
					acString = "plugged ac";
					break;
				case BatteryManager.BATTERY_PLUGGED_USB:
					acString = "plugged usb";
					break;
			}
			batTxt.setTextColor(Color.BLUE);
			batTxt.setTextSize(38);
			batTxt.setText(	  "Status: " + statusString +
							  "\nLevel: " + String.valueOf(level) + "%" +
						      "\nHealth: " + healthString +
//			        		  "\nPresent: " + String.valueOf(present)+
			        		  "\nScale: " + String.valueOf(scale)+
			        		  "\nPlugged: " + acString +
			        		  "\nVoltage: " + String.valueOf(voltage) + "mV" +
			        		  "\nTemperature: " + String.valueOf(temperature) + "¢J" +
			        		  "\nTechnology: " + technology);			       
			}
	}
};
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.batteryinfo);
        batTxt = (TextView)findViewById(R.id.battery_info);
        registerReceiver(mBatInfoReceiver, 
        		new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

	}
	
	@Override
	protected void onResume() {
		super.onResume();
        registerReceiver(mBatInfoReceiver, 
        		new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
	  }

	  @Override
	  protected void onPause() {
	    super.onPause();
	    unregisterReceiver(mBatInfoReceiver);
	  }
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
