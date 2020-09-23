package com.mohalim.election.core.di.activities.electors;

import androidx.lifecycle.ViewModel;

import com.mohalim.election.core.di.key.ViewModelKey;
import com.mohalim.election.ui.electors.ElectorsViewModel;
import com.mohalim.election.ui.main.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ElectorsViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ElectorsViewModel.class)
    abstract ViewModel bindsElectorsViewModel(ElectorsViewModel electorsViewModel);

}
