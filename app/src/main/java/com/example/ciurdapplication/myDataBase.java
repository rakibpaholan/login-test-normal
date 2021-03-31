package com.example.ciurdapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class myDataBase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "all_user.db";
    private static final String TABLE_NAME = "user_details";
    private static final int VERSION_NUMBER = 14;
    private static final String ID = "Id";
    private static final String USER_NAME = "user_name";
    private static final String USER_EMAIL = "email";
    private static final String USER_PHONE = "user_phone";
    private static final String USER_PASS = "user_password";
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+USER_NAME+" VARCHAR(200) Not null , "+USER_EMAIL+" VARCHAR(200) Not null, "+USER_PHONE+" INTEGER(100) Not null, "+USER_PASS+" VARCHAR(200) Not null );";
    private Context context;
    private static final String UPGRADE_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME+" ";

    public myDataBase( Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
            Toast.makeText(context,"DataBase is created",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(context,"The Exception is : "+e, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(UPGRADE_TABLE);
            Toast.makeText(context,"Database is Updated",Toast.LENGTH_SHORT).show();
            onCreate(db);
        }catch (Exception e){
            Toast.makeText(context,"Exception is "+e,Toast.LENGTH_SHORT).show();
        }
    }

    public long insertData(UserDetails userDetails){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME,userDetails.getUser_name());
        contentValues.put(USER_EMAIL,userDetails.getUser_email());
        contentValues.put(USER_PHONE,userDetails.getUser_contact());
        contentValues.put(USER_PASS,userDetails.getUser_pass());
        long rowId = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return rowId;
    }

    public boolean login(String user_name, String pass){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        boolean result = false;

        if (cursor.getCount()==0){
            Toast.makeText(context,"No Data Found",Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()){
                String current_user_name = cursor.getString(1);
                String user_password = cursor.getString(4);
                if (current_user_name.equals(user_name) && user_password.equals(pass)){
                    result = true;
                    break;
                }
            }
        }

        return result;


    }
}
