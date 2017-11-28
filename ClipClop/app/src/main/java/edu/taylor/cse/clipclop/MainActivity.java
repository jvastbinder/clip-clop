package edu.taylor.cse.clipclop;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.view.View;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


public class MainActivity extends AppCompatActivity {
int bufferSize = 5;
    private ClipData clip;
    private String convertedClipData;
    private LinkedList<String> clipQueue;
    private ClipboardManager mClipboard;
    private Iterator<String> clipIterator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BufferNotif.setContext(getApplicationContext());

        final LinkedList<String> buffer;
        EditText bufferSizeDisplay = (EditText) findViewById(R.id.editText);
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                EditText bufferSizeDisplay = (EditText) findViewById(R.id.editText);
                String text = bufferSizeDisplay.getText().toString();
                if(!text.contentEquals(""))
                {
                    if(Integer.parseInt(text) < 1)
                    {
                        changeBufferSizeDisplay(1);
                    }
                    else
                    {
                        bufferSize = Integer.parseInt(text);
                    }
                }
            }
        };

        bufferSizeDisplay.addTextChangedListener(watcher);

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
                        //check to see if copied item is already in buffer
                        clipIterator = clipQueue.iterator();
                        boolean isClipInQueue = false;
                        while(clipIterator.hasNext()){
                            if (clipIterator.next().matches(convertedClipData)){
                                isClipInQueue = true;
                            }
                        }

                        if(isClipInQueue == false){
                            clipQueue.add(convertedClipData);
                        }


                        while (clipQueue.size() > bufferSize) {
                            clipQueue.poll();
                        }

                        BufferNotif.setBufferContents(clipQueue);
                        BufferNotif.showBigBufferInterface();
                    }
                });
    }

    public void plusButton(View view){
        changeBufferSizeDisplay(++bufferSize);
    }

    public void minusButton(View view){
        changeBufferSizeDisplay(--bufferSize);

        while (clipQueue.size() > bufferSize) {
            clipQueue.poll();
        }

        BufferNotif.setBufferContents(clipQueue);
        BufferNotif.showBigBufferInterface();
    }

    private void changeBufferSizeDisplay(int size)
    {
        //TODO decide min/max buffer size and handle accordingly
        EditText bufferSizeDisplay = (EditText) findViewById(R.id.editText);
        bufferSizeDisplay.setText("" + size);
    }

}
