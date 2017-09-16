package com.example.hani__000.myaccounts;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.rey.material.widget.FloatingActionButton;
import com.rey.material.widget.SnackBar;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by hani-_000 on 2017-08-31.
 */

public class ChangePasswordFragment extends Fragment{

    EditText oldPass , newPass , confirmPass;
    String oldPassword;
    SharedPreferences pref;
    String PREF_PASSWORD = "prefsPassword";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_change_password, container, false);
        pref = getActivity().getSharedPreferences(LogIn.PREFS_NAME, MODE_PRIVATE);

        oldPassword = pref.getString(PREF_PASSWORD,"");

        FloatingActionButton done = (FloatingActionButton)rootView.findViewById(R.id.doneChangePass);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassWord();

            }
        });
        FloatingActionButton cancel = (FloatingActionButton)rootView.findViewById(R.id.CancelFBTN);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fgmr = getActivity().getSupportFragmentManager();
                fgmr.popBackStack();
            }
        });

        oldPass = (EditText)rootView.findViewById(R.id.oldPassTB);
        newPass = (EditText)rootView.findViewById(R.id.newPassTB);
        confirmPass = (EditText)rootView.findViewById(R.id.retypePass);

        return rootView;
    }

    private void changePassWord() {
        if(oldPassword.equals(oldPass.getText().toString()))
        {
            if(newPass.getText().toString().equals(confirmPass.getText().toString())) {
                pref.edit().putString(PREF_PASSWORD, newPass.getText().toString()).putBoolean("prefsSaved",false).commit();
                Toast.makeText(getActivity(),"Password Changed.", Toast.LENGTH_SHORT).show();
                FragmentManager fgmr = getActivity().getSupportFragmentManager();
                fgmr.popBackStack();
            }
            else
                Toast.makeText(getActivity(),"Passwords don't match!", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getActivity(),"Wrong Password!", Toast.LENGTH_SHORT).show();

    }


}
