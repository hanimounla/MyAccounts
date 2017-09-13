package com.example.hani__000.myaccounts;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by hani-_000 on 2017-08-31.
 */

public class SettingsMainFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main_settings, container, false);
        getActivity().setTheme(R.style.AppTheme);



        Button changePassBTN = (Button)rootView.findViewById(R.id.changePass);
        changePassBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragMgr = getActivity().getSupportFragmentManager();
                FragmentTransaction fragTrans = fragMgr.beginTransaction();
                ChangePasswordFragment a = new ChangePasswordFragment();
                fragTrans.replace(android.R.id.content, a);
                fragTrans.addToBackStack(null);
                fragTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragTrans.commit();
            }
        });

        final int x = 0;
        Button changeColor = (Button)rootView.findViewById(R.id.changeColor);
        changeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (x == 0) {
                }

                else
                    getActivity().setTheme(R.style.haniTheme);
            }
        });
        return rootView;
    }
}
