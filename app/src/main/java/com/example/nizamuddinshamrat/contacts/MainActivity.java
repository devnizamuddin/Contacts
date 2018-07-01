package com.example.nizamuddinshamrat.contacts;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.nizamuddinshamrat.contacts.Database.PersonalInfoDataSource;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ContactAdapter.ClickListener {

    FloatingActionButton addContactFab;
    RecyclerView recyclerView;
    PersonalInfoDataSource dataSource;
    ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addContactFab = findViewById(R.id.addContactFab);
        recyclerView = findViewById(R.id.recyclerView);

        dataSource =new PersonalInfoDataSource(this);
        ArrayList<PersonInfo>personInfos = dataSource.getAllContacts();
        contactAdapter = new ContactAdapter(this,personInfos,this);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(contactAdapter);

        //on Floating Action ber clicked
        addContactFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PersonalInfoInputActivity.class));
            }
        });





    }

    @Override
    public void onClick(PersonInfo personInfo) {

    }

    @Override
    public void onLongClick(PersonInfo personInfo) {

        final int contactId = personInfo.getPersonId();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Do you want to delete this contact ??");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean status = dataSource.deleteContact(contactId);
            }
        });
        builder.setNegativeButton("No",null);




    }
}
