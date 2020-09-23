package com.mohalim.election.core.di.activities.electors;

import com.mohalim.election.ui.electors.ElectorsFragment;
import com.mohalim.election.ui.main.MainFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ElectorsFragmentsModule {
    @ContributesAndroidInjector
    abstract ElectorsFragment contributeElectorsFragment();
}
