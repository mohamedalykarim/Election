package com.mohalim.election.ui.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.mohalim.election.core.repositories.Repository;
import com.mohalim.election.databinding.FragmentMainBinding;
import com.mohalim.election.ui.electors.ElectorsActivity;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Reusable
public class MainViewModel extends ViewModel {
    private static final String TAG = "MainViewModel";

    @Inject
    Repository repository;

    @Inject
    public MainViewModel() {
    }

    public void login(Context context, FragmentMainBinding binding, String username, String password) {
        repository.login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(context, "تم تسجيل الدخول", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, ElectorsActivity.class);
                        context.startActivity(intent);
                        ((Activity)context).finish();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        binding.progressBar.setVisibility(View.GONE);

                    }
                });
    }
}