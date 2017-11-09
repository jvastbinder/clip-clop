package edu.taylor.cse.clipclop;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class clipboardListener extends MainActivity{
    ClipboardManager mClipboard;
    ClipData currentClipData;
    String convertedClipData;
    Queue<String> clipQueue = new Queue<String>() {
        @Override
        public boolean add(String s) {
            return false;
        }

        @Override
        public boolean offer(String s) {
            return false;
        }

        @Override
        public String remove() {
            return null;
        }

        @Override
        public String poll() {
            return null;
        }

        @Override
        public String element() {
            return null;
        }

        @Override
        public String peek() {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @NonNull
        @Override
        public Iterator<String> iterator() {
            return null;
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @NonNull
        @Override
        public <T> T[] toArray(@NonNull T[] ts) {
            return null;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(@NonNull Collection<?> collection) {
            return false;
        }

        @Override
        public boolean addAll(@NonNull Collection<? extends String> collection) {
            return false;
        }

        @Override
        public boolean removeAll(@NonNull Collection<?> collection) {
            return false;
        }

        @Override
        public boolean retainAll(@NonNull Collection<?> collection) {
            return false;
        }

        @Override
        public void clear() {

        }
    };
    ClipboardManager.OnPrimaryClipChangedListener primaryChangeListener =
            new ClipboardManager.OnPrimaryClipChangedListener() {
                @Override
                public void onPrimaryClipChanged() {
                    currentClipData = mClipboard.getPrimaryClip();
                    convertedClipData = currentClipData.toString();
                    clipQueue.add(convertedClipData);
                }
            };



}
