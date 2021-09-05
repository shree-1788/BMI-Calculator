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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener{
    private EditText mail2,fullName,password2;
    private Button register;
    private String fName,ma,pass;
   FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mail2=(EditText)findViewById(R.id.email2);
        fullName=(EditText)findViewById(R.id.fullName);
        password2=(EditText)findViewById(R.id.password2);

        register = (Button)findViewById(R.id.register);
        register.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.register:
                reg();
                break;
        }
    }

    private void reg() {
        fName=fullName.getText().toString().trim();
        ma=mail2.getText().toString().trim();
        pass=password2.getText().toString().trim();

        if(fName.isEmpty())
        {
            fullName.setError("Full Name required");
            fullName.requestFocus();
            return;
        }

        if(ma.isEmpty())
        {
            mail2.setError("Mail required");
            mail2.requestFocus();
            return;
        }

        if(pass.isEmpty())
        {
            password2.setError("Password required");
            password2.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(ma).matches())
        {
            mail2.setError("Enter Valid Email");
            mail2.requestFocus();
            return;
        }
        if(pass.length()<6)
        {
            password2.setError("Length short");
            password2.requestFocus();
            return;
        }

mAuth.createUserWithEmailAndPassword(ma,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful())
        {
//            sendUserData();
            User user = new User(fName,ma);
            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(Register.this, "Register Successfully! ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Register.this,MainActivity.class));

                    }
                    else
                    {
                        Toast.makeText(Register.this, "Error in register", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }else{
            Toast.makeText(Register.this, "Error in register", Toast.LENGTH_SHORT).show();
        }

    }
});


    }

//    private void sendUserData() {
//
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//        DatabaseReference reference = firebaseDatabase.getReference("users");
//        User user = new User(fName,ma);
//        reference.setValue(user);
//
//
//
//
//    }
}