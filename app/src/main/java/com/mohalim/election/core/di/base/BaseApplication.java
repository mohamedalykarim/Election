package com.mohalim.election.core.di.base;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;

import androidx.multidex.MultiDex;

import com.mohalim.election.core.di.components.DaggerAppComponent;
import com.mohalim.election.core.utils.Constants;
import com.pixplicity.easyprefs.library.Prefs;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class BaseApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        DaggerAppComponent.builder().getInstance((Application) getApplicationContext()).build().inject(this);

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(Constants.PREFERENCE_NAME)
                .setUseDefaultSharedPreference(true)
                .build();

        return DaggerAppComponent.builder().getInstance(this).build();
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
