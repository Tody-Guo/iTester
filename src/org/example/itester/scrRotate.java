package org.example.itester;

import org.example.itester.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class scrRotate extends Activity{

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrrorate);
        
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
