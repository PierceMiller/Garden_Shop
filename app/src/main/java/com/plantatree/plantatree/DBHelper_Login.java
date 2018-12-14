package com.plantatree.plantatree;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper_Login extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PlantATree_Login.db";
    private static final int DATABASE_VERSION = 1;

    //Creates a table called user, that holds two values of an email and a password per user.
    private static final String SQL_CREATE_LOGIN_TABLE =
            "CREATE TABLE USER(EMAIL TEXT PRIMARY KEY, PASSWORD TEXT)";

    private static final String EXISTING_TABLE = "DROP TABLE IF EXISTS user";

    public DBHelper_Login(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_LOGIN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(EXISTING_TABLE);
    }

    public boolean addUser(String email, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        /*CV class, allows the ability to add information within an object,
        in this case, having the users email and password stored in the
        correct object*/
        values.put("email",email);
        values.put("password", password);

        long ins = db.insert("user", null, values);

        if(ins==-1) return false;
        else return true;
    }

    public Boolean validateEmail(String email){

        /*cursor acts as a pointer to a specified object and queries the database
        if the inputted email does exist and returns a boolean value upon completion*/
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM user WHERE email=?", new String[]{email});

        if(c.getCount() > 0)return false;
        else return true;
    }

    public Boolean emailPassword(String email, String password){

        /*similar to the one before, however asks for multiple conditions and
        looks to see if both belong to the same user*/
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM user WHERE email=? AND password=?",
                new String[]{email, password});

        if(c.getCount() > 0) return true;
        else return false;
    }
}
