package com.example.easynots;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LanguageManager {

    private Context ct;

    public LanguageManager(Context ctx){
        ct=ctx;
        SharedPreferences sharedPreferences = ct.getSharedPreferences("Lang", Context.MODE_PRIVATE);

    }
    public void updateResource(String code){
        Locale locale=new Locale(code);
        Locale.setDefault(locale);
        Resources resources=ct.getResources();
        Configuration configuration=resources.getConfiguration();
        configuration.locale=locale;
        resources.updateConfiguration(configuration,resources.getDisplayMetrics());

    }
}
