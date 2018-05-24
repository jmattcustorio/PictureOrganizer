package com.example.admin.picturemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FileManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_manager);

        Button buttonSave = (Button) findViewById(R.id.saveProceed);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProceedClick();
            }
        });
    }

    public void saveProceedClick () {

        final EditText folderName = this.findViewById(R.id.folderName);
        Log.i("Message", "success");
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("FOLDER_NAME", folderName.getText().toString());
        startActivity(i);
    }
}
