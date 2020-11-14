package com.umk.register.grades;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.umk.register.R;

public class CustomListViewGrades extends ArrayAdapter<String> {

    private String[] desc;
    private String[] date;
    private int[] imgid;
    private Activity context;

    public CustomListViewGrades(Activity context, String[] desc, String[] date, int[] imgid){
        super(context, R.layout.listview_layout,desc);

        this.context = context;
        this.desc = desc;
        this.date = date;
        this.imgid = imgid;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View r = convertView;
        ViewHolder viewHolder = null;
        if(r == null){

            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.listview_layout,null,true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);

        }
        else {
            viewHolder = (ViewHolder)r.getTag();

        }
        viewHolder.ivw.setImageResource(imgid[position]);
        viewHolder.tvw1.setText(desc[position]);
        viewHolder.tvw2.setText(date[position]);

        return r;
    }
    class ViewHolder{
        TextView tvw1;
        TextView tvw2;
        ImageView ivw;
        ViewHolder(View v){
            tvw1 = v.findViewById(R.id.tvdesc);
            tvw2 = v.findViewById(R.id.tvdate);
            ivw = v.findViewById(R.id.imageView);
        }

    }

}