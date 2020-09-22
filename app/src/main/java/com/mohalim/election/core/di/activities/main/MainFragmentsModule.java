package com.mohalim.election.core.di.activities.main;

import com.mohalim.election.ui.main.MainFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentsModule {
    @ContributesAndroidInjector
    abstract MainFragment contributeMainFragment();
}
