package com.mohalim.election.core.di.activities.records;

import androidx.lifecycle.ViewModel;

import com.mohalim.election.core.di.key.ViewModelKey;
import com.mohalim.election.ui.electors.ElectorsViewModel;
import com.mohalim.election.ui.records.RecordsViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class RecordsViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(RecordsViewModel.class)
    abstract ViewModel bindsRecordsViewModel(RecordsViewModel recordsViewModel);

}
