package org.example.itester;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.example.itester.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.widget.TextView;

public class meminfo extends Activity{
	private String strMemSize = "unknown";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meminfo);
        
        TextView v = (TextView)findViewById(R.id.mem_info);
       	String line = null;
       	long memSize = 0;
       	Display display = getWindowManager().getDefaultDisplay();
       	
        try {
        
        	File f = new File("/proc/meminfo");
        	BufferedReader r = new BufferedReader(new InputStreamReader( new FileInputStream( f )),32);
				line = r.readLine();
				 memSize =  Long.parseLong(line.substring(line.indexOf(":")+1,
									line.lastIndexOf("k")).trim())/1024;
        }
		catch (IOException e) {
				return ;
		}
        
        if (memSize >256 && memSize <=512)
        {
        	strMemSize = "512 MB";
        } else if (memSize > 512 && memSize <= 1024){
        	strMemSize = "1 GB";
        }
        
        v.setTextColor(Color.BLUE);
        v.setTextSize(38);
        v.setText("CPU ABI : " +  android.os.Build.CPU_ABI + 
        		"\nMem Size: " + strMemSize +
        		"\nLCD Res: " + display.getWidth() + " x " + display.getHeight());
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
