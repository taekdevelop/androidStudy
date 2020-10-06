package com.example.messengerintro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button okBtn;
    Button settingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getView();

        setListener();
    }

    private void getView()
    {
        okBtn = findViewById(R.id.btn_ok);
        okBtn = findViewById(R.id.btn_ok);
        settingBtn = findViewById(R.id.btn_setting);
    }

    private void setListener() {
        okBtn.setOnClickListener(this);
        settingBtn.setOnClickListener(this);
    }

    private void viewText(int stringID)
    {
        Toast toast = Toast.makeText(this, getString(stringID), Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onClick(View v) {
        if (v == okBtn)
        {
            viewText(R.string.ok_click);
        }
        else if (v == settingBtn)
        {

        }
    }
}