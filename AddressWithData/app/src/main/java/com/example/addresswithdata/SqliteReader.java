package com.example.addresswithdata;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class SqliteReader extends AppCompatActivity {

    TextView nameView;
    TextView phoneView;
    TextView emailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_reader);

        getViewById();

        readAndSetText();
    }

    private void getViewById()
    {
        nameView = findViewById(R.id.text_sqlite_result);
        phoneView = findViewById(R.id.text_sqlite_phone);
        emailView = findViewById(R.id.text_sqlite_email);
    }

    private void readAndSetText()
    {
        String tableAndDBName = getString(R.string.tableName);
        SQLiteDatabase db = openOrCreateDatabase(tableAndDBName, MODE_PRIVATE, null);

        Cursor cursor = db.rawQuery("select name, phone, email from " + tableAndDBName
                + " order by _id desc limit 1", null);

        while (cursor.moveToNext())
        {
            nameView.setText(cursor.getString(0));
            phoneView.setText(cursor.getString(1));
            emailView.setText(cursor.getString(2));
        }

        db.close();
    }
}