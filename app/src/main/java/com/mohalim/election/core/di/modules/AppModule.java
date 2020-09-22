package com.mohalim.election.core.di.modules;

import com.mohalim.election.core.dataSource.FirebaseDataSource;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    static FirebaseDataSource provideFirebaseDataSource(){
        return new FirebaseDataSource();
    }

}
