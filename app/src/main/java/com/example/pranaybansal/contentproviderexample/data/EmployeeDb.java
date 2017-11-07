package com.example.pranaybansal.contentproviderexample.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pranay Bansal on 11/6/2017.
 */

public class EmployeeDb extends SQLiteOpenHelper {


    public static EmployeeDb employeeDb = null;
    private static final String DB_NAME = "Employee.db";
    private static final int DB_VERSION = 1;

    private EmployeeDb(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static EmployeeDb getInstance(Context context){
        if (employeeDb == null){
            employeeDb = new EmployeeDb(context);
        }
        return employeeDb;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE "+ EmployeeContract.EmployeeDetails.TABLE_NAME + "("+
                        EmployeeContract.EmployeeDetails.COLUMN_NAME + " TEXT,"+
                        EmployeeContract.EmployeeDetails.COLUMN_EMPNO + " TEXT);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ EmployeeContract.EmployeeDetails.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
