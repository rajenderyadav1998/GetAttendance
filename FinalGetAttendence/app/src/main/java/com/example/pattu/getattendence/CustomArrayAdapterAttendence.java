package com.example.pattu.getattendence;

/**
 * Created by Pattu on 13-May-17.
 */

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


class CustomArrayAdapterAttendence extends ArrayAdapter<String> {
    private Context context;
    private String[] name;
    private String[] roll;

    CustomArrayAdapterAttendence(Context context, String[] name, String[] roll) {
        super(context, -1, name);
        this.context = context;
        this.name = name;
        this.roll = roll;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_custom_attendence, parent, false);

        TextView name = (TextView) rowView.findViewById(R.id.textView16);
        TextView roll = (TextView) rowView.findViewById(R.id.textView17);

        name.setText(this.name[position]);
        roll.setText(this.roll[position]);

        return rowView;
    }
}
