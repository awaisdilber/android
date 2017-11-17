package com.cto247.directoryapp;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cto247.directoryapp.fragment.ContactDetailFragment;
import com.cto247.directoryapp.models.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.cto247.directoryapp.utils.Constants.DEPARTMENT;
import static com.cto247.directoryapp.utils.Constants.DESIGNATION;
import static com.cto247.directoryapp.utils.Constants.EMAIL;
import static com.cto247.directoryapp.utils.Constants.EMER_NUM;
import static com.cto247.directoryapp.utils.Constants.FULL_NAME;
import static com.cto247.directoryapp.utils.Constants.MOBILE_NUM;
/**
 * Created by Muhammad Awais on 28-Oct-17.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.MyViewHolder> implements
        View.OnClickListener {

    private Context contex;

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


    public ContactListAdapter(Context ctx, List<Employee> list) {
        this.contex = ctx;
        Collections.sort(list, Employee.CompareByName);
        this.empList = list;
        this.orgList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_list_recycler_item, parent, false);
        itemView.setOnClickListener(this);

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
        holder.name.setTag(position);

        if (emp.getMobile() != null && emp.getMobile().length() > 0){
            holder.contactInfo.setText("M: "+ emp.getMobile());
        }
    }

    @Override
    public int getItemCount() {
        return empList.size();
    }

    @Override
    public void onClick(View view) {
        int position = (int) view.findViewById(R.id.name).getTag();

        Bundle bundle = new Bundle();
        bundle.putString(FULL_NAME, empList.get(position).getFullName());
        bundle.putString("ext", empList.get(position).getExt());
        bundle.putString(MOBILE_NUM, empList.get(position).getMobile());
        bundle.putString(EMER_NUM, empList.get(position).getContactPhone());
        bundle.putString(EMAIL, empList.get(position).getPersonalEmail());
        bundle.putString(DESIGNATION, empList.get(position).getDesignation());
        bundle.putString(DEPARTMENT, empList.get(position).getDepartment());

        ContactDetailFragment detailFragment = new ContactDetailFragment();
        detailFragment.setArguments(bundle);

        ((MainActivity)contex).replaceFragment(detailFragment, "ContactDetailFragment", true);
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
