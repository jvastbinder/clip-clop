package edu.taylor.cse.clipclop;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import java.util.LinkedList;
import java.util.Queue;

import static android.content.Context.CLIPBOARD_SERVICE;

public class ClipboardListener{

    private ClipData clip;
    private String convertedClipData;
    private Queue<String> clipQueue;
    private Context context;
    private int numClips;

    public ClipboardListener(Context c) {
        this.context = c;
        this.clipQueue = null;
        this.clipQueue = new LinkedList<String>();

        final ClipboardManager mClipboard = (ClipboardManager) this.context.getSystemService(CLIPBOARD_SERVICE);
        ClipboardManager.OnPrimaryClipChangedListener primaryChangeListener =
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

                        while (clipQueue.size() > numClips) {
                            clipQueue.poll();
                        }
                    }
                };

    }

    public void setNumClips(int numClips) {
        this.numClips = numClips;
    }

    public Queue<String> getClipQueue(){
        return clipQueue;
    }








}
