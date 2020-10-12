package com.example.addresswithdata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    EditText nameEdit;
    EditText phoneEdit;
    EditText emailEdit;

    Button sqliteBtn;
    Button realmBtn;
    Button fileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViewByID();

        setBtnClick();
    }

    private void getViewByID()
    {
        nameEdit = findViewById(R.id.edit_name);
        phoneEdit = findViewById(R.id.edit_phone);
        emailEdit = findViewById(R.id.edit_email);

        sqliteBtn = findViewById(R.id.btn_sqlite);
        realmBtn = findViewById(R.id.btn_realm);
        fileBtn = findViewById(R.id.btn_file);
    }

    private void setBtnClick()
    {
        View.OnClickListener sqliteListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String tableAndDBName = getString(R.string.tableName);
                SQLiteDatabase db = openOrCreateDatabase(tableAndDBName, MODE_PRIVATE, null);

                db.execSQL("drop table " + tableAndDBName);

                db.execSQL("create table " + tableAndDBName +
                        " (_id integer primary key autoincrement, " +
                        "name, phone, email)");

                db.execSQL("insert into " + tableAndDBName + " (name, phone, email) values (?, ?, ?)",
                        new String[]{
                                nameEdit.getText().toString(),
                                phoneEdit.getText().toString(),
                                emailEdit.getText().toString()
                        });

                db.close();

                startActivity(new Intent(v.getContext(), SqliteReader.class));
            }
        };

        View.OnClickListener realmListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Realm.init(v.getContext());
                Realm realm = Realm.getDefaultInstance();

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        testDBData dbData = realm.createObject(testDBData.class);

                        dbData.setName(nameEdit.getText().toString());
                        dbData.setPhone(phoneEdit.getText().toString());
                        dbData.setEMail(emailEdit.getText().toString());
                    }
                });

                startActivity(new Intent(v.getContext(), RealmReader.class));
            }
        };

        View.OnClickListener fileListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String filePath = getFilesDir().toString() + "/" + getString(R.string.fileName) ;

                try
                {
                    File file = new File(filePath);

                    if (false == file.exists())
                    {
                        file.createNewFile();
                    }

                    // PrintWriter는 파일을 출력할 때 개행을 출력함.
                    PrintWriter writer = new PrintWriter(file);

                    writer.println(nameEdit.getText().toString());
                    writer.println(phoneEdit.getText().toString());
                    writer.println(emailEdit.getText().toString());

                    writer.flush();
                    writer.close();

                    startActivity(new Intent(v.getContext(), FileReader.class));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };

        sqliteBtn.setOnClickListener(sqliteListener);
        realmBtn.setOnClickListener(realmListener);
        fileBtn.setOnClickListener(fileListener);
    }

    private void showToast(String message)
    {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}