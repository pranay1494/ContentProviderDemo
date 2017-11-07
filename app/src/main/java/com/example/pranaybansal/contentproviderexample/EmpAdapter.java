package com.example.pranaybansal.contentproviderexample;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pranaybansal.contentproviderexample.data.EmployeeContract;

/**
 * Created by Pranay Bansal on 11/7/2017.
 */

public class EmpAdapter extends RecyclerView.Adapter<EmpAdapter.ViewHolder> {

    private Context context;
    private Cursor cursor;

    public EmpAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_emp_list,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int name = cursor.getColumnIndex(EmployeeContract.EmployeeDetails.COLUMN_NAME);
        int number = cursor.getColumnIndex(EmployeeContract.EmployeeDetails.COLUMN_EMPNO);

        cursor.moveToPosition(position);

        String empName = cursor.getString(name);
        String empNumber = cursor.getString(number);

        holder.empName.setText(empName);
        holder.empNumber.setText(empNumber);
    }

    @Override
    public int getItemCount() {
        return cursor!=null?cursor.getCount():0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView empNumber;
        TextView empName;
        public ViewHolder(View itemView) {
            super(itemView);
            empNumber = (TextView) itemView.findViewById(R.id.emp_number);
            empName = (TextView) itemView.findViewById(R.id.emp_name);
        }
    }

    public void refreshData(Cursor tempCursor){
        this.cursor = tempCursor;
        notifyDataSetChanged();
    }
}
