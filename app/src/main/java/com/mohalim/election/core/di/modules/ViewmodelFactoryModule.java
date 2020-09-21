package com.mohalim.election.core.di.modules;

import androidx.lifecycle.ViewModelProvider;


import com.mohalim.election.core.utils.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewmodelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory factory);
}
