package com.pranay.mynotification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic("alert");

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {


                            return;
                        }
                        Log.i("token",task.toString());
                    }
                });





    }

//
//    private Broadcastreceiver broadcastreceiver=new Broadcastreceiver()
//    {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            super.onReceive(context, intent);
//            String title=intent.getStringExtra("title");
//            String body=intent.getStringExtra("body");
//            String image=intent.getStringExtra("imgurl");
//            try {
//
//                (MainActivity.this).runOnUiThread(new Runnable() {
//                    @Override
//                    public void run()
//                    {
//
//                    }
//                });
//            }catch (Exception e)
//            {
//
//            }
//
//
//        }
//    }



}
