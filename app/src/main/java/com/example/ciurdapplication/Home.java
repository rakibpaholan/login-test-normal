package com.example.ciurdapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Home extends AppCompatActivity implements View.OnClickListener {
    private Button show_all_user;
    private myDataBase myDataBase;
    private Button delete_button,update_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        show_all_user = (Button)findViewById(R.id.show_user);
        show_all_user.setOnClickListener(this);


        delete_button = (Button)findViewById(R.id.delete_id);
        update_button = (Button)findViewById(R.id.update_data);

        delete_button.setOnClickListener(this);
        update_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id==R.id.show_user){
            Intent intent = new Intent(Home.this,All_users.class);
            startActivity(intent);
        }else if (id==R.id.delete_id){
            Intent intent = new Intent(Home.this,Delete_Activity.class);
            startActivity(intent);
        }else if (id==R.id.update_data){
            Intent intent = new Intent(Home.this,Update_data.class);
            startActivity(intent);
        }

    }
}