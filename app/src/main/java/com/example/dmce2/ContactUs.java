package com.example.dmce2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactUs extends AppCompatActivity {
     private EditText msg;
     private Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        msg=(EditText) findViewById(R.id.doubt1);
        send=(Button) findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendemail();
            }
        });
    }

    private void sendemail() {
        String message = msg.getText().toString().trim();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_TEXT,message);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]  {"dmcecomp.miniproject@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Student Feedback: ");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}