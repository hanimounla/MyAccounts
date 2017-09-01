package com.example.hani__000.myaccounts;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rey.material.widget.FloatingActionButton;

/**
 * Created by hani-_000 on 2017-08-31.
 */

public class ChangePasswordFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_change_password, container, false);

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







        return rootView;
    }

    private void changePassWord() {

    }


}
