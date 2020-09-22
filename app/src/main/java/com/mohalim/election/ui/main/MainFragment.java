package com.mohalim.election.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mohalim.election.core.di.base.BaseFragment;
import com.mohalim.election.databinding.FragmentMainBinding;


public class MainFragment extends BaseFragment {
    private MainViewModel mViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentMainBinding binding = FragmentMainBinding.inflate(inflater, container,false);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        return binding.getRoot();
    }


}