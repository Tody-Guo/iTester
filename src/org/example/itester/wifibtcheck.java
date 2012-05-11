package org.example.itester;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class wifibtcheck extends Activity{
	private static final int REQUEST_ENABLE_BT = 3;
	private WifiManager mWifi;
	private BluetoothAdapter bAdapt;
	private String btMac, WifiMac;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wifibtcheck);

        mWifi = (WifiManager)getSystemService(Context.WIFI_SERVICE);

        if (!mWifi.isWifiEnabled())
        {
        	mWifi.setWifiEnabled(true);
        }
        
        WifiInfo wifiInfo = mWifi.getConnectionInfo();
        
        bAdapt= BluetoothAdapter.getDefaultAdapter();
       
        if (bAdapt != null)
        {
            if (!bAdapt.isEnabled())
            {
                Intent enBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enBT, REQUEST_ENABLE_BT);
            }
            
            btMac = bAdapt.getAddress();
        }else{
        	btMac = "No Bluetooth Device!";
        }
        
        if((WifiMac = wifiInfo.getMacAddress())== null)
        {
        	WifiMac = "No Wifi Device";
        }
      
        TextView mac = (TextView)findViewById(R.id.macView);
        mac.setTextSize(38);
        mac.setText("Wi-Fi MAC:  "+ WifiMac + "\nBlueTooth:  " + btMac);

        
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    switch (requestCode) {

	    case REQUEST_ENABLE_BT:
	        // When the request to enable Bluetooth returns
	        if (resultCode == Activity.RESULT_OK) {
	            // Bluetooth is now enabled, so set up a chat session
	        } else {
	            finish();
	        }
	    }
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




