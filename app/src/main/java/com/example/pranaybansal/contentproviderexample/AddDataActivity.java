package com.example.pranaybansal.contentproviderexample;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pranaybansal.contentproviderexample.data.EmployeeContract;

/**
 * Created by Pranay Bansal on 11/7/2017.
 */

public class AddDataActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etNumber;
    private Button btnSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_items);
        etName = (EditText)findViewById(R.id.etName);
        etNumber = (EditText)findViewById(R.id.etNumber);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put(EmployeeContract.EmployeeDetails.COLUMN_NAME,etName.getText().toString());
                values.put(EmployeeContract.EmployeeDetails.COLUMN_EMPNO,etNumber.getText().toString());
                getContentResolver().insert(EmployeeContract.EmployeeDetails.CONTENT_URI,values);
                finish();
            }
        });
    }
}
