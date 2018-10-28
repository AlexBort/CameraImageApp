package com.example.camera_image;

import android.annotation.SuppressLint;
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

import com.example.camera_image.activity.MainActivity;

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
        timer.schedule(timerTask, 0, 60000);
    }

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Log.e(TAG, "run: " + " check");
            notification();
        }
    };

    public NotificationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void notification() {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);

        @SuppressLint("WrongConstant") PendingIntent pendingIntent =
                PendingIntent.getActivity(getBaseContext(), 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
        Context context = getApplicationContext();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);


        Notification notification = builder.
                setContentTitle("Image Notification").
                setContentText("Need to take a new picture").
                setTicker("New Message Alert").
                setAutoCancel(true).
                setSmallIcon(R.mipmap.ic_launcher_round).
                setContentIntent(pendingIntent).build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }


}
