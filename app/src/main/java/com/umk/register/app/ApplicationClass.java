package com.umk.register.app;

import android.app.Application;
import android.content.Context;

import com.onesignal.OneSignal;

public class ApplicationClass extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationClass.context = getApplicationContext();
        OneSignal.startInit(this)
                    .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                    .unsubscribeWhenNotificationsAreDisabled(true)
                    .init();
        }

    public static Context getAppContext(){
        return ApplicationClass.context;
    }

}
