package edu.taylor.cse.clipclop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinkedList<String> buffer = new LinkedList<>();

        buffer.add("David Tomas");
        buffer.add("Adam Bloomenberg");
        buffer.add("Chris Happpner");
        buffer.add("Andrew Hurse");
        buffer.add("John Vasbinder");

        BufferNotif.setContext(this);
        BufferNotif.setBufferContents(buffer);
        BufferNotif.showBigBufferInterface();

    }

}
