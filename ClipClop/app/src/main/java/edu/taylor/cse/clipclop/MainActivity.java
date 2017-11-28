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

    private ClipData clip;
    private String convertedClipData;
    private ClipboardManager mClipboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BufferNotif.setContext(getApplicationContext());

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
                        BufferData.setSize(1);
                        changeBufferSizeDisplay();
                    }
                    else
                    {
                        BufferData.setSize(Integer.parseInt(text));
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

                        boolean isClipInQueue = false;
                        for (String item: BufferData.data){
                            if (item.matches(convertedClipData)){
                                isClipInQueue = true;
                            }
                        }

                        if(isClipInQueue == false){
                            BufferData.data.add(convertedClipData);
                        }

                        BufferNotif.showBigBufferInterface();
                    }
                });
    }

    public void plusButton(View view){
        BufferData.setSize(BufferData.getSize()+1);
        changeBufferSizeDisplay();
    }

    public void minusButton(View view){
        BufferData.setSize(BufferData.getSize()-1);
        changeBufferSizeDisplay();
    }

    private void changeBufferSizeDisplay()
    {
        //TODO decide min/max buffer size and handle accordingly
        EditText bufferSizeDisplay = (EditText) findViewById(R.id.editText);
        bufferSizeDisplay.setText(String.format("%d",BufferData.getSize()));
    }

}
