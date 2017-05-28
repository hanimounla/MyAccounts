package com.example.hani__000.myaccounts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.rey.material.app.Dialog;
import com.rey.material.app.DialogFragment;
import com.rey.material.app.SimpleDialog;
import com.rey.material.widget.CompoundButton;
import com.rey.material.widget.FloatingActionButton;

/**
 * Created by hani-_000 on 2017-04-18.
 */
public class UpdateAccountFragment extends Fragment {
    EditText WebSiteTB, eMailTB, UserNameTB, PasswordTB;
    FloatingActionButton updateFBTN, cancelFBTN , deleteFBTN;
    CompoundButton showPassBTN;
    Account acc;
    int accountID;

    public UpdateAccountFragment (int accountID)
    {
        this.accountID = accountID;
    }

    private void fillAccountInfo() {
        DatabaseHelper db = new DatabaseHelper(getActivity());
        acc = db.getAccountByID(accountID);
        WebSiteTB.setText(acc.getWebSite());
        eMailTB.setText(acc.geteMail());
        UserNameTB.setText(acc.getUserName());
        PasswordTB.setText(acc.getPassWord());

        getActivity().setTitle("Edit account");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_update_account, container, false);
        getActivity().setTitle("Edit Account");

        WebSiteTB = (EditText) rootView.findViewById(R.id.webSiteTB);
        eMailTB = (EditText) rootView.findViewById(R.id.emailTB);
        UserNameTB = (EditText) rootView.findViewById(R.id.userNameTB);
        PasswordTB = (EditText) rootView.findViewById(R.id.passwordTB);

        showPassBTN = (CompoundButton) rootView.findViewById(R.id.showPass);

        fillAccountInfo();

        updateFBTN= (FloatingActionButton)rootView.findViewById(R.id.updateFBTN);
        cancelFBTN= (FloatingActionButton)rootView.findViewById(R.id.CancelFBTN);
        deleteFBTN= (FloatingActionButton)rootView.findViewById(R.id.deleteFBTN);

        updateFBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });

        cancelFBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragMgr = getActivity().getSupportFragmentManager();
                getActivity().setTitle("My Accounts");
                fragMgr.popBackStack();
            }
        });

        deleteFBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });

        showPassBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showPassBTN.getText().toString().equals("Show")) {
                    // This will display plain-text
                    PasswordTB
                            .setTransformationMethod(SingleLineTransformationMethod
                                    .getInstance());
                    showPassBTN.setText("Hide");
                } else {
                    // This will display *******
                    PasswordTB
                            .setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());
                    showPassBTN.setText("Show");
                }
            }
        });
        return  rootView;
    }

    private void delete() {
        Dialog.Builder builder = null;
        builder = new SimpleDialog.Builder(R.style.SimpleDialogLight){
            @Override
            public void onPositiveActionClicked(DialogFragment fragment) {
                DatabaseHelper db = new DatabaseHelper(getActivity());
                db.deleteAccount(accountID);
                FragmentManager fragMgr = getActivity().getSupportFragmentManager();
                getActivity().setTitle("My Accounts");
                fragMgr.popBackStack();
                Toast.makeText(getActivity(), "Account Deleted", Toast.LENGTH_SHORT).show();

                super.onPositiveActionClicked(fragment);
            }

            @Override
            public void onNegativeActionClicked(DialogFragment fragment) {
//                Toast.makeText(getActivity(), "no", Toast.LENGTH_SHORT).show();
                super.onNegativeActionClicked(fragment);
            }
        };

        builder.title("Delete Account?")
                .positiveAction("Yes")
                .negativeAction("No");
        DialogFragment fragment = DialogFragment.newInstance(builder);
        fragment.show(getFragmentManager(), null);

    }

    private void update() {
        DatabaseHelper db = new DatabaseHelper(getActivity());
        Account acc = new Account();
        acc.setAccountID(accountID);
        acc.setWebSite(WebSiteTB.getText().toString());
        acc.seteMail(eMailTB.getText().toString());
        acc.setUserName(UserNameTB.getText().toString());
        acc.setPassWord(PasswordTB.getText().toString());
        db.updateAccount(acc);
        Toast.makeText(getActivity(), "Account updated", Toast.LENGTH_SHORT).show();
        FragmentManager fragMgr = getActivity().getSupportFragmentManager();
        getActivity().setTitle("My Accounts");
        fragMgr.popBackStack();

    }
}
