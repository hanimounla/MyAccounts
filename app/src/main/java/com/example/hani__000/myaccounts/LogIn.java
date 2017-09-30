package com.example.hani__000.myaccounts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rey.material.widget.CheckBox;

import org.w3c.dom.Text;

import static android.content.Context.MODE_PRIVATE;

public class LogIn extends AppCompatActivity {
    EditText userNameTB, userPasswordTB , retypepassTB;
    TextView welcomeLBL;
    Button btnlogin;
    CheckBox rememberMe;
    boolean firstTime;
    static final String PREFS_NAME = "MyPrefsFile";
    String PREF_USERNAME = "prefsUsername";
    String PREF_PASSWORD ="prefsPassword";
    String PREF_SAVED = "prefsSaved";
    String PREF_FIRST_TIME = "prefsFirstTime";
    int PREF_COLOR = R.color.colorPrimary;
    SharedPreferences pref ;

    String enterdName;
    String enterdPass;
    String confirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
         welcomeLBL = (TextView)findViewById(R.id.welcomeLBL);
        welcomeLBL.setText("My Accounts Sign Up");


        pref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);


        userNameTB = (EditText) findViewById(R.id.ownerNameTB);
        userPasswordTB = (EditText) findViewById(R.id.ownerPasswordTB);
        retypepassTB = (EditText) findViewById(R.id.retypePass);
        rememberMe = (CheckBox) findViewById(R.id.rememberMeCB);
        btnlogin = (Button) findViewById(R.id.btnLogin);

        firstTime = checkFirstTimeStart();

        checkRememberMeSettings();

        btnlogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                enterdPass = userPasswordTB.getText().toString();
                if (!firstTime){
                    if (validLoginCriedentials()) {
                        setRememberMeSettings();
                        startActivity(new Intent(LogIn.this,MainActivity.class));
                        finish();
                    }
                    else
                        Toast.makeText(LogIn.this, "Wrong Password!", Toast.LENGTH_SHORT).show();
                }
               else{
                        createForFirstTime();}
            }
        });
    }

    private void createForFirstTime() {
        if (enterdName != "")
        {
            enterdName = userNameTB.getText().toString();
            confirmPass = retypepassTB.getText().toString();
            if(enterdPass.equals(confirmPass))
            {
                pref.edit().putBoolean(PREF_FIRST_TIME, false)
                           .putString(PREF_USERNAME, enterdName)
                           .putString(PREF_PASSWORD, enterdPass)
                           .putBoolean(PREF_SAVED, rememberMe.isChecked())
                           .commit();
                startActivity(new Intent(LogIn.this, MainActivity.class));
                finish();
            }
            else
                Toast.makeText(LogIn.this, "Passwords dont match!", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(LogIn.this, "Enter Name!!", Toast.LENGTH_SHORT).show();
    }

    private boolean checkFirstTimeStart() {
        firstTime = pref.getBoolean(PREF_FIRST_TIME,true);
        if (firstTime)
        {
            btnlogin.setText("Create for first time");
            retypepassTB.setVisibility(View.VISIBLE);
            userNameTB.setVisibility(View.VISIBLE);
        }
        else
        {
            firstTime = false;
            welcomeLBL.setText("Welcome " + pref.getString(PREF_USERNAME,""));
//            setTitle("Welcome back "+pref.getString(PREF_USERNAME,""));

        }
        return firstTime;
    }

    private boolean validLoginCriedentials() {
        String ownerPass = pref.getString(PREF_PASSWORD,"");
        return (enterdPass.equals(ownerPass));
    }

    private void checkRememberMeSettings() {

        String upass = pref.getString(PREF_PASSWORD, "");
        Boolean saved = pref.getBoolean(PREF_SAVED, false);

        if (saved) {
            userPasswordTB.setText(upass);
            rememberMe.setChecked(true);
        }
    }

    private void setRememberMeSettings() {

        String passwordInString = userNameTB.getText().toString();

        getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                .edit()
                .putString(PREF_USERNAME, passwordInString)
                .putString(PREF_USERNAME,pref.getString(PREF_USERNAME,""))
                .putBoolean(PREF_SAVED, rememberMe.isChecked())
                .commit();
    }
}
