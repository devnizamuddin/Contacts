package com.example.nizamuddinshamrat.contacts;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.nizamuddinshamrat.contacts.Database.PersonalInfoDataSource;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ContactAdapter.ClickListener {


    FloatingActionButton addContactFab;
    RecyclerView recyclerView;
    PersonalInfoDataSource dataSource;
    ContactAdapter contactAdapter;
    ArrayList<PersonInfo>personalInfos;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View items
        addContactFab = findViewById(R.id.addContactFab);
        recyclerView = findViewById(R.id.recyclerView);
        toolbar = findViewById(R.id.mainAppBar);

        //set App bar text
        setSupportActionBar(toolbar);
        toolbar.setTitle("Contacts");

        //set Contact recyclerView
        dataSource =new PersonalInfoDataSource(this);
        personalInfos = dataSource.getAllContacts();
        contactAdapter = new ContactAdapter(this,personalInfos,this);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(contactAdapter);

        //on Floating Action ber clicked
        //Add contact Activity
        addContactFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PersonalInfoInputActivity.class));
            }
        });





    }

    //on Contact Click
    //Contact Detail
    @Override
    public void onClick(PersonInfo personInfo) {

        Intent intent = new Intent(this,Contact_Detail_Activity.class);
        intent.putExtra("contactInfo",personInfo);
        startActivity(intent);
    }

    //on Contact Long Click
    //Delete Contact
    @Override
    public void onLongClick(final PersonInfo personInfo) {

        final int contactId = personInfo.getPersonId();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Do you want to delete this contact ??");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean status = dataSource.deleteContact(contactId);
                personalInfos.remove(personInfo);
                contactAdapter.updateContact(personalInfos);
                Toast.makeText(MainActivity.this, "Contact Deleted", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("No",null);
        builder.show();




    }

}
