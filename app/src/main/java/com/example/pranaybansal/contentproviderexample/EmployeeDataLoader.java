package com.example.pranaybansal.contentproviderexample;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;

import com.example.pranaybansal.contentproviderexample.data.EmployeeContract;

/**
 * Created by Pranay Bansal on 11/7/2017.
 */

public class EmployeeDataLoader extends AsyncTaskLoader<Cursor> {
    private Cursor query=null;

    //never store this context as it may lead to leak in case of configration changes.
    public EmployeeDataLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (query == null) {
            forceLoad();
        }else{
            deliverResult(query);
        }
    }

    @Override
    public Cursor loadInBackground() {
        query = getContext().getContentResolver().query(EmployeeContract.EmployeeDetails.CONTENT_URI, null, null, null, EmployeeContract.EmployeeDetails.COLUMN_EMPNO);
        return query;
    }

    @Override
    public void deliverResult(Cursor data) {
        query = data;
        super.deliverResult(data);
    }
}
