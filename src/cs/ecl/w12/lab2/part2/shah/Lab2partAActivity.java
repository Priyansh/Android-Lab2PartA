package cs.ecl.w12.lab2.part2.shah;

//import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
//import android.os.Bundle;

public class Lab2partAActivity extends IntentService {
    /** Called when the activity is first created. */
	
	public Lab2partAActivity()
	{
		
		super("Lab2partAActivity");
	}
	
    @Override
    protected void onHandleIntent(Intent intent) 
    {
		// Normally we would do some work here, like download a file.
		// For our sample, we just sleep for 5 seconds.
		long endTime = System.currentTimeMillis() + 5 * 1000;
		while (System.currentTimeMillis() < endTime) 
		{
			synchronized (this)
			{
				try
				{
					wait(endTime - System.currentTimeMillis());
				} 
				catch (Exception e)
				{
					
				}
			}
		}
	}
}