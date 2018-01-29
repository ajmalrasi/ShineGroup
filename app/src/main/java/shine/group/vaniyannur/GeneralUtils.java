package shine.group.vaniyannur;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.DisplayMetrics;

/**
 * Created by kpajm on 15-05-2017.
 */

public class GeneralUtils {

    public static String formatName(String rawName){
        String lowerCaseName = rawName.toLowerCase();
        String[] splitName = lowerCaseName.split(" ");
        for (int i=0;i<splitName.length;i++){
            if (splitName[i].length()==2){
                splitName[i] = splitName[i].substring(0, 2).toUpperCase() + splitName[i].substring(2);
            }else {
                splitName[i] = splitName[i].substring(0, 1).toUpperCase() + splitName[i].substring(1);
            }
        }
        return TextUtils.join(" ",splitName);
    }


    public static int getScreenDpi(Activity a) {
        DisplayMetrics metrics = new DisplayMetrics();
        a.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        System.out.println(metrics.densityDpi);
        switch (metrics.densityDpi){
            case DisplayMetrics.DENSITY_LOW:
                return 160;
            case DisplayMetrics.DENSITY_MEDIUM:
                return 240;
            case DisplayMetrics.DENSITY_HIGH:
                return 260;
            case DisplayMetrics.DENSITY_XHIGH:
                return 280;
            case DisplayMetrics.DENSITY_XXHIGH:
                return 360;
            case DisplayMetrics.DENSITY_XXXHIGH:
                return 420;
            default:
                return 300;
        }
    }

}
