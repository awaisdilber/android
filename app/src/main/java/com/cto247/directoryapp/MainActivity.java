package com.cto247.directoryapp;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.os.Bundle;

import com.cto247.directoryapp.fragment.ContactListFragment;
import com.cto247.directoryapp.models.DirectoryDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        SearchView sv = (SearchView)findViewById(R.id.dirsearch);
        ContactListFragment fr = new ContactListFragment();
        fr.setDirSearch(sv);

//        addFragment(new ContactListFragment(), "ContactListFragment", true);
        addFragment(fr, "ContactListFragment", true);
    }

    @Override
    protected void onDestroy() {
        DirectoryDatabase.destroyInstance();
        super.onDestroy();
    }

    private void addFragment(Fragment fragment, String tag, boolean isAddtoBackstack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(isAddtoBackstack) {
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.replace(R.id.fragContainer, fragment);
        fragmentTransaction.commit();
    }
}
