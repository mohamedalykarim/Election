package com.mohalim.election.ui.records;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mohalim.election.R;
import com.mohalim.election.core.di.base.BaseFragment;
import com.mohalim.election.core.models.Elector;
import com.mohalim.election.core.utils.Constants;
import com.mohalim.election.core.utils.ViewModelProviderFactory;
import com.mohalim.election.databinding.FragmentRecordsBinding;
import com.mohalim.election.ui.main.MainActivity;
import com.pixplicity.easyprefs.library.Prefs;

import javax.inject.Inject;

public class RecordsFragment extends BaseFragment {
    private static final String TAG = "RecordsFragment";
    @Inject
    ViewModelProviderFactory factory;
    private RecordsViewModel mViewModel;
    FragmentRecordsBinding binding;


    public static RecordsFragment newInstance() {
        return new RecordsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRecordsBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this, factory).get(RecordsViewModel.class);

        mViewModel.initRecyclerView(getContext(), binding);
        binding.nameTV.setText(Prefs.getString(Constants.NAME, ""));
        binding.logoutTv.setOnClickListener(this);

        return binding.getRoot();
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);

        if (view.getId() == binding.logoutTv.getId()){
            Prefs.remove(Constants.NAME);
            Prefs.remove(Constants.USER_NAME);

            Toast.makeText(getContext(), "تم تسجيل الخروج", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), MainActivity.class);
            getActivity().startActivity(intent);
            getActivity().finish();

        }
    }



}