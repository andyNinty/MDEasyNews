package andy.lee.mdeasynews.lib;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import andy.lee.mdeasynews.constant.Constant;
import andy.lee.mdeasynews.utils.LogUtil;
import andy.lee.mdeasynews.utils.SharedPreferencesUtil;

/**
 * andy.lee.mdeasynews.lib
 * Created by andy on 16-12-8.
 */

public class APP extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        boolean mode = SharedPreferencesUtil.getBoolean(this, Constant.MODE, false);
        LogUtil.e("mode =" + mode);
        if (mode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
