package com.mohalim.election.core.di.modules;

import android.app.Application;

import com.mohalim.election.core.dataSource.FirebaseDataSource;
import com.mohalim.election.core.repositories.Repository;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    static FirebaseDataSource provideFirebaseDataSource(Application application){
        return new FirebaseDataSource(application);
    }

    @Provides
    static Repository provideRepository(FirebaseDataSource firebaseDataSource){
        return new Repository(firebaseDataSource);
    }

}
