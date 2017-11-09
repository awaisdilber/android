package com.cto247.directoryapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cto247.directoryapp.ContactListAdapter;
import com.cto247.directoryapp.R;
import com.cto247.directoryapp.manager.DataManager;
import com.cto247.directoryapp.models.EmployeeInfo;

import java.util.List;

/**
 * Created by Muhammad Awais on 28-Oct-17.
 */

public class ContactListFragment extends Fragment implements SearchView.OnQueryTextListener {

    private ContactListAdapter mAdapter;
    private RecyclerView recyclerView;
    private List<EmployeeInfo> mEmployeeModel;
    private SearchView dirSearch;

    public void setDirSearch(SearchView value){
        this.dirSearch = value;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.contact_list_frag_layout, container, false);

        //        ContactListAdapter mAdapter = new ContactListAdapter(DataManager.getDataManager().getEmployeeInfoList());
        mAdapter = new ContactListAdapter(DataManager.getDataManager().getEmployeeInfoList());

//        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

//        SearchView dirSearch = (SearchView)view.findViewById(R.id.dirsearch);
        if (dirSearch != null) {
            dirSearch.setOnQueryTextListener(this);
        }
        else{
            Log.d("dirapp", "SearchView not found.");
        }

        return view;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mAdapter.setFilter(newText);
        return true;
    }
}
