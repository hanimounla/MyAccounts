package com.example.hani__000.myaccounts;

/**
 * Created by hani-_000 on 2017-03-18.
 */
public class Account
{
    int accountID;
    String webSite , eMail ,UserName , passWord;
    byte [] image;

    //setters
    public void setAccountID(int accountID) {this.accountID = accountID;}
    public void setWebSite(String webSite)  {this.webSite = webSite;}
    public void seteMail(String eMail)  {this.eMail = eMail;}
    public void setUserName(String userName) {this.UserName = userName;}
    public void setPassWord(String password) {this.passWord = password; }
    public void setImage(byte []  image) {this.image = image; }

    //Getters
    public int getAccountID(){return accountID;}
    public String getWebSite(){return webSite;}
    public String geteMail(){return eMail;}
    public String getUserName(){return UserName;}
    public String getPassWord() {return passWord;}
    public byte []  getImage() {return image;}
}
