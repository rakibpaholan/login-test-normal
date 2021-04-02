package com.example.ciurdapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class All_users extends AppCompatActivity {
    private ListView user_details_listView;
    private myDataBase myDataBase = new myDataBase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);
        user_details_listView = (ListView)findViewById(R.id.list_view_id);
        loadData();

    }

    public void loadData(){
        ArrayList<String> list_of_data = new ArrayList<>();
        Cursor cursor = myDataBase.showData();
        if (cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),cursor+" Data Not Found",Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                list_of_data.add(cursor.getString(0)+" \t "+cursor.getString(1)+" \t "+cursor.getString(2)+" \t "+cursor.getString(3)+" \t "+cursor.getString(4));
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.user_view_resours_file,R.id.user_details_text_view,list_of_data);
            user_details_listView.setAdapter(adapter);
            user_details_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String value = parent.getItemAtPosition(position).toString();
                    Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}