package com.cto247.directoryapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.cto247.directoryapp.fragment.ContactDetailFragment;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    private Intent i;
    private Context ctx;
    private Button btnCallMobile, btnCallEmergencyContact;
    private ImageButton imgBtnCallMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ctx = this;

        imgBtnCallMobile = (ImageButton) findViewById(R.id.imgCallMobile);

        //imgBtnCallMobile.setOnClickListener(ctx);


//        if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(DetailActivity.this,
//                    new String[]{Manifest.permission.CALL_PHONE},
//                    12);
//        } else {
//            //call();
//        }

        i = getIntent();
        String fullName = i.getStringExtra("FullName");
        Toast.makeText(this, fullName, Toast.LENGTH_LONG).show();
        ContactDetailFragment detailFr = new ContactDetailFragment();
        addFragment(detailFr, "ContactDetailFragment", true);

    }

    private void addFragment(Fragment fragment, String tag, boolean isAddtoBackstack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (isAddtoBackstack) {
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.replace(R.id.detailFragContainer, fragment);
        fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    void call() {
        Uri uri = Uri.parse("tel:" + "03004384961");
        Intent i = new Intent(Intent.ACTION_CALL, uri);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(i);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {

//        if (requestCode == 12 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            call();
//        }

        if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(DetailActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    12);
        } else {
            call();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgCallMobile:
                call();
                break;
        }
    }
}
