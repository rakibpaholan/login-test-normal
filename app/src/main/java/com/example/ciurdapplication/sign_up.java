package com.example.ciurdapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sign_up extends AppCompatActivity implements View.OnClickListener {
    private EditText user_name, user_email, user_phone,user_pass;
    private Button sing_in_button;
    private UserDetails userDetails;
    private myDataBase myDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setTitle("Sing Up Window");

        user_name = (EditText)findViewById(R.id.user_name);
        user_email = (EditText)findViewById(R.id.email_id);
        user_phone = (EditText)findViewById(R.id.phone_number_id);
        user_pass = (EditText)findViewById(R.id.password);
        sing_in_button = (Button)findViewById(R.id.sign_in_id);
        sing_in_button.setOnClickListener(this);


        userDetails = new UserDetails();
        myDataBase = new myDataBase(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String user_name_value = user_name.getText().toString();
        String user_email_value = user_email.getText().toString();
        String user_phone_value = user_phone.getText().toString();
        String user_pass_value = user_pass.getText().toString();

        userDetails.setUser_name(user_name_value);
        userDetails.setUser_email(user_email_value);
        userDetails.setUser_contact(user_phone_value);
        userDetails.setUser_pass(user_pass_value);

        if (id==R.id.sign_in_id){
            long rowId = myDataBase.insertData(userDetails);
            if (rowId>0){
                Toast.makeText(getApplicationContext(),"Data Successfully Inserted",Toast.LENGTH_LONG).show();
                user_name.setText("");
                user_email.setText("");
                user_phone.setText("");
                user_pass.setText("");
                Intent intent = new Intent(sign_up.this,MainActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(getApplicationContext(),"Data didn't inserted ",Toast.LENGTH_LONG).show();
            }
        }
    }
}