package org.example.itester;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.os.Vibrator;
import android.provider.Settings;
import android.content.*;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.KeyEvent;
//import android.widget.AbsListView;
//import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ITester extends Activity {
	 private static final String TAG = "iTester 2";
	 private String release = android.os.Build.DISPLAY;

//	 public static ArrayList<Map<String, Boolean>> isSelected = new ArrayList<Map<String, Boolean>>();;  
//	 private Map<String,Boolean> item;

//	 private ArrayList isChecked = new ArrayList();
//	 private boolean isPlayed = false;
	 private ListView listview;
	 
	 private String mMCU_version;
	 private String [] eMMC_strSplit =  new String[20];
	 private Float eMMC_Total;
	 private MediaPlayer mediaplay = null;
	 private String [] testItem = null;
	 
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);   
        
        testItem = getResources().getStringArray(R.array.TestTitle);
/*      for(int i=0; i< testItem.length; i++)
       	{
       		Log.e(TAG, "isChecked reloaded..... from iTester...");
       		isChecked.add(false);
       	}
*/        
        listview = new ListView(this);
        listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked,
        											testItem));
        
        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        
        listview.setOnItemClickListener(new OnItemClickListener(){
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id){ 
        		startItemTest(position);  /*  --- here Now to start test --- */
//        		isChecked.set(position, true);
//        		Log.e(TAG, isChecked.toString());
        		listview.setItemChecked(position, true);
        	}
        });
        
        setContentView(listview);   /* Now showing.... */
        
