package edu.taylor.cse.clipclop;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {
    private ClipboardManager mClipboard;
    private ClipData clip;
    private String convertedClipData;
    private Queue<String> clipQueue;
    private int notifyID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //create ID so we can update the notification
        notifyID = 0;
        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.clip)
                .setContentTitle("Buffer Interface")
                .setContentText("This is how the Buffer will be accessed")
                .setOngoing(true);

        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MainActivity.class);

        // The stack builder object will contain an artificial back stack for the started Activity.
        // This ensures that navigating backward from the Activity leads out of your app to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);

        // mNotificationId is a unique integer your app uses to identify the
        // notification. For example, to cancel the notification, you can pass its ID
        // number to NotificationManager.cancel().
        mNotificationManager.notify(0, mBuilder.build());

        //Add copied string to clipboard by initializing listener
        mClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        clip = ClipData.newPlainText(
                "What is on clipboard currently",
                "Add to buffer");
        clipQueue = new LinkedList<String>();

        mClipboard.addPrimaryClipChangedListener(
                new ClipboardManager.OnPrimaryClipChangedListener() {
                    @Override
                    public void onPrimaryClipChanged() {
                        clip = mClipboard.getPrimaryClip();
                        if(   clip == null
                                || clip.getItemCount() == 0
                                || clip.getItemCount() > 0 && clip.getItemAt(0).getText() == null
                                )
                            return;
                        convertedClipData = clip.getItemAt(0).getText().toString();
                        clipQueue.add(convertedClipData);
                        mBuilder.setContentText(clipQueue.poll());
                        mNotificationManager.notify(notifyID, mBuilder.build());
                    }
                });

    }
}
