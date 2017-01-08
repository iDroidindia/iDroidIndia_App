package com.example.santosh.idroidindia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by SANTOSH on 8/18/2016.
 */

public class custom_adapter extends ArrayAdapter {

    Context context;
    String[] course_array;
    String[] date_array;
    String[] offer_array;

    custom_adapter(Context c,String[] course,String[] date,String[] offer){
        super(c,R.layout.layout_textview,course);

        this.context=c;

        this.course_array=course;
        this.date_array=date;
        this.offer_array=offer;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.layout_textview,parent,false);

        TextView tv1 = (TextView) row.findViewById(R.id.course);
        TextView tv2 = (TextView) row.findViewById(R.id.date);
        TextView tv3 = (TextView) row.findViewById(R.id.offer);

        tv1.setText(course_array[position]);
        tv2.setText(date_array[position]);
        tv3.setText(offer_array[position]);
        return row;
    }
}
