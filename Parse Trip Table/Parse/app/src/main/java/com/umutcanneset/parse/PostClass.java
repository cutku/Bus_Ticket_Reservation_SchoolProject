package com.umutcanneset.parse;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class PostClass extends ArrayAdapter<String> {

    private final ArrayList<String> from;
    private final ArrayList<String> where;
    private final ArrayList<Date> date;
    private final Activity context;

    public PostClass(ArrayList<String> from, ArrayList<String> where, ArrayList<Date> date, Activity context){

        super(context,R.layout.custom_view,from);
        this.from=from;
        this.where=where;
        this.date=date;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater=this.context.getLayoutInflater();
        View customView=layoutInflater.inflate(R.layout.custom_view,null,true);
        TextView fromText=customView.findViewById(R.id.custom_view_from_text);
        TextView whereText=customView.findViewById(R.id.custom_view_where_text);
        TextView dateText=customView.findViewById(R.id.custom_view_date_text);

        fromText.setText(from.get(position));
        whereText.setText(from.get(position));
        dateText.setText(from.get(position));


        return customView;
    }
}
