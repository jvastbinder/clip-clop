package edu.taylor.cse.clipclop;

import android.app.IntentService;
import android.content.Intent;

public class BufferService extends IntentService {

    public BufferService(){
        super("BufferService");
    }

    @Override
    public void onHandleIntent(Intent intent)
    {
        if( intent.getAction() == "edu.taylor.cse.clipclop_display_big_buffer")
        {
            BufferNotif.showBigBufferInterface();
        }
        else if (intent.getAction().contains("edu.taylor.cse.place_in_clipboard"))
        {
            String bufferItem = intent.getStringExtra("pasteItem");
            PasteBuffer paste = new PasteBuffer(getApplicationContext());
            paste.setPasteContent("pasteItem",bufferItem);
            paste.pasteReadyNotification("Item placed in clipboard");
        }
    }
}
