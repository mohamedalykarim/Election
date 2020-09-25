package com.mohalim.election.core.repositories;

import com.mohalim.election.core.dataSource.FirebaseDataSource;
import com.mohalim.election.core.models.Elector;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class Repository {
    private static final String TAG = "Repository";

    FirebaseDataSource firebaseDataSource;

    public Repository(FirebaseDataSource firebaseDataSource) {
        this.firebaseDataSource = firebaseDataSource;
    }

    public Completable login(String username, String password) {
        return firebaseDataSource.login(username, password);
    }

    public Completable addNewElector(Elector elector) {
        return firebaseDataSource.addNewElector(elector);
    }

    public @NonNull Flowable<List<Elector>> getRecords() {
        return firebaseDataSource.getRecords();
    }
}
