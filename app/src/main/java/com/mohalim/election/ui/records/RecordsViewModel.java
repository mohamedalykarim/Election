package com.mohalim.election.ui.records;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mohalim.election.core.models.Elector;
import com.mohalim.election.core.repositories.Repository;
import com.mohalim.election.databinding.FragmentRecordsBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.operators.observable.ObservableCache;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Reusable
public class RecordsViewModel extends ViewModel {
    private final String TAG = "RecordsViewModel";
    @Inject
    Repository repository;
    List<Elector> electors = new ArrayList<>();

    @Inject
    public RecordsViewModel() {
    }

    public void initRecyclerView(Context context, FragmentRecordsBinding binding) {
        RecordsRecyclerViewAdapter adapter = new RecordsRecyclerViewAdapter();
        adapter.setElectorList(electors);

        binding.recordsRV.setLayoutManager(new LinearLayoutManager(context));
        binding.recordsRV.setHasFixedSize(true);
        binding.recordsRV.setAdapter(adapter);
        this.getRecords(adapter);
    }

    private void getRecords(RecordsRecyclerViewAdapter adapter) {
        repository.getRecords()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .toObservable()
        .subscribe(new Observer<List<Elector>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<Elector> newElectors) {
                electors.clear();
                electors.addAll(newElectors);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}