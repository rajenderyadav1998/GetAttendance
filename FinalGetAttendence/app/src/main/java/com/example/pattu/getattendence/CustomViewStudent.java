package com.example.pattu.getattendence;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomViewStudent extends ArrayAdapter<String> {
    private Context context;
    private String[] name;
    private String[] roll;
    private String[] noteText;
    private Integer[] present;
    private Integer[] absent;
    private Integer[] leave;
    private Integer[] note;

    CustomViewStudent(Context context, String[] name, String[] roll,String[] noteText, Integer[] present, Integer[] absent, Integer[] leave, Integer[] note) {
        super(context, -1, name);
        this.context = context;
        this.name = name;
        this.roll = roll;
        this.noteText = noteText;
        this.present = present;
        this.absent = absent;
        this.leave = leave;
        this.note = note;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_custom_view_student, parent, false);

        TextView name = (TextView) rowView.findViewById(R.id.textView10);
        TextView roll = (TextView) rowView.findViewById(R.id.textView18);
        TextView attend = (TextView) rowView.findViewById(R.id.textView19);

        name.setText(this.name[position]);
        roll.setText(this.roll[position]);

        if (present[position] == 1)
            attend.setText(R.string.present);
        else if (absent[position] == 1)
            attend.setText(R.string.absent);
        else if (leave[position] == 1)
            attend.setText(R.string.leave);
        else if (note[position] == 1)
            attend.setText(noteText[position]);

        return rowView;
    }
}