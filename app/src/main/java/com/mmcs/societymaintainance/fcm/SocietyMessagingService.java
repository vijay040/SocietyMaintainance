package com.mmcs.societymaintainance.fcm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.mmcs.societymaintainance.R;
import com.mmcs.societymaintainance.activity.BroadcastNotifiActivity;
import com.mmcs.societymaintainance.activity.ComplaintNotificationActivity;
import com.mmcs.societymaintainance.activity.EmergancyAlarmActivity;
import com.mmcs.societymaintainance.activity.SplashActivity;
import com.mmcs.societymaintainance.activity.VisitorNotificationActivity;

public class SocietyMessagingService extends FirebaseMessagingService {

    private static final String TAG = "Society";
    private static Intent intent;
    public static int id = 0;
    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // Check if message contains a notification payload.
        Log.e("**********","******************handle intent**************************");
       /* intent = new Intent(this, VisitorNotificationActivity.class);
        if (remoteMessage.getNotification().getTitle().equalsIgnoreCase("Hi Guest is waiting!"))
            intent = new Intent(this, VisitorNotificationActivity.class);
        else if (remoteMessage.getNotification().getTitle().equalsIgnoreCase("New complain registered!"))
            intent = new Intent(this, ComplaintNotificationActivity.class);
        else if (remoteMessage.getNotification().getTitle().equalsIgnoreCase("ALARM!")) {
            intent = new Intent(this, EmergancyAlarmActivity.class);
            //Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            EmergancyAlarmActivity. r = MediaPlayer.create(this, R.raw.emergency_alarm);
            EmergancyAlarmActivity. r.setLooping(true);
            EmergancyAlarmActivity.r.start();
        }
        else if (remoteMessage.getNotification().getTitle().equalsIgnoreCase("Broadcast Message!"))
            intent = new Intent(this, BroadcastNotifiActivity.class);

        if(remoteMessage.getNotification().getTitle().equalsIgnoreCase("Hi your maid has been CheckedIn!")||
                remoteMessage.getNotification().getTitle().equalsIgnoreCase("Hi your maid has been CheckedOut!"))
            intent = new Intent(this, SplashActivity.class);

        intent.putExtra("NOTIFICATION_VALUE", remoteMessage);
        sendNotification(remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getTitle());*/







        intent = new Intent(this, VisitorNotificationActivity.class);
        if (remoteMessage.getNotification().getTitle().equalsIgnoreCase("Hi Guest is waiting!"))
            intent = new Intent(this, VisitorNotificationActivity.class);
        else if (remoteMessage.getNotification().getTitle().equalsIgnoreCase("New complain registered!"))
            intent = new Intent(this, ComplaintNotificationActivity.class);
        else if (remoteMessage.getNotification().getTitle().equalsIgnoreCase("ALARM!")) {
            intent = new Intent(this, EmergancyAlarmActivity.class);
            //Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            EmergancyAlarmActivity. r = MediaPlayer.create(this, R.raw.emergency_alarm);
            EmergancyAlarmActivity. r.setLooping(true);
            EmergancyAlarmActivity.r.start();
        }
        else if (remoteMessage.getNotification().getTitle().equalsIgnoreCase("Broadcast Message!"))
            intent = new Intent(this, BroadcastNotifiActivity.class);

        if(remoteMessage.getNotification().getTitle().equalsIgnoreCase("Hi your maid has been CheckedIn!")||
                remoteMessage.getNotification().getTitle().equalsIgnoreCase("Hi your maid has been CheckedOut!"))
            intent = new Intent(this, SplashActivity.class);

        intent.putExtra("NOTIFICATION_VALUE", remoteMessage);
       // Intent notificationIntent = new Intent(this, SplashActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        Uri notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager notificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence nameChannel = this.getString(R.string.app_name);
            String descChannel = this.getString(R.string.app_name);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(this.getString(R.string.app_name), nameChannel, importance);
            channel.setDescription(descChannel);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }

        PendingIntent pendingIntent = PendingIntent.getActivity((this), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Create Notification
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, this.getString(R.string.app_name))
                .setChannelId(this.getString(R.string.app_name))
                .setContentTitle(TextUtils.isEmpty(remoteMessage.getNotification().getTitle()) ? getString(R.string.app_name) :remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setTicker(this.getString(R.string.app_name))
                .setSmallIcon(R.drawable.ic)
                .setSound(notificationSound)
                .setLights(Color.RED, 3000, 3000)
                .setVibrate(new long[]{500, 500})
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

     /*   if (result != null) {
            notification.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(result));
        }*/

        assert notificationManager != null;
        notificationManager.notify(100, notification.build());




    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String messageBody, String title) {
        id++;
        long[] pattern = {500,500,500,500,500};
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, id /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setVibrate(pattern)
                .setOngoing(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id /* ID of notification */, notificationBuilder.build());

    }

    @Override
    public void handleIntent(Intent intent) {
        //super.handleIntent(intent);
        Log.e("**********","******************handle intent**************************");

        try {
            if (intent.getExtras() != null) {
                RemoteMessage.Builder builder = new RemoteMessage.Builder("SocietyMessagingService");
                for (String key : intent.getExtras().keySet()) {
                    builder.addData(key, intent.getExtras().get(key).toString());
                }
                onMessageReceived(builder.build());
            } else {
                super.handleIntent(intent);
            }
        } catch (Exception e) {
            super.handleIntent(intent);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       // EmergancyAlarmActivity.r.stop();
    }
}