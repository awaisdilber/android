package com.cto247.directoryapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cto247.directoryapp.models.EmployeeInfo;

import java.util.List;

/**
 * Created by Muhammad Awais on 28-Oct-17.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.MyViewHolder> {

    private List<EmployeeInfo> empList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView logo;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            logo = (ImageView) view.findViewById(R.id.img_android);
        }
    }


    public ContactListAdapter(List<EmployeeInfo> list) {
        this.empList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_list_recycler_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        EmployeeInfo emp = empList.get(position);

        holder.name.setText(emp.getEmployeeName());
        holder.logo.setBackgroundResource(R.drawable.con64);
    }

    @Override
    public int getItemCount() {
        return empList.size();
    }
}
