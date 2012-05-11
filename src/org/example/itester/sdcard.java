package org.example.itester;

import java.io.*;

import org.example.itester.R;


import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

public class sdcard extends Activity {
	
	String sdpath = "/mnt/sdcard/";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sdcard);
        
        TextView sdres = (TextView)findViewById(R.id.sd_card_result);
        
		switch (Build.VERSION.SDK_INT)
		{
			case  7: // android 2.1
				sdpath = "/sdcard/"; 
				break; 
			case  8: // android 2.2
				sdpath = "/mnt/sdcard/";
				break;
			case  9: // android 2.3
			case 10: // android 2.3.3
				sdpath = "/mnt/sdcard/external_sdcard/";
				break; 
			case 11:  // android 3.0
			case 12:  // android 3.1
			case 13:  // android 3.2
				sdpath = "/mnt/sdcard2/";
				break;
			case 14:  // android 4.0
			case 15:  // android 4.0.3
				sdpath = "/mnt/sdcard/external_sdcard/";
				break;
			default:
				sdpath = "/mnt/sdcard/";  // default SD Path
				break;
		}
		
        Log.d("aNexter", "R/W @ \"" + sdpath + "\" @SDK: "+ Build.VERSION.SDK_INT);
        
        try {
        	if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
        			&& fileExists(sdpath + "sd.flg")) {  
        		File f = new File(sdpath + "sdt.txt");
	        
        		f.createNewFile();
        		FileOutputStream wf = new FileOutputStream(f);
        		wf.write("\nSD_Test_String_from_iTester\nWritten By Tody \n(c) 2012 T-ware Inc.\n".getBytes()); // write string to file...
        		wf.flush();
        		wf.close();
        		f.delete();  // delete file
        	}else{
        		sdres.setTextSize(38);
        		sdres.setTextColor(Color.YELLOW);
        		sdres.setText(" Does SD Card inside of it? ");
        		return;
        	}

        	} catch (FileNotFoundException e) {
        		// TODO Auto-generated catch block
        		sdres.setTextSize(38);
        		sdres.setTextColor(Color.RED);
        		sdres.setText("SD Card Test Fail - File Not Found");
        		return;
        	} catch (IOException e) {
        		// TODO Auto-generated catch block
        		sdres.setTextSize(38);
        		sdres.setTextColor(Color.RED);
        		sdres.setText("SD Card Test Fail - Read Write Fail");
        		return;	
        }
		sdres.setTextSize(38);
		sdres.setTextColor(Color.GREEN);
		sdres.setText("SD Card R/W Test Pass!!!");
	}
	
    public boolean fileExists(String filePath)
    {
    	File f = new File(filePath);
    	return f.exists();
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
