package com.example.nizamuddinshamrat.contacts.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonalInfoDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "personInfo_db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CONTACTS = "table_contacts";

    public static final String COL_PERSON_ID = "person_id";
    public static final String COL_PERSON_NAME = "person_name";
    public static final String COL_PERSON_NUMBER = "person_number";
    public static final String COL_PERSON_EMAIL = "person_email";
    public static final String COL_PERSON_ADDRESS = "person_address";


    public static final String CREATE_TABLE_CONTACTS = "CREATE TABLE "+TABLE_CONTACTS+"("+
            COL_PERSON_ID+" INTEGER PRIMARY KEY, "+
            COL_PERSON_NAME+" TEXT, "+
            COL_PERSON_NUMBER+" TEXT, "+
            COL_PERSON_EMAIL+" TEXT, "+
            COL_PERSON_ADDRESS+" TEXT);";



    public PersonalInfoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_CONTACTS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CONTACTS);
        onCreate(db);

    }
}
