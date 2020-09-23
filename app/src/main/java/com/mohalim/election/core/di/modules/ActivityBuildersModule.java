package com.mohalim.election.core.di.modules;

import com.mohalim.election.core.di.activities.electors.ElectorsFragmentsModule;
import com.mohalim.election.core.di.activities.electors.ElectorsViewModelsModule;
import com.mohalim.election.core.di.activities.main.MainFragmentsModule;
import com.mohalim.election.core.di.activities.main.MainViewModelsModule;
import com.mohalim.election.ui.electors.ElectorsActivity;
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

    @ContributesAndroidInjector(
            modules = {
                    ElectorsFragmentsModule.class,
                    ElectorsViewModelsModule.class
            }
    )
    abstract ElectorsActivity contributeElectorsActivity();
}
