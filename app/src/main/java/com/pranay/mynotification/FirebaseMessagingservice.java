package com.pranay.mynotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationBuilderWithBuilderAccessor;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.datatransport.runtime.backends.BackendRequest;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Random;

public class FirebaseMessagingservice extends FirebaseMessagingService {



    NotificationCompat.Builder notifiaction;
    Bitmap bitmap;
    String id="Default";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage)
    {

        String title = remoteMessage.getData().get("title");
        String body = remoteMessage.getData().get("body");
        String imgurl = remoteMessage.getData().get("image");


        bitmap=getbitmap(imgurl);



        getnotifiacation(bitmap,title,body,imgurl);
    }

    @Override
    public void onNewToken(String token) {

    }


    private void getnotifiacation(Bitmap bitmap, String title, String body, String imgurl)
    {

        Intent intent=new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        notifiaction=new NotificationCompat.Builder(this,id)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(body)
                .setLargeIcon(getbitmap(imgurl))
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap))
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(id,"notifiaction",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0,notifiaction.build());

    }



    public static Bitmap getbitmap(String imgurl)
    {
        try
        {
            URL url=new URL(imgurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream=connection.getInputStream();
            Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
            return  bitmap;

        }catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }

    }
}
