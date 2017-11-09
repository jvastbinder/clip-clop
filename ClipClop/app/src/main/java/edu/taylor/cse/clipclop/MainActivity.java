package edu.taylor.cse.clipclop;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final Runnable task = new Runnable() {
//            @Override
//            public void run() {
                BufferNotif notif= new BufferNotif();

                notif.notify(this,"Potato",3);      //code you want to run every second
//            }
//        };

 //       task.run();

    }
}
