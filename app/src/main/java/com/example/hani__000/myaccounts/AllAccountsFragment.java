package com.example.hani__000.myaccounts;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rey.material.widget.FloatingActionButton;
import com.rey.material.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by hani-_000 on 2017-04-15.
 */
public class AllAccountsFragment extends Fragment {
    ListView accountsList;
    FloatingActionButton newAccFBTN ;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_all_accounts, container, false);

////        List<Map<String, String>> accountsArrayList  = new ArrayList<Map<String, String>>();
//        /*DatabaseHelper db = new DatabaseHelper(getActivity());
//        List<Account> Accounts = db.getAllAccounts();
//        for (Account social_account : Accounts)
//        {
//            Map<String, String> accountRow = new HashMap<String, String>();
//            String accountId = social_account.getAccountID() + "";
//            accountRow.put("A", social_account.getWebSite());
//            accountRow.put("B", accountId);
//            accountsArrayList.add(accountRow);
//        }
//        String[] from = {"A", "B"};
//        int[] views = { R.id.accountNameLBL, R.id.accountIDLBL};
//        final SimpleAdapter ADA = new SimpleAdapter(getActivity(),
//                accountsArrayList, R.layout.my_list_layout, from,views);
//        accountsList.setAdapter(ADA);*/

        accountsList = (ListView) rootView.findViewById(R.id.AccountsList);
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
        ShowRecords();

    }
//    Account dataModel;
    private void ShowRecords(){
        DatabaseHelper db = new DatabaseHelper(getActivity());
        DataAdapter data;

        final ArrayList<Account> accounts = new ArrayList<>(db.getAllAccounts() );
        data = new DataAdapter(getActivity(), accounts);

        accountsList.setAdapter(data);

//        accountsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                dataModel = accounts.get(position);
//
//                Toast.makeText(getActivity(),String.valueOf(dataModel.getAccountID()), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void setUpViews() {

        DatabaseHelper db = new DatabaseHelper(getActivity());
        Cursor cursor = db.getAccountsByCursor();
        if (cursor.moveToFirst()) {
            try {
                SimpleCursorAdapter curAdapter = new SimpleCursorAdapter(
                        getActivity(), R.layout.my_list_layout, cursor,
                        new String[]{"_id", "WebSite", "Image"},
                        new int[]{R.id.accountIDLBL, R.id.accountNameLBL, R.id.accountImage});

                SimpleCursorAdapter.ViewBinder viewBinder = new SimpleCursorAdapter.ViewBinder() {

                    public boolean setViewValue(View view, Cursor cursor,
                                                int columnIndex) {
                        ImageView image = (ImageView) (((LinearLayout)view).getChildAt(1));
                        byte[] byteArr = cursor.getBlob(columnIndex);
                        image.setImageBitmap(BitmapFactory.decodeByteArray(byteArr, 0, byteArr.length));
                        return true;
                    }
                };
                ImageView image = (ImageView) rootView.findViewById(R.id.accountImage);
//                View contentView=LayoutInflater.from(getContext()).inflate(R.layout.list_row,parent,false);
                viewBinder.setViewValue(image, cursor, cursor.getColumnIndex("Image"));
                curAdapter.setViewBinder(viewBinder);
                accountsList.setAdapter(curAdapter);
            } catch (Exception e) {
                Toast.makeText(getActivity(),e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
