package com.example.loginwithdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context){
        super(context,"auth_db",null,1);

    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE auth_table (username Text, password Text);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS auth_table",null);
        onCreate(db);
    }

    public Boolean signup(String uname,String pass){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username",uname);
        values.put("password",pass);

        long res = db.insert("auth_table",null,values);

        if (res == -1){
            return false;
        }else {
            return true;
        }
    }

    public Boolean checkUser(String user){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM auth_table where username = ?",new String[]{user});
        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }


    public Boolean signIn(String user,String passwd){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM auth_table where username = ? and password = ?",new String[]{user,passwd});
        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }
}
