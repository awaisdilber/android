package com.cto247.directoryapp.fragment;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cto247.directoryapp.MainActivity;
import com.cto247.directoryapp.R;
import com.cto247.directoryapp.app.CTOApp;

import java.util.Arrays;

import static com.cto247.directoryapp.utils.Constants.DEPARTMENT;
import static com.cto247.directoryapp.utils.Constants.DESIGNATION;
import static com.cto247.directoryapp.utils.Constants.EMAIL;
import static com.cto247.directoryapp.utils.Constants.EMER_NUM;
import static com.cto247.directoryapp.utils.Constants.FULL_NAME;
import static com.cto247.directoryapp.utils.Constants.MOBILE_NUM;

/**
 * Created by adilber on 11/14/2017.
 */

public class ContactDetailFragment extends Fragment implements View.OnClickListener {

    private String fullName = "N/A";
    private String ext = "N/A";
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

        ((MainActivity) getActivity()).hideOrShowSearchView(false);

        Bundle arg = getArguments();
        if (arg != null) {
            fullName = arg.getString(FULL_NAME);
            ext = arg.getString("ext");
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

        if (ext != null && ext.length() > 0){
            fullName = fullName +" - Ext. "+ ext;
        }

        mobileNum = Arrays.asList(mobileNum.split(",")).get(0);

        ((TextView) v.findViewById(R.id.txtFullNameDetailView)).setText(fullName);
        ((TextView) v.findViewById(R.id.txtMobileDetailView)).setText(mobileNum);
        ((TextView) v.findViewById(R.id.txtEmergencyContactNumberDetailView)).setText(emerNum);
        ((TextView) v.findViewById(R.id.txttxPersonalEmailDetailView)).setText(email);
        ((TextView) v.findViewById(R.id.txDesignationDetailView)).setText(designation);
        ((TextView) v.findViewById(R.id.txDepartmentDetailView)).setText(department);
        ((TextView) v.findViewById(R.id.txManagerDetailView)).setText(manager);

        v.findViewById(R.id.imgCallMobile).setOnClickListener(this);
        v.findViewById(R.id.imgCallMobileDetailView).setOnClickListener(this);
        v.findViewById(R.id.imgMsgMobileDetailView).setOnClickListener(this);
        v.findViewById(R.id.imgCallEMRMobileDetailView).setOnClickListener(this);
        v.findViewById(R.id.imgEmailPersonalDetailView).setOnClickListener(this);

        v.findViewById(R.id.imgCallEMRMobile).setOnClickListener(this);
        v.findViewById(R.id.imgEmailPersonal).setOnClickListener(this);
        v.findViewById(R.id.imgMsgMobile).setOnClickListener(this);

        v.findViewById(R.id.txttxPersonalEmailDetailView).setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {

                if ("".equals(email) || email == null || email.contains("xxxx")) {
                    Toast.makeText(getActivity(), "No Email address found!", Toast.LENGTH_LONG).show();
                    return false;
                }else {
                    ClipboardManager clipboard = (ClipboardManager) CTOApp.getAppContext().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Email", email);
                    clipboard.setPrimaryClip(clip);

                    Toast.makeText(getActivity(), "Email address copied to clipboard.",
                            Toast.LENGTH_LONG).show();

                    return true;
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgCallMobile:
                call(mobileNum);
                break;
            case R.id.imgCallMobileDetailView:
                call(mobileNum);
                break;
            case R.id.imgCallEMRMobile:
                call(emerNum);
                break;
            case R.id.imgEmailPersonal:
                composeEmail(email);
                break;
            case R.id.imgMsgMobile:
                SendMessage(mobileNum);
                break;
            case R.id.imgMsgMobileDetailView:
                SendMessage(mobileNum);
                break;
            case R.id.imgEmailPersonalDetailView:
                composeEmail(email);
                break;
            case R.id.imgCallEMRMobileDetailView:
                call(emerNum);
                break;
        }
    }

    private void call(String number) {
        if ("".equals(number) || number == null || number.contains("xxxx")) {
            Toast.makeText(getActivity(), "Invalid Number!", Toast.LENGTH_LONG).show();
            return;
        }
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + number));
        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getActivity(), "Please grant permission to call in settings!",
                    Toast.LENGTH_LONG).show();
            return;
        }
        startActivity(callIntent);
    }

    public void composeEmail(String emlAddress) {
        if (emlAddress != null) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setType("text/plain");
            intent.setData(Uri.parse("mailto:" + emlAddress));
            intent.putExtra(Intent.EXTRA_EMAIL, emlAddress);
            startActivity(intent);
        } else {
            Toast.makeText(getActivity(), "Invalid Email Address!", Toast.LENGTH_LONG).show();
            return;
        }
    }

    public void SendMessage(String number) {
        if (number != null) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setType("text/plain");
            intent.setData(Uri.parse("smsto:" + number));
            startActivity(intent);
        } else {
            Toast.makeText(getActivity(), "Invalid Number!", Toast.LENGTH_LONG).show();
            return;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}