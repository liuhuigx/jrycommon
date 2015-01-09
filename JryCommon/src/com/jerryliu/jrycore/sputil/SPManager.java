
package com.jerryliu.jrycore.sputil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;

import com.jerryliu.jrycore.bean.BaseJsonObject;
import com.jerryliu.jrycore.log.Liu;

public class SPManager {
    public final static String KEY_PROJECTS = "KEY_PROJECTS";
    public final static String KEY_ACTIVE_PROJECT = "KEY_ACTIVE_PROJECT";
    public final static String KEY_ITEMS = "KEY_ITEMS";

    private String mPrefName;

    public SPManager(String prefName) {
        mPrefName = prefName;
    }

    public HashMap<Long, BaseJsonObject> readSPContent(Context c) {
        HashMap<Long, BaseJsonObject> hm = new HashMap<Long, BaseJsonObject>();
        SharedPreferences settings = c.getSharedPreferences(mPrefName, 0);
        // MULTI PROJECTS SUPPORT.
        Set<String> jsSet = settings.getStringSet(KEY_PROJECTS, null);

        if (null == jsSet || jsSet.size() == 0) {
            return null;
        }

        Iterator<String> ite = jsSet.iterator();

        long length = 0;
        while (ite.hasNext()) {
            String js = ite.next();
            length += js.length();
            BaseJsonObject po = BaseJsonObject.createfromJsonString(js);
            if (po != null) {
                hm.put(po.getId(), po);
            }
        }

        Liu.d("readSPContent hm size:" + hm.size() + " length:" + length);
        return hm;
    }

    public long readAcitiveProjectFromSP(Context c) {
        SharedPreferences settings = c.getSharedPreferences(mPrefName, 0);
        long apid = settings.getLong(KEY_ACTIVE_PROJECT, -1);
        return apid;
    }

    public void writeActiveProjectToSP(Context c, long apid) {
        writeLongToSP(c, KEY_ACTIVE_PROJECT, apid);
    }

    private void writeLongToSP(Context c, String key, long val) {
        SharedPreferences settings = c.getSharedPreferences(mPrefName, 0);
        SharedPreferences.Editor e = settings.edit();
        e.putLong(key, val);

        // Commit the edits!
        e.commit();
    }

    public void setSPcontent(Context c, Set<String> vals) {
        writeToSharedPreference(c, KEY_PROJECTS, vals);
    }

    private void writeToSharedPreference(Context c, String key, Set<String> vals) {
        SharedPreferences settings = c.getSharedPreferences(mPrefName, 0);
        SharedPreferences.Editor e = settings.edit();
        e.putStringSet(key, vals);

        e.commit();
    }
}
