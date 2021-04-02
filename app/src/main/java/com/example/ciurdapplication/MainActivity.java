package com.example.ciurdapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText user_name,password;
    private Button logIn,signUp;
    private myDataBase myDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        user_name = (EditText)findViewById(R.id.user_name_id);
        password = (EditText)findViewById(R.id.user_password_id);


        logIn = (Button)findViewById(R.id.login_button_id);
        signUp = (Button)findViewById(R.id.sign_up_id);

        logIn.setOnClickListener(this);
        signUp.setOnClickListener(this);


        myDataBase = new myDataBase(this);
        SQLiteDatabase sqLiteDatabase = myDataBase.getWritableDatabase();

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id==R.id.sign_up_id){
            Intent intent = new Intent(MainActivity.this,sign_up.class);
            startActivity(intent);
        }
        else if (id==R.id.login_button_id){
            String user_name_value = user_name.getText().toString();
            String user_password = password.getText().toString();
            boolean result =  myDataBase.login(user_name_value,user_password);
            if (result==true){
                Toast.makeText(getApplicationContext(),"Login Successfully !",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this,Home.class);
                startActivity(intent);
            }else {
                Toast.makeText(getApplicationContext(),"You need to sign up",Toast.LENGTH_LONG).show();
            }
        }

    }
}