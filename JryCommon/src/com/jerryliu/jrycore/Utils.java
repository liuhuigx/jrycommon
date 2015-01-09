
package com.jerryliu.jrycore;

import java.util.Calendar;

import com.jerryliu.jrycore.log.Liu;

public class Utils {

    public static void log(String l) {
        Liu.d(l);
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
