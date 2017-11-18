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
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;
import java.util.LinkedList;
import java.util.Queue;


public class MainActivity extends AppCompatActivity {
int bufferSize = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //create ID so we can update the notification
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
        ClipboardListener cliplistener = new ClipboardListener(getApplicationContext());
        cliplistener.setNumClips(4);


       PasteBuffer paste = new PasteBuffer();
       paste.setContext(getApplicationContext());
       paste.setPasteContent("pasteItem","Copied Item Text 1");
    }

    public void plusButton(View view){
        changeBufferSizeDisplay(++bufferSize);
    }

    public void minusButton(View view){
        changeBufferSizeDisplay(--bufferSize);
    }

    private void changeBufferSizeDisplay(int size)
    {
        //TODO decide min/max buffer size and handle accordingly
        EditText bufferSizeDisplay = (EditText) findViewById(R.id.editText);
        bufferSizeDisplay.setText("" + size);
    }

}
