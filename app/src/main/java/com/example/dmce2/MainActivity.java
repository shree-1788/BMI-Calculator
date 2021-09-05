package com.example.dmce2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView register,forgot_pass;
    private Button login;
    private EditText email,password;
    private String mail,pass;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (TextView) findViewById(R.id.register);
    register.setOnClickListener(this);

    forgot_pass = (TextView) findViewById(R.id.forget_pass);
    forgot_pass.setOnClickListener(this);

    login=(Button)findViewById(R.id.login);
    login.setOnClickListener(this);

    email=(EditText)findViewById(R.id.mail);
    password=(EditText)findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.register:
                startActivity(new Intent(MainActivity.this,Register.class));
                break;
            case R.id.login:
                userLogin();
                break;
            case R.id.forget_pass:
                startActivity(new Intent(MainActivity.this,ForgetPassword.class));
                break;


        }
    }

    private void userLogin() {
        mail=email.getText().toString().trim();
        pass=password.getText().toString().trim();

        if(mail.isEmpty()) {
            email.setError("Email required");
            email.requestFocus();
            return;
        }
        if(pass.isEmpty())
        {
            password.setError("Password Required");
            password.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches())
        {
            email.setError("Enter Valid email");
            email.requestFocus();
            return;
        }
        if(pass.length()<6)
        {
            password.setError("Length short");
            password.requestFocus();
            return;
        }
mAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful())
        {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if(user.isEmailVerified()) {
//                Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, List.class));
            }
        else
            {
                user.sendEmailVerification();
                Toast.makeText(MainActivity.this, "Check your email to verify your account", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(MainActivity.this, "Wrong credentials try again!", Toast.LENGTH_SHORT).show();
        }
    }
});

    }


}