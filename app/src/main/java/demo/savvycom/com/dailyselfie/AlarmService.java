//package demo.savvycom.com.dailyselfie;
//
//import android.app.AlarmManager;
//import android.app.IntentService;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.Intent;
//
///**
// * Created by Bui The Nam on 7/23/2015.
// */
//public class AlarmService extends IntentService {
//
//    AlarmManager mAlarmManager;
//    public static final int REQUEST_NOTIFICATION = 0;
//    /**
//     * Creates an IntentService.  Invoked by your subclass's constructor.
//     *
//     * @param name Used to name the worker thread, important only for debugging.
//     */
//    public AlarmService(String name) {
//        super("AlarmSelfieService");
//
//    }
//
//    @Override
//    protected void onHandleIntent(Intent intent) {
//        String action = intent.getStringExtra("action");
//
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), REQUEST_NOTIFICATION, new Intent("com.savvycom.DAILYSELFIE"), Intent.FLAG_ACTIVITY_NEW_TASK);
//        mAlarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
//        mAlarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, );
//    }
//
//
//}
