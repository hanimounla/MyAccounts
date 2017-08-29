package com.example.hani__000.myaccounts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.rey.material.widget.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hani-_000 on 2017-04-15.
 */
public class AllAccountsFragment extends Fragment {
    ListView accountsList;
    FloatingActionButton newAccFBTN ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_all_accounts, container, false);
        List<Map<String, String>> accountsArrayList  = new ArrayList<Map<String, String>>();
        accountsList = (ListView) rootView.findViewById(R.id.AccountsList);
        DatabaseHelper db = new DatabaseHelper(getActivity());
        List<Account> Accounts = db.getAllAccounts();
//        ArrayList<String> Rows = new ArrayList<String>();
//        String row;
        for (Account account : Accounts)
        {
            Map<String, String> accountRow = new HashMap<String, String>();
            String accountId = account.getAccountID() + "";
            accountRow.put("A", account.getWebSite());
            accountRow.put("B", accountId);
            accountsArrayList.add(accountRow);
//            row = account.getAccountID() + " " + account.getWebSite();
//            Rows.add(row);
        }
        String[] from = {"A", "B"};
        int[] views = { R.id.accountNameLBL, R.id.accountIDLBL};
        final SimpleAdapter ADA = new SimpleAdapter(getActivity(),
                accountsArrayList, R.layout.my_list_layout, from,views);
        accountsList.setAdapter(ADA);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.select_dialog_item, Rows);
//        accountsList.setAdapter(adapter);

        accountsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tt = (TextView) view.findViewById(R.id.accountIDLBL);
                int accountID = Integer.parseInt(tt.getText().toString());
                FragmentManager fragMgr = getActivity().getSupportFragmentManager();
                FragmentTransaction fragTrans = fragMgr.beginTransaction();
                UpdateAccountFragment a = new UpdateAccountFragment(accountID);
                fragTrans.replace(android.R.id.content, a);
                fragTrans.addToBackStack(null);
                fragTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragTrans.commit();
            }
        });

        accountsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = accountsList.getItemAtPosition(i).toString();
                Toast.makeText(getActivity(),selected + "Long Clicked :)" , Toast.LENGTH_SHORT).show();
//
                return true;
            }
        });

        newAccFBTN = (FloatingActionButton)rootView.findViewById(R.id.NewAccFBTN);
        newAccFBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                FragmentManager fragMgr = getActivity().getSupportFragmentManager();
                FragmentTransaction fragTrans = fragMgr.beginTransaction();
                AddAccountFragment a = new AddAccountFragment();
                fragTrans.replace(android.R.id.content, a);
                fragTrans.addToBackStack(null);
                fragTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragTrans.commit();
                getActivity().setTitle("New Account");
            }
        });

        Button aboutBTN = (Button)rootView.findViewById(R.id.aboutBTN);
        aboutBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),About.class));
            }
        });

        Button settingsBTN = (Button)rootView.findViewById(R.id.settingsBTN);
        settingsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),Settings.class));
            }
        });
        return rootView;
    }



}
