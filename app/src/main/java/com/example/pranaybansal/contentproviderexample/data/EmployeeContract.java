package com.example.pranaybansal.contentproviderexample.data;

import android.provider.BaseColumns;

/**
 * Created by Pranay Bansal on 11/6/2017.
 */

public class EmployeeContract {

    public class EmployeeDetails implements BaseColumns{
        public static final String TABLE_NAME = "EMPLOYEES";
        public static final String  COLUMN_NAME = "NAME";
        public static final String  COLUMN_EMPNO = "EMP_NO";
    }
}