/*        
        listview.setOnScrollListener(new OnScrollListener(){
        	public void onScroll(AbsListView absListView, int firstVisibleItem, 
        			int visibleItemCount, int totalItemCount)
        	{
        		for (int i=0; i< isChecked.size(); i++)
        		{
        			if (isChecked.get(i).equals("true"))
        			{
        				listview.setItemChecked(i, true);
        			}
        		}
        	}

			public void onScrollStateChanged(AbsListView view, int scrollState) {
        		for (int i=0; i< isChecked.size(); i++)
        		{
        			if (isChecked.get(i).equals("true"))
        			{
        				listview.setItemChecked(i, true);
        			}
        		}				
			}
        });
*/
    }   
    
    public void startItemTest(int id)
    {
    	switch(id)
    	{
    		case 0:
    			startActivity(new Intent(this, timecheck.class));
    			Log.d(TAG, "Check Time now");
    			break;
    			
    		case 1:
    			Log.d(TAG, "Reading Battery information");
    			startActivity(new Intent(this, batteryinfo.class));
    		break;
    			
    		case 2:
    			Log.d(TAG, "G-Sensor Test");
    			startActivity(new Intent(this, scrRotate.class));
    			break;
    			
    		case 3:
    			try{
    				Log.d(TAG, "USB to LAN Test");
    				Intent i = new Intent();
    				i.setClassName("com.android.settings", "com.android.settings.EthernetSettings");
    				startActivity(i);
    			}catch(ActivityNotFoundException e)
    			{
    				Toast.makeText(getApplicationContext(),
        					"以太网连接设置打开失败",
        					Toast.LENGTH_SHORT)
        					.show();
        			return ;
    			}
    			break;

    		case 4:
    			Log.d(TAG, "SD Card Read/Wirte Test");
    			startActivity(new Intent(this, sdcard.class));
    			break;
    			
    		case 5:
    			try{
    				Intent i = new Intent();
   					i.setClassName("com.oem.iFileManager", "com.oem.iFileManager.iFileManager");
    		    	startActivity(i);
    			}catch(ActivityNotFoundException e)
    			{
    				Toast.makeText(getApplicationContext(),
        					"文件管理器打开失败",
        					Toast.LENGTH_SHORT)
        					.show();
    				return ;
    			}    			
    			break;
    			
    		case 6:
    			Log.d(TAG, "Display brightness Setting");
    			startActivity(new Intent(Settings.ACTION_DISPLAY_SETTINGS));
    			break;

    		case 7:
    			startActivity(new Intent(this, PointerLocation.class));
    			break;
    			
    		case 8:
    			Log.d(TAG, "Input Keyboard Test");
    			Intent i= new Intent();
    			i.setClassName("com.android.quicksearchbox", "com.android.quicksearchbox.SearchActivity");
    			startActivity(i);
    			break;
    			
    		case 9:
    			Log.d(TAG, "Wifi Test");
    			startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));

    			break;
    			
    		case 10:
    			Log.d(TAG, "Vibrator Test");
    			long [] pattern = {100, 400, 100, 400};    
    			Vibrator iVibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
    	        iVibrator.vibrate(pattern, -1); 
				break;

    		case 11:
    			try{
    				i = new Intent();		
//    				if(Build.VERSION.RELEASE.startsWith("2.3"))
//    				{
//    					i.setClassName("com.fb.FileBrower", "com.fb.FileBrower.FileBrower");    					
//    				}else
    				{	
    					i.setClassName("com.oem.iFileManager", "com.oem.iFileManager.iFileManager");
    				}
    				startActivity(i);
    			}catch(ActivityNotFoundException e)
    			{
    				Toast.makeText(getApplicationContext(),
        					"文件管理器打开失败",
        					Toast.LENGTH_SHORT)
        					.show();
    				return ;
    			}
    			break;

    		case 12:
    			try{
    				i= new Intent();
   					i.setClassName("com.oem.iFileManager", "com.oem.iFileManager.iFileManager");
    				startActivity(i);
    			}catch(ActivityNotFoundException e)
    			{
    				Toast.makeText(getApplicationContext(),
        					"文件管理器打开失败",
        					Toast.LENGTH_SHORT)
        					.show();
    				return ;
    			}
    			break;
    			
    		case 13:
    			Log.d(TAG, "SoundRecord Test");
    			try{
    				i = new Intent();
    				i.setClassName("com.android.soundrecorder", "com.android.soundrecorder.SoundRecorder");
    				startActivity(i);

    			}catch(ActivityNotFoundException e)
    			{
    				Toast.makeText(getApplicationContext(),
        					"录音程式打开失败",
        					Toast.LENGTH_SHORT)
        					.show();
        			return ;
    			}
    			break;

    		case 14:
    			Log.d(TAG, "RGB Test");
    			startActivity(new Intent(this, rgbchk.class));
    			break;
 
    		case 15:
    			Log.d(TAG, "Camera Test");
    			try{
    				i = new Intent();
   					i.setAction("android.media.action.VIDEO_CAMERA");
    		    	startActivity(i);
    			}catch(ActivityNotFoundException e)
    			{
    				Toast.makeText(getApplicationContext(),
        					"摄像头程式打开失败",
        					Toast.LENGTH_SHORT)
        					.show();
    				return ;
    			}
    			break;
    			
    		case 16: // bug fixed, cannot search Bluetooth devices...
    			Log.d(TAG, "Bluetooth Test");
    			final BluetoothAdapter bAdapt = BluetoothAdapter.getDefaultAdapter();
    			if (bAdapt == null)
    			{
    				Toast.makeText(getApplicationContext(), "No Bluetooth Device", 
    							Toast.LENGTH_SHORT).show();
    				break ; 
    			}
    			try{
    				i = new Intent();
    				i.setClassName("com.android.IvtBluetooth", "com.android.IvtBluetooth.IVTBluetooth");
    				startActivity(i);
			
    			}catch(ActivityNotFoundException e)
    			{
    				try{
    					Log.d(TAG, "No IVT Devices found, and now starting default Bluetooth Settings");
    					startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
    				}catch(ActivityNotFoundException e1)
    				{
    					Toast.makeText(getApplicationContext(), "No Bluetooth", Toast.LENGTH_SHORT).show();
    				}
    			}
    			break;

    		case 17:
    			Log.d(TAG, "Memory information");
    			startActivity(new Intent(this, meminfo.class));
    			break;

    		case 18:
    			Log.d(TAG, "eMMC storage information");
   		       	File path = Environment.getDataDirectory();   
   		       	StatFs stat = new StatFs(path.getPath());   
   		       	
   		       	long blockSize = stat.getBlockSize();   
   		       	long aBlocks = stat.getAvailableBlocks(); 
   		       	
   		       	float aSize = (float)blockSize * aBlocks/(1024*1024*1024);
    		    try
    		    {		       	
   		       		FileReader emmc_fr = new FileReader("/proc/partitions");
   		       		BufferedReader emmc_br = new BufferedReader(emmc_fr);
   		       		String eMMC_str = emmc_br.readLine();
   		       		eMMC_str = emmc_br.readLine();
   		       		eMMC_str = emmc_br.readLine();  /** start at 3th line */
   		       		eMMC_strSplit = eMMC_str.split(" ");

   		       		while (eMMC_strSplit == null || 
   		       			Integer.parseInt(eMMC_strSplit[eMMC_strSplit.length-2]) < 10000)
   		       		{
 	   		       		eMMC_str = emmc_br.readLine();
 	  		       		eMMC_strSplit = eMMC_str.split(" ");
   		       		}

   		       		eMMC_Total = (float) (Float.parseFloat(
   		       				eMMC_strSplit[eMMC_strSplit.length-2]
   		       				)/(1000*1000)*1.024*1.024*1.024);   		       			

    		    }catch (FileNotFoundException e)
    		    {
    		    	eMMC_Total = (float)-1;
    		    } catch (IOException e) {
    		    	eMMC_Total = (float)-2;
				}
   		       	new AlertDialog.Builder(this)
   		       		.setTitle("eMMC Storage Information")
   		       		.setMessage(String.format("全部容量: %.0f GB \n\n可用容量: %.2f GB"
   		       			, eMMC_Total, aSize))
   		       			.setIcon(R.drawable.icon)
   		       			.setPositiveButton("Ok", null)
   		       			.show();
    			break;
 
    		case 19:
    			Log.d(TAG, "Reading WiFi & Bluetooth MAC Address...");
    			startActivity(new Intent(this, wifibtcheck.class));
    		break;
    		
    		case 20:
    		{
    			Log.d(TAG, "Image & MCU Version verification");
    		    try
    		    {
    		      FileReader fr = new FileReader("/proc/shmcu_version");
    		      BufferedReader br = new BufferedReader(fr);
    		      mMCU_version = br.readLine();
    		      br.close();
    		    }
    		    catch (FileNotFoundException e)
    		    {
    		    	mMCU_version = "No MCU File";
    		    } catch (IOException e) {
    		    	mMCU_version = "Read MCU Err";
				}
    		    
    		    new AlertDialog.Builder(this)
    		    .setIcon(R.drawable.icon)
    		    .setTitle("Image & MCU Version")
    		    .setMessage(  "Mode number: " + Build.MODEL
    		    			+ "\n\nAndroid: " + Build.VERSION.RELEASE
    		    			+ "\n\nMCU Version: " + mMCU_version
    		    			+ "\n\nRelease : " + release
    		    			+ "\n\nImage Version: " + System.getProperty("os.version", "Unknown OS"))
    		    .setPositiveButton("Ok", null)
    		    .show();
    		}
    			break;
    			
    		case 21:
    			try{
    				Log.d(TAG, "Compass Test");
    				i= new Intent();
    				i.setClassName("com.apksoftware.compass", "com.apksoftware.compass.Compass");
    				startActivity(i);
    			}catch(ActivityNotFoundException e){
    				try{
    					/* try another COMPASS App. */
    					i= new Intent();
    					i.setClassName("com.netpatia.android.filteredcompass", 
    							"com.netpatia.android.filteredcompass.FilteredCompassActivity");
    					startActivity(i);
    				}catch(ActivityNotFoundException e1){    					
        				Toast.makeText(getApplicationContext(),
            					"指北针程式打开失败",
            					Toast.LENGTH_SHORT)
            					.show();
        				return ;
    				}
    			}
    			break;

    		case 22:
    			Log.d(TAG, "Suspend Test");
    			new AlertDialog.Builder(this)
    				.setIcon(R.drawable.icon)
    				.setTitle("Suspend测试")
    				.setMessage("按电源按钮进行休眠测试，30秒后唤醒！")
