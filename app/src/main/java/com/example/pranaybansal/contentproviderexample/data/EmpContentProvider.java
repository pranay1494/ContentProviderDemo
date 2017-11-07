package com.example.pranaybansal.contentproviderexample.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Pranay Bansal on 11/6/2017.
 */

public class EmpContentProvider extends ContentProvider {

    private static final int EMPLOYEES = 100;
    private static final int EMP_NO = 101;
    private EmployeeDb employeeDb = null;
    private UriMatcher matcher = buildUriMatcher();

    /**
     * initalize your db here.
     *
     * @return
     */
    @Override
    public boolean onCreate() {
        employeeDb = EmployeeDb.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        SQLiteDatabase db = employeeDb.getReadableDatabase();
        Cursor returnCursor= null;
        int type = matcher.match(uri);
        if (type == EMPLOYEES){
            returnCursor = db.query(EmployeeContract.EmployeeDetails.TABLE_NAME, strings, s, strings1, null, null, s1);
        }else{
            throw new UnsupportedOperationException("Not supported");
        }
        returnCursor.setNotificationUri(getContext().getContentResolver(),uri);
        return returnCursor;
    }

    /**
     * used to return the mime type of data queried.
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase db = employeeDb.getWritableDatabase();
        int type = matcher.match(uri);
        Uri returnUri = null;
        if (type == EMPLOYEES) {
            long id = db.insert(EmployeeContract.EmployeeDetails.TABLE_NAME, null, contentValues);
            if (id > 0) {
                returnUri = ContentUris.withAppendedId(EmployeeContract.EmployeeDetails.CONTENT_URI, id);
            } else {
                //throw exception
            }
        } else {
            //error throw exception.
        }
        //notify resolver that datasource has been changed.
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    private UriMatcher buildUriMatcher() {
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(EmployeeContract.AUTHORITY, EmployeeContract.PATH_EMP, EMPLOYEES);
        matcher.addURI(EmployeeContract.AUTHORITY, EmployeeContract.PATH_EMP + "/#", EMP_NO);
        return matcher;
    }
}
