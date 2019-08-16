package clear.chilli.clear;

import android.app.Application;

import cn.vlion.internation.ad.core.ADManager;

/**
 * Created by xiansong on 2017/9/29.
 */

public class VLionApplication extends Application {

//    private static final String vlionAppId = "30097";//842

    @Override
    public void onCreate() {
        super.onCreate();

        ADManager.getInstance().init(this, "30103",getResources().getString(R.string.G_id));

    }
}
