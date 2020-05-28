package com.umk.register;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class CustomListViewNotes extends ArrayAdapter<String> {

    private String[] desc;
    private String[] date;
    private String[] teacher;
    private int[] imgid;
    private Activity context;

    public CustomListViewNotes(Activity context, String[] desc, String[] date, String[] teacher, int[] imgid){
        super(context, R.layout.listview_layout,desc);

        this.context = context;
        this.desc = desc;
        this.date = date;
        this.teacher = teacher;
        this.imgid = imgid;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View r = convertView;
        ViewHolder viewHolder = null;
        if(r == null){

            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.listview_layout_notes,null,true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);

        }
        else {
            viewHolder = (ViewHolder)r.getTag();

        }
        viewHolder.ivw.setImageResource(imgid[position]);
        viewHolder.tvw1.setText(desc[position]);
        viewHolder.tvw2.setText(date[position]);
        viewHolder.tvw3.setText(teacher[position]);

        return r;
    }
    class ViewHolder{
        TextView tvw1;
        TextView tvw2;
        TextView tvw3;
        ImageView ivw;
        ViewHolder(View v){
            tvw1 = v.findViewById(R.id.tvdesc);
            tvw2 = v.findViewById(R.id.tvdate);
            tvw3 = v.findViewById(R.id.tvteacher);
            ivw = v.findViewById(R.id.imageView);
        }

    }

}