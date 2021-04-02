package com.example.ciurdapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Delete_Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText delete_id_taken;
    private Button delete_button;
    private myDataBase myDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_);

        delete_id_taken = (EditText) findViewById(R.id.delete_id_taker);
        delete_button = (Button)findViewById(R.id.delete_button);
        delete_button.setOnClickListener(this);

        myDataBase = new myDataBase(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String delete_id_value = delete_id_taken.getText().toString();
        if (id==R.id.delete_button){
            int result = myDataBase.delete_data(delete_id_value);
            if (result<0){
                Toast.makeText(getApplicationContext(),"Data Not Found",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(),delete_id_value+" "+result+" Data Has Deleted",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Delete_Activity.this,All_users.class);
                startActivity(intent);
            }
        }
    }
}