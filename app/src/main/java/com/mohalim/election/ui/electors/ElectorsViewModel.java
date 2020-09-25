package com.mohalim.election.ui.electors;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.mohalim.election.core.models.Elector;
import com.mohalim.election.core.repositories.Repository;
import com.mohalim.election.databinding.FragmentElectorsBinding;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Reusable
public class ElectorsViewModel extends ViewModel {
    @Inject
    Repository repository;

    @Inject
    public ElectorsViewModel() {
    }

    public void addNewElector(Context context, FragmentElectorsBinding binding, Elector elector) {
        repository.addNewElector(elector)
        .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(context, "بالتوفيق، تم اضافة الناخب بنجاح ...", Toast.LENGTH_SHORT).show();
                        binding.idET.setText("");
                        binding.nameET.setText("");
                        binding.phoneET.setText("");
                        binding.familyET.setText("");
                        binding.regionET.setText("");
                        binding.loading.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        binding.loading.setVisibility(View.GONE);
                    }
                });
    }
}