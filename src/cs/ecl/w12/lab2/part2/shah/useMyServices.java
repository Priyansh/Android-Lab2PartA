package cs.ecl.w12.lab2.part2.shah;


import cs.ecl.w12.lab2.part2.shah.myLocalService;
import cs.ecl.w12.lab2.part2.shah.myLocalService.LocalBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

 
public class useMyServices extends Activity implements OnClickListener{

	   myLocalService mLocalService;
	   boolean mBound = false;

	   Button btn_RandomNo; // for bound service
	   Button btn_intentService,btn_Services,btn_StopService; //for IntentServices
	   
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btn_intentService = (Button) findViewById(R.id.btnIntent);
		btn_Services = (Button) findViewById(R.id.btnService);
		btn_StopService = (Button) findViewById(R.id.btnStop);
		
		btn_RandomNo = (Button) findViewById(R.id.btnRandomNo);
		
		btn_RandomNo.setOnClickListener(this);
		btn_intentService.setOnClickListener(this);
		btn_Services.setOnClickListener(this);
		btn_StopService.setOnClickListener(this);
		
		
	}
	
	@Override
	protected void onStart()
	{
		super.onStart();
		Intent intent = new Intent(this, myLocalService.class);
		bindService(intent, mConn, Context.BIND_AUTO_CREATE);
		Toast.makeText(this, "onStart: Bind to LocalService", Toast.LENGTH_SHORT).show();
	}
	
	@Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (mBound == true) {
            unbindService(mConn);
            mBound = false;
            Toast.makeText(this, "onStop: Unbind from the service", Toast.LENGTH_SHORT).show();
        }
    }
	
	
	/** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConn = new ServiceConnection() {

        public void onServiceConnected(ComponentName className, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            LocalBinder binder = (LocalBinder) service;
                        
            mLocalService = binder.getService();
            mBound = true;
        }

        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
	
	public void onClick(View obj)
	{
		switch(obj.getId())
		{
			case R.id.btnIntent :
				startService(new Intent(this, Lab2partAActivity.class));
				Toast.makeText(this, "IntentService is started", Toast.LENGTH_LONG).show();
				break;
				
			case R.id.btnService :
				startService(new Intent(this, Lab2Services.class));
				Toast.makeText(this, "Service is started", Toast.LENGTH_LONG).show();
				break;
				
			case R.id.btnStop :
				stopService(new Intent(this, Lab2Services.class));
				Toast.makeText(this, "Service is stop", Toast.LENGTH_LONG).show();
				break;
				
			case R.id.btnRandomNo :
				if (mBound == true) 
				{
		            
		            int num = mLocalService.getRandomNumber();
		            Toast.makeText(this, "Number: " + num, Toast.LENGTH_SHORT).show();
		        }
		
		}
		
	}

	

}

