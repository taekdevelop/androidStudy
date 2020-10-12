package com.example.addresswithdata;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmObject;

public class RealmReader extends AppCompatActivity {

    TextView nameView;
    TextView phoneView;
    TextView emailView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_reader);

        getViewById();

        readAndSetText();
    }

    private void getViewById()
    {
        nameView = findViewById(R.id.text_realm_result);
        phoneView = findViewById(R.id.text_realm_phone);
        emailView = findViewById(R.id.text_realm_email);
    }

    private void readAndSetText()
    {
        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();

        testDBData dbData = realm.where(testDBData.class).findFirst();

        nameView.setText(dbData.getName());
        phoneView.setText(dbData.getPhone());
        emailView.setText(dbData.getEMail());
    }
}