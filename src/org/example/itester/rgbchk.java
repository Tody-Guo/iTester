package org.example.itester;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class rgbchk extends Activity implements OnClickListener{
	int id = 0;
	View rgb;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rgbchk);
        
        rgb = findViewById(R.id.rgb_chk);
        rgb.setOnClickListener(this);
        rgb.setBackgroundColor(Color.MAGENTA);
        
	}
	
	
//	@Override
	public void onClick(View v)
	{
		TextView v1 = (TextView)v;
		
		switch(id)
		{
			case 0:
				v1.setText("Red");
				rgb.setBackgroundColor(Color.RED);
				break;
			case 1:
				v1.setText("Green");
				rgb.setBackgroundColor(Color.GREEN);
				break;
			case 2:
				v1.setText("Blue");
				rgb.setBackgroundColor(Color.BLUE);
				break;

			case 3:
				v1.setText("Gray1");
				rgb.setBackgroundColor(Color.DKGRAY);
				break;

			case 4:
				v1.setText("Gray2");
				rgb.setBackgroundColor(Color.GRAY);
				break;

			case 5:
				v1.setText("Gray3");
				rgb.setBackgroundColor(Color.LTGRAY);
				break;
				
			case 6:
				v1.setText("White");
				rgb.setBackgroundColor(Color.WHITE);
				break;
			default:
				v1.setText("LCD Ending Test!!!");
				rgb.setBackgroundColor(Color.BLACK);
				id = -1;
				break;
		}
		id ++;
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



