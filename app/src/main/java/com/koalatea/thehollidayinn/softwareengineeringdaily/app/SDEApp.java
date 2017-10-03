package com.koalatea.thehollidayinn.softwareengineeringdaily.app;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

import com.akaita.java.rxjava2debug.RxJava2Debug;
import com.koalatea.thehollidayinn.softwareengineeringdaily.BuildConfig;

import timber.log.Timber;

/**
 * Created by Kurian on 25-Sep-17.
 */

public class SDEApp extends Application {

    @VisibleForTesting
    public static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        initDependencies();
        createLogger();
        // Enable RxJava assembly stack collection, to make RxJava crash reports clear and unique
        // Make sure this is called AFTER setting up any Crash reporting mechanism as Crashlytics
        RxJava2Debug.enableRxJava2AssemblyTracking(new String[]{BuildConfig.APPLICATION_ID});
    }

    private void createLogger() {
        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void initDependencies() {
        if(component == null) {
            component = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
    }

    public static AppComponent component() {
        return component;
    }
}
