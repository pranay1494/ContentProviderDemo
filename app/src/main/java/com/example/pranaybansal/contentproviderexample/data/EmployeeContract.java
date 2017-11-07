package com.example.pranaybansal.contentproviderexample.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Pranay Bansal on 11/6/2017.
 */

/**
 * Always create a contract class as it provides the structure of your table to other app developers.
 */
public class EmployeeContract {

    public static final String AUTHORITY = "com.example.pranaybansal.contentproviderexample";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+AUTHORITY);
    public static final String PATH_EMP = "EMPLOYEES";

    public static class EmployeeDetails implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_EMP).build();
        public static final String TABLE_NAME = "EMPLOYEES";
        public static final String  COLUMN_NAME = "NAME";
        public static final String  COLUMN_EMPNO = "EMP_NO";
    }
}
