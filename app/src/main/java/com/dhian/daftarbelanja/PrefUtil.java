package com.dhian.daftarbelanja;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import android.support.v4.content.*;

/**
 * Created by krrishnaaaa on Oct 02, 2015
 */
public final class PrefUtil {
    
    
    
    private static SharedPreferences getPref(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
    private static SharedPreferences getCheckPref(Context context){
        return context.getSharedPreferences("status",context.MODE_PRIVATE);
    }

    public static void saveString(Context context, String key, String value) {
        getPref(context).edit().putString(key, value).apply();
    }
    public static void saveBooleanString(Context context, String key, String value){
        getPref(context).edit().putString(key,value).apply();
    }

    public static String getString(Context context, String key, String defValue) {
        return getPref(context).getString(key, defValue);
    }
    
    public static void removeString(Context context, String key) {
        getPref(context).edit().remove(key).apply();
    }
    public static void removeStringCheck(Context context, String key){
        getCheckPref(context).edit().remove(key).apply();
    }

    public static List<Data> getAllValues(Context context) {
        Map<String, ?> values = getPref(context).getAll();
        List<Data> prefDataList = new ArrayList<>();
        
        
        //String sCheck = sharedPreferences.getString(list.get(position).sNamaBarang,"false");
        for (Map.Entry<String, ?> entry : values.entrySet()) {
            Data prefData = new Data();
            prefData.sNamaBarang = entry.getKey();
            prefData.sJumlah = entry.getValue().toString();
            SharedPreferences sharedPreferences= context.getSharedPreferences("status", Context.MODE_PRIVATE);
            
            prefData.sCheck = sharedPreferences.getString(entry.getKey()+"cb","false");
            prefDataList.add(prefData);
        }
        return prefDataList;
    }
}
