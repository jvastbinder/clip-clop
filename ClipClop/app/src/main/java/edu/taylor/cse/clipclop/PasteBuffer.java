package edu.taylor.cse.clipclop;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by krhapner on 11/10/2017.
 */

public class PasteBuffer extends AppCompatActivity {
    Context context = null;
    public PasteBuffer (Context c) {
        this.context = c;

    }
    public void setPasteContent(String label, String text) {
        ClipboardManager clipboard = (ClipboardManager) this.context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(label, text);
        clipboard.setPrimaryClip(clip);
        pasteReadyNotification(text);
    }
    public void pasteReadyNotification(String clipText){
        String pasteBufferMsg = "Ready to paste text: " + clipText;
        Toast pasteBufferNotification = new Toast(this.context);
        pasteBufferNotification.makeText(this.context,pasteBufferMsg,Toast.LENGTH_SHORT).show();

    }
}
