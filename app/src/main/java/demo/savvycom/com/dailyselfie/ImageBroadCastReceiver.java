package demo.savvycom.com.dailyselfie;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Bui The Nam on 7/22/2015.
 */
public class ImageBroadCastReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        context = context.getApplicationContext();
        Intent intentNoti = new Intent(context, MainActivity.class);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intentNoti, Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        Notification notification = new Notification.Builder(context)
                .setSmallIcon(R.drawable.small_icon)
                .setContentTitle("Daily Selfie")
                .setAutoCancel(true)
                .setContentText("Time for another selfie")
                .setContentIntent(pendingIntent)
                .build();

        notificationManager.notify(0, notification);
    }
}
