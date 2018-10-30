package com.mmcs.societymaintainance.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.mmcs.societymaintainance.R;
import com.mmcs.societymaintainance.activity.DrawerActivity;
import com.mmcs.societymaintainance.activity.VisitorNotificationActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

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
        Log.e("**********", "****************onMessageReceived*******************");
        Log.e("dataChat", remoteMessage.getData() + "");
        Log.e("remoteMessage", "************************" + remoteMessage);
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Message data payload: " + remoteMessage.getData());
        }
        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            Log.e(TAG, "Message getBodyLocalizationKey " + remoteMessage.getNotification().getBodyLocalizationKey());
            Log.e(TAG, "Message getTag " + remoteMessage.getNotification().getTag());
            Log.e(TAG, "Message getBodyLocalizationArgs " + remoteMessage.getNotification().getBodyLocalizationArgs());
            Log.e(TAG, "Message getTitle " + remoteMessage.getNotification().getTitle());

        }

        for (Map.Entry<String, String> entry : remoteMessage.getData().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            Log.e("*****************", "key, " + key + " value " + value);
        }

        String str = remoteMessage.getNotification().getTag();
        try {
            JSONObject o = new JSONObject(str);
            JSONArray a = o.getJSONArray("types");
            for (int i = 0; i < a.length(); i++) {
                Log.d("Type", a.getString(i));
            }
        } catch (Exception e) {
        }
        Log.e("str****************", "" + str);
        intent = new Intent(this, VisitorNotificationActivity.class);
      /*  String body=remoteMessage.getNotification().getBody();
        Log.e("body","body********"+body);
        String strID[]=body.split("#");
        Log.e("strID size"+strID.length,"***********************************id*****"+strID[1]);*/
        if (remoteMessage.getNotification().getTitle().equalsIgnoreCase("Hi Gest is waiting!"))
            intent = new Intent(this, VisitorNotificationActivity.class);

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
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id /* ID of notification */, notificationBuilder.build());

    }

   /* public void handleIntent(Intent intent) {

        Log.e("********************","****************Called*******************");
        try {
            if (intent.getExtras() != null) {
                RemoteMessage.Builder builder = new RemoteMessage.Builder("MyFirebaseMessagingService");
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
    }*/
}