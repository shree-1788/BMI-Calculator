package com.example.dmce2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class List extends AppCompatActivity implements View.OnClickListener {

    private Button file,timeTable,doubt,site,signout,fetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        fetch = (Button) findViewById(R.id.fetch);
        file = (Button) findViewById(R.id.file_sharing);
        timeTable=(Button) findViewById(R.id.time_table);
        doubt = (Button) findViewById(R.id.query);
        site=(Button) findViewById(R.id.site);
        signout=(Button) findViewById(R.id.signout);
        fetch.setOnClickListener(this);
        signout.setOnClickListener(this);
        file.setOnClickListener(this);
        doubt.setOnClickListener(this);
        timeTable.setOnClickListener(this);
        site.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.file_sharing:
                startActivity(new Intent(List.this, FileSharing.class));
                break;
            case R.id.site:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.dmce.ac.in/"));
                startActivity(intent);
                break;
            case R.id.signout:
                usersignout();
                break;
            case R.id.time_table:
                startActivity(new Intent(List.this,TimeTable.class));
                break;
            case R.id.query:
                startActivity(new Intent(List.this,ContactUs.class));
                break;
            case R.id.fetch:
                startActivity(new Intent(List.this,Reterive.class));
                break;

        }
    }

    private void usersignout() {

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(List.this,MainActivity.class));

    }
}