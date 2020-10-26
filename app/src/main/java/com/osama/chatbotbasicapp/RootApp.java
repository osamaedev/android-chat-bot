package com.osama.chatbotbasicapp;

import android.app.Application;

import com.osama.mobioptionsads.MobiInitializationListener;
import com.osama.mobioptionsads.MobiOptionsAdsInit;

import org.jetbrains.annotations.NotNull;

public class RootApp extends Application {

    private static MobiOptionsAdsInit mobiOptionsAdsInit;

    public static RootApp rootApplication;


    public static synchronized void setupMobiOptionsAds(@NotNull MobiInitializationListener listener) {
        if (mobiOptionsAdsInit == null) {
            mobiOptionsAdsInit = new MobiOptionsAdsInit(rootApplication, "TJ6N6Wy8aZsc9oWW92TuXlIZwsGtj7", listener);
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    public RootApp() {
        super();
        rootApplication = this;
    }

}
