package com.example.nizamuddinshamrat.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.nizamuddinshamrat.contacts.Database.PersonalInfoDataSource;

public class PersonalInfoInputActivity extends AppCompatActivity {

    private ImageView personImage;
    private EditText personName, personNumber, personEmail, personAddress;
    android.support.v7.widget.Toolbar toolbar;

    //Objects
    PersonalInfoDataSource dataSource;
    PersonInfo personInfoSerializable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info_input);

        personImage = findViewById(R.id.personImg);
        personName = findViewById(R.id.nameEt);
        personNumber = findViewById(R.id.numberEt);
        personEmail = findViewById(R.id.emailEt);
        personAddress = findViewById(R.id.addressEt);
        dataSource = new PersonalInfoDataSource(this);

        personInfoSerializable = (PersonInfo) getIntent().getSerializableExtra("ContactDetail");

        //Toolbar View
        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (personInfoSerializable != null){

            getSupportActionBar().setTitle("Edit Contact");
            personName.setText(personInfoSerializable.getPersonName());
            personNumber.setText(personInfoSerializable.getPersonNumber());
            personEmail.setText(personInfoSerializable.getPersonEmail());
            personAddress.setText(personInfoSerializable.getPersonAddress());

        }
        else {
            getSupportActionBar().setTitle("Create Contacts");
        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.input_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()){

            case android.R.id.home:
                this.finish();
                break;

            case R.id.saveContacts:
                saveContacts();
                break;

            case R.id.help:
                break;

        }
        return super.onOptionsItemSelected(item);


    }
    public void saveContacts(){
        String name = personName.getText().toString();
        String number = personNumber.getText().toString();
        String email = personEmail.getText().toString();
        String address = personAddress.getText().toString();

       if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(number) ) {
           //Name and number hare
           PersonInfo personInfo = new PersonInfo(name,number,email,address);

           if (personInfoSerializable == null){

               //Insert Contact
               boolean status =  dataSource.insertContact(personInfo);
               if (status){
                   Toast.makeText(this, "Contact Saved"+email, Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(this,MainActivity.class));
                   finish();

               }
               else {
                   Toast.makeText(this, "Something Error", Toast.LENGTH_SHORT).show();
               }
           }
           else {

               //update Contact
               boolean status =  dataSource.editContact(personInfo,personInfoSerializable.getPersonId());
               if (status){
                   Toast.makeText(this, "Contact Edited", Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(this,MainActivity.class));
                   finish();

               }
               else {
                   Toast.makeText(this, "Something Error", Toast.LENGTH_SHORT).show();
               }
           }



       }
       else if (!TextUtils.isEmpty(number) && TextUtils.isEmpty(name)){
           Toast.makeText(this, "Name Required", Toast.LENGTH_SHORT).show();
       }
       else if (!TextUtils.isEmpty(name) && TextUtils.isEmpty(number)){
           Toast.makeText(this, "Number Required", Toast.LENGTH_SHORT).show();
       }
       else {
           Toast.makeText(this, "Name & Number Required", Toast.LENGTH_SHORT).show();
       }
    }
}
