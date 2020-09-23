package com.mohalim.election.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mohalim.election.ui.electors.ElectorsActivity;
import com.mohalim.election.core.di.base.BaseFragment;
import com.mohalim.election.core.utils.Constants;
import com.mohalim.election.core.utils.ViewModelProviderFactory;
import com.mohalim.election.databinding.FragmentMainBinding;
import com.pixplicity.easyprefs.library.Prefs;

import javax.inject.Inject;


public class MainFragment extends BaseFragment {
    private static final String TAG = "MainFragment";

    @Inject
    ViewModelProviderFactory factory;

    FragmentMainBinding binding;
    private MainViewModel mViewModel;

    int errors = 0;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container,false);
        mViewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);

        binding.loginBtn.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!Prefs.getString(Constants.USER_NAME, "").equals("")){
            Intent intent = new Intent(getActivity(), ElectorsActivity.class);
            getActivity().startActivity(intent);
            getActivity().finish();
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);


        if (view.getId() == binding.loginBtn.getId()){
            errors = 0;
            loginValidate();

            if (errors == 0){
                mViewModel.login(getContext(), binding.usernameET.getText().toString(), binding.passwordET.getText().toString());
            }
        }
    }



    private void loginValidate() {
        if (binding.usernameET.getText().toString().equals("")){
            binding.usernameET.setError("رجاء ادخال اسم المستخدم");
            errors++;
        }

        if (binding.passwordET.getText().toString().equals("")){
            binding.passwordET.setError("رجاء ادخال الرقم السري");
            errors++;
        }
    }
}