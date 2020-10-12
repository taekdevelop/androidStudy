package com.example.addresswithdata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;

public class FileReader extends AppCompatActivity {

    TextView nameView;
    TextView phoneView;
    TextView emailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_reader);

        getViewById();

        readAndSetText();
    }

    private void getViewById()
    {
        nameView = findViewById(R.id.text_file_result);
        phoneView = findViewById(R.id.text_file_phone);
        emailView = findViewById(R.id.text_file_email);
    }

    private void readAndSetText()
    {
        String value = null;

        String path = getFilesDir().toString() + "/" + getString(R.string.fileName);

        File file = new File(path);

        try
        {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(file));

            String name = null;
            String phone = null;
            String email = null;

            name = reader.readLine();
            phone = reader.readLine();
            email = reader.readLine();

            nameView.setText((CharSequence) name);
            phoneView.setText((CharSequence)phone);
            emailView.setText((CharSequence)email);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}