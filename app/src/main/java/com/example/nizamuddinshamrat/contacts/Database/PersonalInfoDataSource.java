package com.example.nizamuddinshamrat.contacts.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nizamuddinshamrat.contacts.PersonInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PersonalInfoDataSource {

    PersonalInfoDatabaseHelper helper;
    SQLiteDatabase database;

    public PersonalInfoDataSource(Context context) {
        helper = new PersonalInfoDatabaseHelper(context);
    }

    public void databaseOpen(){
        database = helper.getWritableDatabase();
    }
    public void databaseClose(){
        database.close();
    }

    public boolean insertContact(PersonInfo personInfo){
        this.databaseOpen();

        ContentValues values = new ContentValues();
        values.put(PersonalInfoDatabaseHelper.COL_PERSON_NAME,personInfo.getPersonName());
        values.put(PersonalInfoDatabaseHelper.COL_PERSON_NUMBER,personInfo.getPersonNumber());
        values.put(PersonalInfoDatabaseHelper.COL_PERSON_EMAIL,personInfo.getPersonEmail());
        values.put(PersonalInfoDatabaseHelper.COL_PERSON_ADDRESS,personInfo.getPersonAddress());

        long insertedRow = database.insert(PersonalInfoDatabaseHelper.TABLE_CONTACTS,null,values);

        this.databaseClose();
        if (insertedRow>0){
            return true;
        }
        else
        {
            return false;
        }

    }

    public ArrayList<PersonInfo> getAllContacts(){

        ArrayList<PersonInfo>personInfos = new ArrayList<>();
        this.databaseOpen();

        Cursor cursor =database.query(PersonalInfoDatabaseHelper.TABLE_CONTACTS,null,null,null, null, null, null);
        if (cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                int id = cursor.getInt(cursor.getColumnIndex(PersonalInfoDatabaseHelper.COL_PERSON_ID));
                String name = cursor.getString(cursor.getColumnIndex(PersonalInfoDatabaseHelper.COL_PERSON_NAME));
                String number = cursor.getString(cursor.getColumnIndex(PersonalInfoDatabaseHelper.COL_PERSON_NUMBER));
                String email = cursor.getString(cursor.getColumnIndex(PersonalInfoDatabaseHelper.COL_PERSON_EMAIL));
                String address = cursor.getString(cursor.getColumnIndex(PersonalInfoDatabaseHelper.COL_PERSON_ADDRESS));
                personInfos.add(new PersonInfo(id,name,number,email,address));

            }
            while (cursor.moveToNext());
        }

        cursor.close();
        this.databaseClose();


        return personInfos;

    }
    public boolean deleteContact(int contactId){

        this.databaseOpen();
        int deletedRow = database.delete(PersonalInfoDatabaseHelper.TABLE_CONTACTS,
                PersonalInfoDatabaseHelper.COL_PERSON_ID+"="+contactId,null);

        this.databaseClose();

        if (deletedRow>0){
            return true;
        }
        else {
            return false;
        }

    }
    public boolean editContact (PersonInfo personInfo, int contactId){
        this.databaseOpen();

        ContentValues values = new ContentValues();
        values.put(PersonalInfoDatabaseHelper.COL_PERSON_NAME,personInfo.getPersonName());
        values.put(PersonalInfoDatabaseHelper.COL_PERSON_NUMBER,personInfo.getPersonNumber());
        values.put(PersonalInfoDatabaseHelper.COL_PERSON_EMAIL,personInfo.getPersonEmail());
        values.put(PersonalInfoDatabaseHelper.COL_PERSON_ADDRESS,personInfo.getPersonAddress());

        int editContact = database.update(PersonalInfoDatabaseHelper.TABLE_CONTACTS,values,
                PersonalInfoDatabaseHelper.COL_PERSON_ID+"="+contactId,null);
        if (editContact>0){
            return true;
        }
        else {
            return false;
        }


    }
}
