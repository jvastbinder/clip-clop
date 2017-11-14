//package edu.taylor.cse.clipclop;
//
//import android.app.Notification;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.ClipData;
//import android.content.ClipboardManager;
//import android.content.Context;
//import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.v4.app.TaskStackBuilder;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//import java.util.Collection;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.Queue;
//
//public class clipboardListener{
//    ClipboardManager mClipboard;
//    ClipData currentClipData;
//    String convertedClipData;
//    Queue<String> clipQueue = new LinkedList<String>();
//    ClipboardManager.OnPrimaryClipChangedListener primaryChangeListener =
//            new ClipboardManager.OnPrimaryClipChangedListener() {
//                @Override
//                public void onPrimaryClipChanged() {
//                    currentClipData = mClipboard.getPrimaryClip();
//                    convertedClipData = currentClipData.toString();
//                    clipQueue.add(convertedClipData);
//
//                }
//            };
//
//
//
//}
