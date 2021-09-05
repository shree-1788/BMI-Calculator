package com.example.dmce2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class TimeTable extends AppCompatActivity {
    private ImageView tt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        tt=(ImageView)findViewById(R.id.tt);
        tt.setImageResource(R.drawable.timetable);
    }
}