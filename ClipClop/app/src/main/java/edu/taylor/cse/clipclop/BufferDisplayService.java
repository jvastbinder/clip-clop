package edu.taylor.cse.clipclop;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Andrew on 11/22/17.
 */
public class BufferDisplayService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new BufferDisplayFactory(this.getApplicationContext(), intent);
    }
}

