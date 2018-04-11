package com.example.bb.weather14.pushnotification;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import com.example.bb.weather14.data.dto.NotificationDTO;
import com.example.bb.weather14.screen.main.MainActivity;
import com.example.bb.weather14.utils.AppUtils;
import com.example.bb.weather14.utils.NotificationUtils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import java.util.Map;


/**
 * Created by administrator on 4/9/18.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMessagingService";

    @SuppressLint("LongLogTag")
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            try {
                handleDataMessage(remoteMessage);
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    private void handleDataMessage(RemoteMessage remoteMessage) {
        if (AppUtils.isForeGround(getApplicationContext())) {
            //Do nothing
        } else {
            Map<String, String> data = remoteMessage.getData();
            NotificationDTO notificationDTO = new NotificationDTO(data.get("temp"), data.get("location"), data.get("description"));
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0,
                    intent, 0);
            NotificationUtils.showNotification(getApplicationContext(), notificationDTO.getTemp() + notificationDTO.getDescription(), notificationDTO.getLocation(), pendingIntent);
        }
    }

}
