package com.example.pattu.getattendence;

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

/**
 * Created by Pattu on 07-May-17.
 */

class CustomArrayAdapter extends ArrayAdapter<String> {
    private Context context;
    private String[] name;
    private Integer[] id1;

    CustomArrayAdapter(Context context, String[] name, Integer[] id1) {
        super(context, -1, name);
        this.context = context;
        this.name = name;
        this.id1 = id1;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_list, parent, false);

        TextView name = (TextView) rowView.findViewById(R.id.textView);
        ImageView se = (ImageView) rowView.findViewById(R.id.dot);
        name.setText(this.name[position]);
        final String test = this.name[position];
        se.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context ,test, Toast.LENGTH_LONG).show();



            }
        });

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, StudentDetails.class);
                i.putExtra("id", id1[position].toString());
                context.startActivity(i);
            }
        });

        return rowView;
    }
}