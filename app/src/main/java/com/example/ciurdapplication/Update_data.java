package com.example.ciurdapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update_data extends AppCompatActivity implements View.OnClickListener {
    private EditText update_data_id,update_data_name,update_data_email,update_data_phone;
    private Button update_data_button;
    private myDataBase myDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        update_data_id = (EditText)findViewById(R.id.update_data_id);
        update_data_name = (EditText)findViewById(R.id.update_data_name);
        update_data_email = (EditText)findViewById(R.id.update_data_email);
        update_data_phone = (EditText)findViewById(R.id.updated_data_phone);
        update_data_button = (Button) findViewById(R.id.update_done_button_id);
        update_data_button.setOnClickListener(this);


        myDataBase = new myDataBase(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        String update_id_value = update_data_id.getText().toString();
        String update_name_value = update_data_name.getText().toString();
        String update_email_value = update_data_email.getText().toString();
        String update_phone_value = update_data_phone.getText().toString();

        if (id==R.id.update_done_button_id){
            int result = myDataBase.update_data(update_id_value,update_name_value,update_email_value,update_phone_value);
            if (result<0){
                Toast.makeText(getApplicationContext(),"Data Not founded",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(),"Data Has Updated",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Update_data.this,All_users.class);
                startActivity(intent);
            }
        }
    }
}