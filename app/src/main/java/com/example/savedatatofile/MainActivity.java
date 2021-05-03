package com.example.savedatatofile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText getData;
    Button saveData, loadData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData = findViewById(R.id.getText);
        saveData = findViewById(R.id.saveText);
        loadData = findViewById(R.id.loadText);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = getData.getText().toString();
                if (!message.isEmpty()) {
                    try {
                        FileOutputStream fout = openFileOutput("data.txt", MODE_PRIVATE);
                        OutputStreamWriter osw = new OutputStreamWriter(fout);
                        osw.write(message);
                        osw.flush();
                        osw.close();
                        Toast.makeText(getApplicationContext(), "File saved successfully", Toast.LENGTH_LONG).show();
                        getData.setText("");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Type some data to save..", Toast.LENGTH_LONG).show();
                }
            }
        });
        loadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fin = openFileInput("data.txt");
                    InputStreamReader isr = new InputStreamReader(fin);
                    char[] inputBuffer = new char[100];
                    String s = "";
                    int charRead;
                    while ((charRead = isr.read(inputBuffer)) > 0) {
                        String readString = String.copyValueOf(inputBuffer, 0, charRead);
                        s += readString;
                        inputBuffer = new char[100];
                    }
                    getData.setText(s);
                    Toast.makeText(getApplicationContext(), "File loaded successfully", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }
}