package com.example.hani__000.myaccounts;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hani-_000 on 2017-03-18.
 */
public class DatabaseHelper extends SQLiteOpenHelper
{
    Context context;

    private String TAG = this.getClass().getSimpleName();

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MyAccountsDB.db";

    public static final String TABLE_Accounts = "Accounts";

    public static final String COLUMN_AccountID= "AccountID";
    public static final String COLUMN_WebSite= "WebSite";
    public static final String COLUMN_Email= "Email";
    public static final String COLUMN_UserName = "UserName";
    public static final String COLUMN_Password= "Password";
    public static final String COLUMN_IMAGE= "Image";


    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String COMMA_SEP = ", ";

    private static final String SQL_CREATE_TABLE_Accounts =
            "CREATE TABLE " + TABLE_Accounts +
                    " (" +
                    COLUMN_AccountID + INT_TYPE +" PRIMARY KEY AUTOINCREMENT NOT NULL"+ COMMA_SEP +
                    COLUMN_WebSite + TEXT_TYPE + COMMA_SEP +
                    COLUMN_Email + TEXT_TYPE + COMMA_SEP +
                    COLUMN_UserName + TEXT_TYPE + COMMA_SEP +
                    COLUMN_Password+ TEXT_TYPE + COMMA_SEP+
                    COLUMN_IMAGE + " BLOB" + ")";
    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
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
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_WebSite,acc.getWebSite());
        cv.put(COLUMN_Email ,acc.geteMail());
        cv.put(COLUMN_UserName ,acc.getUserName());
        cv.put(COLUMN_Password ,acc.getPassWord());
        cv.put(COLUMN_IMAGE ,acc.getImage());
        db.insert(TABLE_Accounts, null , cv);

//        String insertAccount = "Insert into Accounts (WebSite,Email,UserName,Password) values" +
//        "('" + acc.getWebSite() + "','" + acc.geteMail() + "','" + acc.getUserName() + "','" + acc.getPassWord() + "')";
//        db.execSQL(insertAccount);
    }

    public List<Account> getAllAccounts()
    {
        String query = "SELECT AccountID , WebSite , Image FROM Accounts order by WebSite";
        List<Account> Accounts = new ArrayList<Account>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor c = database.rawQuery(query, null);
        if (c != null)
        {
            while (c.moveToNext())
            {
                Account acc = new Account();
                acc.setAccountID(c.getInt(0));
                acc.setWebSite(c.getString(1));
                acc.setImage(c.getBlob(2));
                Accounts.add(acc);
            }
        }
        return Accounts;
    }

    public Cursor getAccountsByCursor()
    {
        String query = "SELECT AccountID , WebSite , Image FROM Accounts order by WebSite";
        SQLiteDatabase database = getReadableDatabase();
        Cursor c = database.rawQuery(query, null);
        return c;
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
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_WebSite,acc.getWebSite());
        cv.put(COLUMN_Email ,acc.geteMail());
        cv.put(COLUMN_UserName ,acc.getUserName());
        cv.put(COLUMN_Password ,acc.getPassWord());
        cv.put(COLUMN_IMAGE ,getImage(acc.getWebSite()));
        String where = "AccountID = ?";
        String[] whereArgs = new String[] {String.valueOf(acc.getAccountID())};
        db.update(TABLE_Accounts, cv, where, whereArgs);
//        String query = "update Accounts set " +
//                       "WebSite = '" +acc.getWebSite()+"', "+
//                       "Email = '" + acc.geteMail() + "', " +
//                       "UserName = '" + acc.getUserName() +"', "+
//                       "Password = '" + acc.getPassWord()+"', "+
//                       "Image = " + acc.getImage() +
//                "Where AccountID = " + acc.getAccountID();
//        SQLiteDatabase database = this.getReadableDatabase();
//        database.execSQL(query);
    }

    private byte[] image;
    public byte[] getImage(String WebSite) {
        Resources res =  context.getResources();
        int icon = R.drawable.social_account;
        String websiteLower = WebSite.toLowerCase();
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
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        image = stream.toByteArray();
        return image;
    }
}
