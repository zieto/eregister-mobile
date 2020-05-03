package com.umk.diary.schedules;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.umk.diary.R;

public class CustomListViewSchedule extends ArrayAdapter<String> {

    private String[] start;
    private String[] end;
    private String[] subject;
    private String[] room;
    private Activity context;

    public CustomListViewSchedule(Activity context, String[] start, String[] end, String[] subject, String[] room){
        super(context, R.layout.listview_layout_schedule,start);

        this.context = context;
        this.start = start;
        this.end = end;
        this.subject = subject;
        this.room = room;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View r = convertView;
        ViewHolder viewHolder = null;
        if(r == null){

            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.listview_layout_schedule,null,true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);

        }
        else {
            viewHolder = (ViewHolder)r.getTag();

        }
        viewHolder.tvw1.setText(start[position]);
        viewHolder.tvw2.setText(end[position]);
        viewHolder.tvw3.setText(subject[position]);
        viewHolder.tvw4.setText(room[position]);

        return r;
    }
    class ViewHolder{
        TextView tvw1;
        TextView tvw2;
        TextView tvw3;
        TextView tvw4;
        ViewHolder(View v){
            tvw1 = v.findViewById(R.id.start);
            tvw2 = v.findViewById(R.id.end);
            tvw3 = v.findViewById(R.id.subject);
            tvw4 = v.findViewById(R.id.room);
        }

    }

}
