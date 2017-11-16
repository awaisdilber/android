package com.cto247.directoryapp.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.Manifest;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

import android.widget.TextView;
import android.widget.Toast;

import com.cto247.directoryapp.MainActivity;
import com.cto247.directoryapp.R;

import static com.cto247.directoryapp.utils.Constants.DEPARTMENT;
import static com.cto247.directoryapp.utils.Constants.DESIGNATION;
import static com.cto247.directoryapp.utils.Constants.EMAIL;
import static com.cto247.directoryapp.utils.Constants.EMER_NUM;
import static com.cto247.directoryapp.utils.Constants.FULL_NAME;
import static com.cto247.directoryapp.utils.Constants.MOBILE_NUM;

/**
 * Created by adilber on 11/14/2017.
 */

public class ContactDetailFragment extends Fragment implements View.OnClickListener{

    private String fullName = "N/A";
    private String mobileNum = "N/A";
    private String emerNum = "N/A";
    private String email = "N/A";
    private String designation = "N/A";
    private String department = "N/A";
    private String manager = "N/A";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.contact_detail_frag_layout, container, false);

        ((MainActivity)getActivity()).hideOrShowSearchView(false);

//        imgBtn = (ImageButton) getView().findViewById(R.id.imgCallMobile);
//        imgBtn.setOnClickListener(this);

        Bundle arg = getArguments();
        if(arg != null) {
            fullName = arg.getString(FULL_NAME);
            mobileNum = arg.getString(MOBILE_NUM);
            emerNum = arg.getString(EMER_NUM);
            email = arg.getString(EMAIL);
            designation = arg.getString(DESIGNATION);
            department = arg.getString(DEPARTMENT);
        }

        initViews(view);
        return view;
    }

    private void initViews(View v) {
        ((TextView)v.findViewById(R.id.txtFullNameDetailView)).setText(fullName);
        ((TextView)v.findViewById(R.id.txtMobileDetailView)).setText(mobileNum);
        ((TextView)v.findViewById(R.id.txtEmergencyContactNumberDetailView)).setText(emerNum);
        ((TextView)v.findViewById(R.id.txttxPersonalEmailDetailView)).setText(email);
        ((TextView)v.findViewById(R.id.txDesignationDetailView)).setText(designation);
        ((TextView)v.findViewById(R.id.txDepartmentDetailView)).setText(department);
        ((TextView)v.findViewById(R.id.txManagerDetailView)).setText(manager);

        v.findViewById(R.id.imgCallMobile).setOnClickListener(this);
        v.findViewById(R.id.imgCallEMRMobile).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgCallMobile:
                call(mobileNum);
                break;
            case R.id.imgCallEMRMobile:
                call(emerNum);
                break;
        }
    }

    private void call(String number) {
        if("".equals(number) || number == null) {
            Toast.makeText(getActivity(), "Invalid Number!", Toast.LENGTH_LONG).show();
            return;
        }
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+number));
        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getActivity(), "Please grant permission to call in settings!",
                    Toast.LENGTH_LONG).show();
            return;
        }
        startActivity(callIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}