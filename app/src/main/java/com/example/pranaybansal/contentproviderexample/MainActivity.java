package com.example.pranaybansal.contentproviderexample;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private Button btnAddNew;
    private RecyclerView rvEmployees;
    private EmpAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddNew = (Button) findViewById(R.id.btn_add_new);
        rvEmployees = (RecyclerView) findViewById(R.id.rv_employees);

        rvEmployees.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter = new EmpAdapter(this);
        rvEmployees.setAdapter(adapter);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddDataActivity.class);
                startActivity(intent);
            }
        });

        getLoaderManager().initLoader(999,null,MainActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLoaderManager().restartLoader(999,null,this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new EmployeeDataLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        adapter.refreshData(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.refreshData(null);
    }
}
