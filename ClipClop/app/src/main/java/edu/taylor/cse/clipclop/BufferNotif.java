package edu.taylor.cse.clipclop;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import java.util.LinkedList;

/**
 * Helper class for showing and canceling buffer notif
 * notifications.
 * <p>
 * This class makes heavy use of the {@link NotificationCompat.Builder} helper
 * class to create notifications in a backward-compatible way.
 */
public class BufferNotif {
    /**
     * The unique identifier for this type of notification.
     */
    private static final String NOTIFICATION_TAG = "BufferNotif";
//    private static LinkedList<String> buffer;
    private static Context context;
    private static int lastBufferSizeShown=0;

    /**
     * Shows the notification, or updates a previously shown notification of
     * this type, with the given parameters.
     * @see #cancel(Context)
     */

 //   public static void setBufferContents(LinkedList<String> bufferList)
  //  {
  //      buffer=bufferList;
 //   }

    public static void setContext(Context cxt)
    {
        context=cxt;
    }

    public static void showSmallBufferInterface()
    {
        final Resources res = context.getResources();
        Intent expandBufferIntent = new Intent(context, BufferService.class);
        expandBufferIntent.setAction("edu.taylor.cse.clipclop_display_big_buffer");

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context)

                // Set appropriate defaults for the notification light, sound,
                // and vibration.
                .setCategory(Notification.CATEGORY_SERVICE)

                .setSmallIcon(R.drawable.ic_stat_buffer_notif)
                .setContentText(String.format("%d items ready to paste", BufferData.getSize()))
                .setContentTitle("Clip Buffer")
                .setGroup("buffer")
                .setGroupSummary(true)


                // Set the pending intent to be initiated when the user touches the notification.
                .setContentIntent(
                        PendingIntent.getService(context, 0, expandBufferIntent,0))

                .setOngoing(true);

        notify( builder.build(),0);
    }

    public static void showBigBufferInterface()
    {
        for (int cancelId=BufferData.getSize(); cancelId <= lastBufferSizeShown; cancelId++)
            cancel(cancelId);

        lastBufferSizeShown=1;
        int notifId=1;
        final Resources res = context.getResources();
        for (String item: BufferData.data)
        {
            Intent pasteIntent = new Intent(context, BufferService.class);
            pasteIntent.setAction("edu.taylor.cse.place_in_clipboard-"+item)
                    .putExtra("pasteItem", item);

 //           Intent deleteIntent = new Intent(context, BufferService.class);
 //           pasteIntent.setAction("edu.taylor.cse.delete_from_queue-"+item)
 //                   .putExtra("deleteItem", item);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                    .setCategory(Notification.CATEGORY_SERVICE)
                    //.setShowWhen(false) //when is still showed :(
                    .setSmallIcon(R.drawable.ic_stat_buffer_notif)
                    .setContentIntent(
                        PendingIntent.getService(context,
                                0,
                                pasteIntent,
                                PendingIntent.FLAG_CANCEL_CURRENT))
                    .setOngoing(true)
                  //  .setGroup("buffer")
                 //   .setDeleteIntent(
                   //         PendingIntent.getService(context,
                     //               0,
                    //        deleteIntent,
                    //        PendingIntent.FLAG_CANCEL_CURRENT)

                   // )
                    .setContentTitle(item.substring(0, Math.min(item.length(), 50)));

            notify(builder.build(),notifId);
            notifId+=1;
            lastBufferSizeShown+=1;
       }

    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    private static void notify(final Notification notification, int notifId) {
        final NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            nm.notify(NOTIFICATION_TAG, notifId, notification);
        } else {
            nm.notify(NOTIFICATION_TAG.hashCode(), notification);
        }
    }

    /**
     * Cancels any notifications of this type
     */
    @TargetApi(Build.VERSION_CODES.ECLAIR)
    public static void cancel(int notifId) {
        final NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            nm.cancel(NOTIFICATION_TAG, notifId);
        } else {
            nm.cancel(NOTIFICATION_TAG.hashCode());
        }
    }
}
