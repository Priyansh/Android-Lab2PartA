package cs.ecl.w12.lab2.part2.shah;

import java.util.Random;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class myLocalService extends Service 
{
	 // Binder given to clients
    private final IBinder mBinder = new LocalBinder();
    // Random number generator
    private final Random mGenerator = new Random();

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        myLocalService getService() {
            // Return this instance of LocalService so clients can call public methods
            return myLocalService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /** method for clients */
    public int getRandomNumber() {
      return mGenerator.nextInt(100);
    }
}
