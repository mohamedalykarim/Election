package com.mohalim.election.core.repositories;

import com.mohalim.election.core.dataSource.FirebaseDataSource;

import io.reactivex.rxjava3.core.Completable;

public class Repository {
    private static final String TAG = "Repository";

    FirebaseDataSource firebaseDataSource;

    public Repository(FirebaseDataSource firebaseDataSource) {
        this.firebaseDataSource = firebaseDataSource;
    }

    public Completable login(String username, String password) {
        return firebaseDataSource.login(username, password);
    }
}
