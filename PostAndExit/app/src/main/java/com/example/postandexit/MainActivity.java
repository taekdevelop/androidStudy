package com.example.postandexit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    백버튼을 눌렀을 때 반응하도록 만드는 콜백함수
     */
    @Override
    public void onBackPressed() {
        AlertDialog dialog = buildDialog();
        dialog.show();
    }

    AlertDialog buildDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.backMessage);
        builder.setPositiveButton(R.string.backPositiveMessage, dialogListener);
        builder.setNegativeButton(R.string.backNegativeMessage, null);

        return builder.create();
    }

    DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            finish();
        }
    };
}