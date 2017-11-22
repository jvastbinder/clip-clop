package edu.taylor.cse.clipclop;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RemoteViews;

import java.lang.reflect.Array;

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

    /**
     * Shows the notification, or updates a previously shown notification of
     * this type, with the given parameters.
     * @see #cancel(Context)
     */
    public static void notify(final Context context)
    {
        final Resources res = context.getResources();

        RemoteViews contentView= new RemoteViews(context.getPackageName(),R.layout.buffer_layout);
        //contentView.setTextViewText(R.id.title, "Custom notification");
        //contentView.setTextViewText(R.id.text, "This is a custom layout");
        Intent serviceIntent = new Intent(context, BufferDisplayService.class);
//      serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, 0);
//      serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));
        contentView.setRemoteAdapter(R.id.buffer_listView,serviceIntent);


        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context)

                // Set appropriate defaults for the notification light, sound,
                // and vibration.
                .setDefaults(Notification.DEFAULT_ALL)

                .setSmallIcon(R.drawable.ic_stat_buffer_notif)
                .setContentTitle("FIXME") //TODO
                .setContentText("FIXME") //TODO

                // Set the pending intent to be initiated when the user touches
                // the notification.
                .setContentIntent(
                        PendingIntent.getActivity(
                                context,
                                0,
                                new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com")),
                                PendingIntent.FLAG_UPDATE_CURRENT))

                .setCustomBigContentView(contentView)
                .setOngoing(true);

        notify(context, builder.build());
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    private static void notify(final Context context, final Notification notification) {
        final NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            nm.notify(NOTIFICATION_TAG, 0, notification);
        } else {
            nm.notify(NOTIFICATION_TAG.hashCode(), notification);
        }
    }

    /**
     * Cancels any notifications of this type previously shown using
     * {@link #notify(Context, String, int)}.
     */
    @TargetApi(Build.VERSION_CODES.ECLAIR)
    public static void cancel(final Context context) {
        final NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            nm.cancel(NOTIFICATION_TAG, 0);
        } else {
            nm.cancel(NOTIFICATION_TAG.hashCode());
        }
    }
}
