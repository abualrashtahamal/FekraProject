package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MOOD=0;
    private static final String PREF_NAME="LOGIN";
    private static final String LOGIN="IS_LOGIN";
    public static final String KEY_EMAIL="EMAIL";
    public static final String KEY_PASSWORD="PASSWORD";
    public SessionManager(Context cont){
        this.context=cont;
        sharedPreferences=cont.getSharedPreferences(PREF_NAME,PRIVATE_MOOD);
        editor=sharedPreferences.edit();
    }
    public void createsession(String Email,String Password){
        editor.putBoolean(LOGIN,true);
        editor.putString(KEY_EMAIL,Email);
        editor.putString(KEY_PASSWORD,Password);
        editor.apply();
    }
    public boolean isloggin(){
        return sharedPreferences.getBoolean(LOGIN,false);
    }
    public void checklogin(){

        if(!this.isloggin()){
            Intent i=new Intent(context,MainActivity.class);
            context.startActivity(i);
            ((profilestudent)context).finish();
        }
    }

    public HashMap<String,String>getdetail(){
        HashMap<String,String>user=new HashMap<>();
        user.put(KEY_EMAIL,sharedPreferences.getString(KEY_EMAIL,null));
        user.put(KEY_PASSWORD,sharedPreferences.getString(KEY_PASSWORD,null));
        return user;
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent i=new Intent(context,MainActivity.class);
        context.startActivity(i);
        ((profilestudent)context).finish();
    }
}
