package com.mmcs.societymaintainance.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
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
        sendNotification(remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getTitle());
    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String messageBody, String title) {
        id++;
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, id /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
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