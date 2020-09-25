package com.mohalim.election.core.di.activities.records;

import com.mohalim.election.ui.electors.ElectorsFragment;
import com.mohalim.election.ui.records.RecordsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RecordsFragmentsModule {
    @ContributesAndroidInjector
    abstract RecordsFragment contributeRecordsFragment();
}
