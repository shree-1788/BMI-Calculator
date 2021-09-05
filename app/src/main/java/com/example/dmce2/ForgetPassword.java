package com.example.dmce2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {

    private EditText mail;
    private Button reset;
    private FirebaseAuth au;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        mail = (EditText) findViewById(R.id.email3);
        reset = (Button) findViewById(R.id.reset);
        au=FirebaseAuth.getInstance();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetpass();
            }
        });
    }

    private void resetpass() {
        String email = mail.getText().toString().trim();

        if(email.isEmpty())
        {
            mail.setError("Fill the email");
            mail.requestFocus();
            return ;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            mail.setError("Enter valid email");
            mail.requestFocus();
            return;
        }
        au.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(ForgetPassword.this, "Check your email to reset password", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ForgetPassword.this,MainActivity.class));
                }
                else
                {
                    Toast.makeText(ForgetPassword.this, "Something went wrong try again!!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}