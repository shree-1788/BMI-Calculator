package com.example.dmce2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Reterive extends AppCompatActivity {
    ListView listView;
    FirebaseDatabase database;
    ArrayList<String> list;
    DatabaseReference ref;
    ArrayAdapter<String> adapter;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reterive);
        user = new User();
        listView = (ListView) findViewById(R.id.list_view);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference("Users");
        list= new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.list_item,R.id.user_info,list);
//        studentdbRef = FirebaseDatabase.getInstance().getReference("Users");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                userlist.clear();
                for(DataSnapshot studentDataSnap : snapshot.getChildren()){
                     user = studentDataSnap.getValue(User.class);
                    list.add("Name: "+user.getFname().toString() + "    " + "Email: "+user.getEmail().toString());
                }
                listView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}