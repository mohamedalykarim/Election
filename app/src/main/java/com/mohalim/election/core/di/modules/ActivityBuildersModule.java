package com.mohalim.election.core.di.modules;

import com.mohalim.election.core.di.activities.main.MainFragmentsModule;
import com.mohalim.election.core.di.activities.main.MainViewModelsModule;
import com.mohalim.election.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {
                    MainFragmentsModule.class,
                    MainViewModelsModule.class
            }
    )
    abstract MainActivity contributeMainActivity();
}
