package com.cto247.directoryapp;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cto247.directoryapp.models.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Muhammad Awais on 28-Oct-17.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.MyViewHolder> {

    private List<Employee> empList;
    private List<Employee> orgList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView logo;
        public TextView contactInfo;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            logo = (ImageView) view.findViewById(R.id.img_android);
            contactInfo = (TextView) view.findViewById(R.id.contactinfo);
        }
    }


    public ContactListAdapter(List<Employee> list) {
        Collections.sort(list, Employee.CompareByName);
        this.empList = list;
        this.orgList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_list_recycler_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Employee emp = empList.get(position);
        String nameDetail = emp.getFullName();

        if (emp.getExt() != null && emp.getExt().length() > 0){
            nameDetail = nameDetail +" - Ext."+ emp.getExt();
        }

        holder.name.setText(nameDetail);
        holder.logo.setBackgroundResource(R.drawable.con64);

        if (emp.getMobile() != null && emp.getMobile().length() > 0){
            holder.contactInfo.setText("M: "+ emp.getMobile());
        }
    }

    @Override
    public int getItemCount() {
        return empList.size();
    }

    public void setFilter(String query) {
        empList = new ArrayList<>();
        if (query.isEmpty()){
            Collections.sort(orgList,Employee.CompareByName);
            empList.addAll(orgList);
        }
        else {
            query = query.toLowerCase();
            List<Employee> filteredModelList = new ArrayList<>();
            for (Employee model : orgList) {
                String text = model.getFullName().toLowerCase();

                if (model.getMobile() != null){
                    text = text +" "+ model.getMobile();
                }

                if (model.getExt() != null)
                {
                    text = text +" "+ model.getExt();
                }

                if (text.contains(query)) {
                    filteredModelList.add(model);
                }
            }

            Collections.sort(filteredModelList,Employee.CompareByName);
            empList.addAll(filteredModelList);
        }
        notifyDataSetChanged();
    }


}