//    				.setPositiveButton("Ok", null)
    				.show();
    	        break;

    		case 23:
    			Log.d(TAG, "Gyroscope Test");
    			try{
//    				startActivity(new Intent(this, GyroTest.class));
    				i = new Intent();
    				i.setComponent(new ComponentName("com.shuttle.tp.activity",
    					"com.shuttle.tp.activity.ManualActivity"));
//    				i.putExtra("item_name","GyroTestItem"); 
//    				i.putExtra("cycle_times", 1); 
//    				i.putExtra("spend_time", 1);  
    				startActivity(i);
    			}catch(ActivityNotFoundException e)
    			{

    				Toast.makeText(getApplicationContext(),
        					"Shuttle测试程式打开失败",
        					Toast.LENGTH_SHORT)
        					.show();
        			return ;
    			}
    			break;
    			
    		case 24:
    			Log.d(TAG, "Sensors status...");
    			try{
    				i = new Intent();
    				i.setClassName("com.mtorres.phonetester", "com.mtorres.phonetester.Main");
    				startActivity(i);
    			}catch(ActivityNotFoundException e)
    			{

    				Toast.makeText(getApplicationContext(),
        					"Phone Tester打开失败",
        					Toast.LENGTH_SHORT)
        					.show();
        			return ;
    			}
    			break;
 
    		case 25:
    			Log.d(TAG, "Date Time setting");
    			startActivity(new Intent(Settings.ACTION_DATE_SETTINGS));
    			break;
    
    		case 26:
    			Log.d(TAG, "Sound Setting");
    			startActivity(new Intent(Settings.ACTION_SOUND_SETTINGS));
    			break;
    			
    		case 27:
    			try{
    				Log.d(TAG, "3G Test");
    				i = new Intent();
    				i.setClassName("com.android.phone", "com.android.phone.Settings");
    				startActivity(i);
    			}catch(ActivityNotFoundException e)
    			{
    				Toast.makeText(getApplicationContext(),
        					"3G设置打开失败",
        					Toast.LENGTH_SHORT)
        					.show();
        			return ;
    			}
    			break;
    			
    		case 28:
    			Log.d(TAG, "Open wap.baidu.com");
    			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://wap.baidu.com")));
    	}
    }    
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) { 
    	if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
    	{
    		new AlertDialog.Builder(ITester.this)
    		.setTitle("Exit Tip").setMessage("Are you sure ?")
    		.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
    			public void onClick(DialogInterface dialog, int whichButton)
    			{
    				finish();
    			}
    		})
    		.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){

    			public void onClick(DialogInterface dialog, int whichButton)
    			{
    				dialog.dismiss();
    				Log.d(TAG, "Cancal Exit");
    			}
    		}).show();
    		return true;
    	}
		return false;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 0, 0, "Quit");
        menu.add(0, 1, 1, "Display");
        menu.add(0, 2, 2, "Music");
        menu.add(0, 3, 3, "About");
        
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
    		case 0:
    			Log.d(TAG, "Logout App.");
    			finish();
    			return true;
    		case 1:
    			Log.d(TAG, "Reboot system -- No function");
    			Display display = getWindowManager().getDefaultDisplay();       			
    			new AlertDialog.Builder(this).setIcon(R.drawable.icon)
    				.setTitle("LCD Resolution")
    				.setMessage("Resolution: "+display.getWidth() + " x " + display.getHeight() )
    				.setPositiveButton("Ok", null).show();
    			return false;
    		case 2:
				if (mediaplay == null)
				{
					mediaplay=MediaPlayer.create(this, R.raw.ilu);
				}
				
				if (!mediaplay.isPlaying())
    			{
    				mediaplay.setLooping(false);
    				mediaplay.start();

    				Toast.makeText(getApplicationContext(),
        					"Now Playing...", Toast.LENGTH_SHORT).show();
    			}else{
    				new AlertDialog.Builder(ITester.this)
    	    		.setTitle("Warning").setMessage("Music is playing...")
    	    		.setPositiveButton("Ok", null).show();
    			}
    			return true;
    		case 3:
    			startActivity(new Intent(this, about.class));
    			return true;
        }
        
        return false;
    }
    
    @Override
    public void onDestroy()
    {
    	super.onDestroy();
    	if (mediaplay!=null)
    	{
    		mediaplay.release();
    	}
    }
    
}