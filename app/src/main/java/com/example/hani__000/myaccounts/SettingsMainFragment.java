package com.example.hani__000.myaccounts;

import android.content.res.ColorStateList;
import android.graphics.Color;
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

    Button [] ColorsButtons = new Button[3];
    LinearLayout buttonsLayout;
    Button apply;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_main_settings, container, false);

        buttonsLayout = (LinearLayout)rootView.findViewById(R.id.buttonsLayout);
        buttonsLayout.setVisibility(View.INVISIBLE);
        apply = (Button)rootView.findViewById(R.id.apply);

        ColorsButtons[0] = (Button)rootView.findViewById(R.id.redBTN);
        ColorsButtons[1] = (Button)rootView.findViewById(R.id.greenBTN);
        ColorsButtons[2] = (Button)rootView.findViewById(R.id.blueBTN);

        ColorsButtons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorsButtons[0].setBackgroundResource(R.drawable.check);
                ColorsButtons[1].setBackgroundResource(R.drawable.btn_green);
                ColorsButtons[2].setBackgroundResource(R.drawable.btn_blue);
                apply.setBackgroundColor(Color.RED);
                changeColor(0);
            }
        });

        ColorsButtons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorsButtons[0].setBackgroundResource(R.drawable.btn_red);
                ColorsButtons[1].setBackgroundResource(R.drawable.check);
                ColorsButtons[2].setBackgroundResource(R.drawable.btn_blue);
                apply.setBackgroundColor(Color.GREEN);
            }
        });

        ColorsButtons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorsButtons[0].setBackgroundResource(R.drawable.btn_red);
                ColorsButtons[1].setBackgroundResource(R.drawable.btn_green);
                ColorsButtons[2].setBackgroundResource(R.drawable.check);
                apply.setBackgroundColor(0x84f4);
            }
        });


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
                if(changeColor.getText().toString().equals("CHANGE COLOR")){
                     changeColor.setText("Cancel");
                    buttonsLayout.setVisibility(View.VISIBLE);
                apply.setVisibility(View.VISIBLE);
                }
                else{
                    changeColor.setText("CHANGE COLOR");
                    buttonsLayout.setVisibility(View.INVISIBLE);
                    apply.setVisibility(View.INVISIBLE);
                }
            }
        });

        apply = (Button)rootView.findViewById(R.id.apply);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return rootView;
    }

    private void changeColor(int color) {



    }
}
