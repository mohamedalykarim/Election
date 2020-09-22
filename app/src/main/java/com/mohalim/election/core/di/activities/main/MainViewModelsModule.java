package com.mohalim.election.core.di.activities.main;

import androidx.lifecycle.ViewModel;

import com.mohalim.election.core.di.key.ViewModelKey;
import com.mohalim.election.ui.main.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindsMainViewModel(MainViewModel mainViewModel);

}
