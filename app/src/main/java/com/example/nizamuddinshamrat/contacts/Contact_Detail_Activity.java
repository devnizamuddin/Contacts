package com.example.nizamuddinshamrat.contacts;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Contact_Detail_Activity extends AppCompatActivity {

    //objects
    private PersonInfo personInfo;


    //all view item
    private android.support.v7.widget.Toolbar toolbar;
    private TextView contactNameTv, numberTV, addressTv, emailTv;
    private LinearLayout callLayout, textLayout, emailLayout, addressLayout;
    private View lineThree, lineFour;
    private FloatingActionButton editFab;
    private int CALL_PERMISSION_REQUEST_CODE = 1;
    private int TEXT_PERMISSION_REQUEST_CODE = 2;
    private int EMAIL_PERMISSION_REQUEST_CODE = 3;
    private int MAP_PERMISSION_REQUEST_CODE = 4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);


        //get id of all view item form layout
        contactNameTv = findViewById(R.id.contactNameTv);
        numberTV = findViewById(R.id.numberTv);
        addressTv = findViewById(R.id.addressTV);
        emailTv = findViewById(R.id.emailTV);
        editFab = findViewById(R.id.editFab);

        lineThree = findViewById(R.id.lineThree);
        lineFour = findViewById(R.id.lineFour);


        callLayout = findViewById(R.id.callLayout);
        textLayout = findViewById(R.id.textLayout);
        emailLayout = findViewById(R.id.emailLayout);

        //Working in ToolBar
        toolbar = findViewById(R.id.app_bar_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Get Contact Info from previous Intent
        personInfo = (PersonInfo) getIntent().getSerializableExtra("contactInfo");

        //set data of view item
        contactNameTv.setText(personInfo.getPersonName());
        numberTV.setText(personInfo.getPersonNumber());

        String email = personInfo.getPersonEmail();
        if (!TextUtils.isEmpty(email)) {
            emailTv.setText(email);
        } else {
            emailTv.setText("Not Set Yet");
        }

        String address = personInfo.getPersonAddress();
        if (!TextUtils.isEmpty(address)) {
            addressTv.setText(address);
        } else {
            addressTv.setText("Not Set Yet");
        }

        editFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Contact_Detail_Activity.this, PersonalInfoInputActivity.class);
                intent.putExtra("ContactDetail", personInfo);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                this.finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void callInPhoneNumber(View view) {

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + personInfo.getPersonNumber()));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    CALL_PERMISSION_REQUEST_CODE);
            return;
        }
        else {
            startActivity(intent);

        }
    }

    public void textInPhoneNumber(View view) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:" + personInfo.getPersonNumber()));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    TEXT_PERMISSION_REQUEST_CODE);
            return;
        }
        else {
            startActivity(intent);

        }
    }



    public void sendEmail(View view) {

        if (!TextUtils.isEmpty(personInfo.getPersonEmail())){

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:"+personInfo.getPersonEmail()));
            startActivity(emailIntent);
        }
        else {
            Toast.makeText(this, "Please Set Email First", Toast.LENGTH_SHORT).show();
        }


    }

    public void goToAddress(View view) {

        if (!TextUtils.isEmpty(personInfo.getPersonAddress())){

            Uri gmmIntentUri = Uri.parse("geo:0,0?q="+personInfo.getPersonAddress());
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }
        else {
            Toast.makeText(this, "Please Set Address First", Toast.LENGTH_SHORT).show();
        }


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case 1:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay!
                    Toast.makeText(this, "Call Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    // permission denied, boo! Disable the
                    Toast.makeText(this, "For calling need this permission", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    Toast.makeText(this, "Message Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    // permission denied, boo! Disable the
                    Toast.makeText(this, "For Messaging need this permission", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
