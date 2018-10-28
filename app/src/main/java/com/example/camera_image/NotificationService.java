package com.example.camera_image;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class NotificationService extends Service {


    private static final String TAG = "NotificationService";

    private Timer timer;

    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();
        timer.schedule(timerTask, 60000, 60000);
    }

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Log.e(TAG, "run: " + " mountain");
            notification();
        }
    };

    public NotificationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void notification() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("NotificationnService");


//        Service service = Service.getService(getBaseContext());
//        List<Rubric> listRubric = service.getRubrics();
//


        Intent intent = new Intent(getBaseContext(), FullScreenNotification.class);
        intent.putExtra(INTENT_KEY, randomQuote);
        intent.putExtra(INTENT_KEY_INT, positionRub);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(getBaseContext(), 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
        Context context = getApplicationContext();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);


        Notification notification = builder.
                setContentTitle(title).
                setContentText(randomQuote).
                setTicker("New Message Alert").
                setAutoCancel(true).
                setSmallIcon(R.drawable.ic_launcher_background).
                setLargeIcon(bm).
                setContentIntent(pendingIntent).build();
//        notification.contentView.setImageViewResource(R.id.icon, R.mipmap.pero_1);


        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }


}
