package org.example.itester;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class GyroTest extends Activity implements SensorEventListener {

    /** Called when the activity is first created. */
    private static final float NS2S = 1.0f / 1000000000.0f;
    private float timestamp;
    private float[] angle = new float[3];  
    
    SensorManager mSensorManager;
    private Sensor mGYROSCOPE;
    TextView vX;
    TextView vY;
    TextView vZ;
    
    boolean isSet = false;
    float pre_x;
    float pre_y;
    float pre_z;
    boolean tx =false;
    boolean ty =false;
    boolean tz =false;
    boolean isPass = false;
    boolean isGyroExit = false;
    
    public GyroTest()
    {
    	angle[0] = 0;
    	angle[1] = 0;
    	angle[2] = 0;
    	timestamp = 0;
    	
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gyrotest);
        
        vX = (TextView)findViewById(R.id.v_x);
        vY = (TextView)findViewById(R.id.v_y);
        vZ = (TextView)findViewById(R.id.v_z);
        
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mGYROSCOPE = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        List<Sensor> ListSensor = mSensorManager.getSensorList (Sensor.TYPE_ALL);
        for ( int i=0; i< ListSensor.size(); i++)
        {	
        	Sensor sens =  ListSensor.get(i);
        	if (sens.getType() == Sensor.TYPE_GYROSCOPE)
        	{
        		isGyroExit = true;
        		break;
        	}
        }
        
        if (!isGyroExit)
        {
        	vX.setText("No Gyroscope");
        	vX.setBackgroundColor(Color.RED);
        	new AlertDialog.Builder(this).setIcon(R.drawable.icon)
        		.setMessage("Gyro Sensor not Found!")
        		.setPositiveButton("OK", null)
        		.setTitle("Error")
        		.show();
        }
        
    }
    
    public void onSensorChanged(SensorEvent event)
    {
    	if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE)  
        {  
    		return;  
        }
    	
    	if (isPass)
    	{
    		return;
    	}

    	if (timestamp != 0) {
        	final float dT = (event.timestamp - timestamp) * NS2S;
        	angle[0] += event.values[0] * dT * 100;
        	angle[1] += event.values[1] * dT * 100;
        	angle[2] += event.values[2] * dT * 100;
         }
         timestamp = event.timestamp;
         
         if (!isSet)
         {
        	 pre_x = angle[0];
        	 pre_y = angle[1];
        	 pre_z = angle[2];
        	 isSet = true;
         }
         
         if (Math.abs(angle[0])- Math.abs(pre_x) > 130)
         {
        	 tx = true;
        	 vX.setTextColor(Color.GREEN);
         }

         if (Math.abs(angle[1])- Math.abs(pre_y) > 130)
         {
        	 ty = true;
        	 vY.setTextColor(Color.GREEN);
         }
         
         if (Math.abs(angle[2])- Math.abs(pre_z) > 130)
         {
        	 tz = true;
        	 vZ.setTextColor(Color.GREEN);
         }
         
         vX.setText("X: " + Float.toString(angle[0]));
         vY.setText("Y: " + Float.toString(angle[1])); 
         vZ.setText("Z: " + Float.toString(angle[2]));       

         if(tx && ty && tz )
        {
       	 
       	 	vX.setText("Gyroscope Pass");
       	 	vX.setTextSize(38);
       	 	vY.setText("");
       	 	vZ.setText("");
       	 	isPass = true;
        }

    }
	
    @Override
	public void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mGYROSCOPE, SensorManager.SENSOR_DELAY_NORMAL);
    }

    
    @Override
	public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
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