package edu.taylor.cse.clipclop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.view.View;

import java.util.LinkedList;



public class MainActivity extends AppCompatActivity {
int bufferSize = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinkedList<String> buffer;
        EditText bufferSizeDisplay = (EditText) findViewById(R.id.editText);
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                EditText bufferSizeDisplay = (EditText) findViewById(R.id.editText);
                bufferSize = Integer.parseInt(bufferSizeDisplay.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        bufferSizeDisplay.addTextChangedListener(watcher);

        //Add copied string to clipboard by initializing listener
        ClipboardListener cliplistener = new ClipboardListener(getApplicationContext());
        cliplistener.setNumClips(4);
        buffer=(LinkedList<String>) cliplistener.getClipQueue();

        buffer.add("Test string");

        BufferNotif.setContext(getApplicationContext());
        BufferNotif.setBufferContents(buffer);
        BufferNotif.showBigBufferInterface();

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
