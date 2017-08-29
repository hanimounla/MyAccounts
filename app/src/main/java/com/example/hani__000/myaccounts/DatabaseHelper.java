package com.example.hani__000.myaccounts;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by hani-_000 on 2017-03-18.
 */
public class DatabaseHelper extends SQLiteOpenHelper
{

    private String TAG = this.getClass().getSimpleName();

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MyAccountsDB.db";

    public static final String TABLE_Accounts = "Accounts";

    public static final String COLUMN_AccountID= "AccountID";
    public static final String COLUMN_WebSite= "WebSite";
    public static final String COLUMN_Email= "Email";
    public static final String COLUMN_UserName = "UserName";
    public static final String COLUMN_Password= "Password";


    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_TABLE_Accounts =
            "CREATE TABLE " + TABLE_Accounts +
                    " (" +
                    COLUMN_AccountID + INT_TYPE +" PRIMARY KEY AUTOINCREMENT NOT NULL"+ COMMA_SEP +
                    COLUMN_WebSite + TEXT_TYPE + COMMA_SEP +
                    COLUMN_Email + TEXT_TYPE + COMMA_SEP +
                    COLUMN_UserName + TEXT_TYPE + COMMA_SEP +
                    COLUMN_Password+ TEXT_TYPE + ")";
    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREATE_TABLE_Accounts);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Accounts);
        onCreate(db);
    }
    public void CreateAccount(Account acc)
    {
        SQLiteDatabase db = getWritableDatabase();

        String insertAccount = "Insert into Accounts (WebSite,Email,UserName,Password) values" +
        "('" + acc.getWebSite() + "','" + acc.geteMail() + "','" + acc.getUserName() + "','" + acc.getPassWord() + "')";
        db.execSQL(insertAccount);
    }

    public ArrayList<Account> getAllAccounts()
    {
        String query = "SELECT AccountID , WebSite FROM Accounts order by WebSite";
        ArrayList<Account> Accounts = new ArrayList<Account>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor c = database.rawQuery(query, null);
        if (c != null)
        {
            while (c.moveToNext())
            {
                Account acc = new Account();
                acc.setAccountID(c.getInt(0));
                acc.setWebSite(c.getString(1));
                Accounts.add(acc);
            }
        }
        return Accounts;
    }
    public void deleteAccount(int id)
    {
        String query = "delete from Accounts where AccountID = " + id;
        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL(query);
    }
    public Account getAccountByID(int id)
    {
        Account acc  = new Account();
        String query = "SELECT * FROM Accounts WHERE AccountID = " + id;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.rawQuery(query, null);
        if (c != null)
        {
            while (c.moveToNext())
            {
                acc.setAccountID(c.getInt(0));
                acc.setWebSite(c.getString(1));
                acc.seteMail(c.getString(2));
                acc.setUserName(c.getString(3));
                acc.setPassWord(c.getString(4));
            }
        }
        return acc;
    }

    public void updateAccount(Account acc)
    {
        String query = "update Accounts set " +
                       "WebSite = '" +acc.getWebSite()+"', "+
                       "Email = '" + acc.geteMail() + "', " +
                       "UserName = '" + acc.getUserName() +"', "+
                       "Password = '" + acc.getPassWord()+"' "+
                "Where AccountId = " + acc.getAccountID();
        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL(query);
    }
}
