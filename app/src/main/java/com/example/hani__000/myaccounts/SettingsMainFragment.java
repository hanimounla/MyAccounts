package com.example.hani__000.myaccounts;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by hani-_000 on 2017-08-31.
 */

public class SettingsMainFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_main_settings, container, false);



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

        final Button changeColor = (Button)rootView.findViewById(R.id.changeColor);
        changeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout l = (LinearLayout)rootView.findViewById(R.id.buttonsLayout);
                l.setVisibility(View.VISIBLE);
                changeColor.setText("Cancel");
            }
        });
        return rootView;
    }
}
