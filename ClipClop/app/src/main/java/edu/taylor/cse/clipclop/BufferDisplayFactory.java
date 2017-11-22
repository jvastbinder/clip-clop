package edu.taylor.cse.clipclop;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.LinkedList;

/**
 * Created by Andrew on 11/22/17.
 */

class BufferDisplayFactory implements RemoteViewsService.RemoteViewsFactory {
    private LinkedList<String> buffer;
    private Context appContext;

    public BufferDisplayFactory(Context appContext, Intent constructorIntent){
        //I really don't know why I need these params, but I know that I need them
        this.appContext=appContext;
    }

    public int getCount(){
        return buffer.size();
    }

    public long getItemId(int position) {
        return position;
    }

    public RemoteViews getLoadingView() {
        return null; //uses the android default loading view
    }


    public RemoteViews getViewAt(int position){
        RemoteViews itemView = new RemoteViews(appContext.getPackageName(),R.layout.buffer_item);
        itemView.setTextViewText(R.id.item_text, buffer.get(position));
        return itemView;
    }

    public int getViewTypeCount(){
        return 1;
    }

    public boolean hasStableIds(){ return true; }

    public void  onCreate() {
        buffer = new LinkedList<>();

        buffer.add("David Tomas");
        buffer.add("Adam Bloomenberg");
        buffer.add("Chris Happpner");
        buffer.add("Andrew Hurse");
        buffer.add("John Vasbinder");

    }
    public void onDataSetChanged(){
        //will eventually need to do something here. Not necessary for testing though
    }

    public void onDestroy(){

    }

}
