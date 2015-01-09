
package com.jerryliu.jrycore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;

import com.jerryliu.jrycore.bean.BaseJsonObject;
import com.jerryliu.jrycore.log.Liu;

public class Utils {
    private final static String PREFS_NAME = "pdemo_prefs_project";
    public final static String KEY_PROJECTS = "KEY_PROJECTS";
    public final static String KEY_ACTIVE_PROJECT = "KEY_ACTIVE_PROJECT";
    public final static String KEY_ITEMS = "KEY_ITEMS";

    public static void log(String l) {
        Liu.d(l);
    }

    public static HashMap<Long, BaseJsonObject> readSPContent(Context c, String prefName) {
        HashMap<Long, BaseJsonObject> hm = new HashMap<Long, BaseJsonObject>();
        SharedPreferences settings = c.getSharedPreferences(prefName, 0);
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

        log("readSPContent hm size:" + hm.size() + " length:" + length);
        return hm;
    }

    public static long readAcitiveProjectFromSP(Context c) {
        SharedPreferences settings = c.getSharedPreferences(PREFS_NAME, 0);
        long apid = settings.getLong(KEY_ACTIVE_PROJECT, -1);
        return apid;
    }

    public static void writeActiveProjectToSP(Context c, long apid) {
        writeLongToSP(c, KEY_ACTIVE_PROJECT, apid);
    }

    private static void writeLongToSP(Context c, String key, long val) {
        SharedPreferences settings = c.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor e = settings.edit();
        e.putLong(key, val);

        // Commit the edits!
        e.commit();
    }

    public static void setSPcontent(Context c, Set<String> vals) {
        writeToSharedPreference(c, KEY_PROJECTS, vals);
    }

    private static void writeToSharedPreference(Context c, String key, Set<String> vals) {
        SharedPreferences settings = c.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor e = settings.edit();
        e.putStringSet(key, vals);

        e.commit();
    }

    public static boolean isSameday(long lastCheckDate) {
        long l = lastCheckDate;
        if (l < 0)
            return false;

        Calendar calLastCheck = Calendar.getInstance();
        calLastCheck.setTimeInMillis(l);
        int lc_dayofyear = calLastCheck.get(Calendar.DAY_OF_YEAR);
        int lc_year = calLastCheck.get(Calendar.YEAR);

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        int today_dayofyear = cal.get(Calendar.DAY_OF_YEAR);
        int today_year = cal.get(Calendar.YEAR);

        return (lc_dayofyear == today_dayofyear) && (lc_year == today_year);
    }
}
