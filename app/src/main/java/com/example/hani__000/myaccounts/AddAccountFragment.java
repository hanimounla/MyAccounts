package com.example.hani__000.myaccounts;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.rey.material.widget.FloatingActionButton;

import java.io.ByteArrayOutputStream;

public class AddAccountFragment extends Fragment {
    EditText WebSiteTB , eMailTB , UserNameTB , PasswordTB;
    FloatingActionButton CreateAccountFBTN, cancelFBTN;
    private byte[] image;

    String webSite;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_add_account, container, false);

        WebSiteTB = (EditText) rootView.findViewById(R.id.webSiteTB);
        eMailTB = (EditText)rootView.findViewById(R.id.emailTB);
        UserNameTB = (EditText) rootView.findViewById(R.id.userNameTB);
        PasswordTB = (EditText)rootView.findViewById(R.id.passwordTB);

        CreateAccountFBTN = (FloatingActionButton)rootView.findViewById(R.id.CreateAccountFBTN);
        cancelFBTN= (FloatingActionButton)rootView.findViewById(R.id.CancelFBTN);

        CreateAccountFBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webSite = WebSiteTB.getText().toString();
                String eMail = eMailTB.getText().toString();
                String username = UserNameTB.getText().toString();
                String passWord = PasswordTB.getText().toString();
                if (webSite.isEmpty() || eMail.isEmpty()) {
                    Toast.makeText(getActivity(), "No empties !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                DatabaseHelper d = new DatabaseHelper(getActivity());
                Account acc = new Account();
                acc.setWebSite(webSite);
                acc.seteMail(eMail);
                acc.setUserName(username);
                acc.setPassWord(passWord);
                byte[] accImage = getImage();
                acc.setImage(accImage);
                d.CreateAccount(acc);
                Toast.makeText(getActivity(), "Account Added", Toast.LENGTH_SHORT).show();
                FragmentManager fragMgr = getActivity().getSupportFragmentManager();
                getActivity().setTitle("My Accounts");
                fragMgr.popBackStack();
            }
        });

        cancelFBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                FragmentManager fragMgr = getActivity().getSupportFragmentManager();
                getActivity().setTitle("My Accounts");
                fragMgr.popBackStack();
            }
        });
        return rootView;
    }


    public byte[] getImage() {
        Resources res = getResources();
        int icon = R.drawable.social_account;
        String websiteLower = webSite.toLowerCase();
        switch (websiteLower)
        {
            case "facebook": icon = R.drawable.social_facebook; break;
            case "whatsapp": icon = R.drawable.social_whatsapp; break;
            case "youtube": icon = R.drawable.social_youtube; break;
            case "instagram": icon = R.drawable.social_instagram; break;
            case "twitter": icon = R.drawable.social_twitter; break;
            case "linkedin": icon = R.drawable.social_linkedin; break;
            default: break;
        }
        Drawable drawable = res.getDrawable(icon);
        Bitmap bitmap  = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream  stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        image = stream.toByteArray();
        return image;
    }
}
