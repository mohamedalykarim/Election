package com.mohalim.election.ui.electors;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mohalim.election.R;
import com.mohalim.election.core.di.base.BaseFragment;
import com.mohalim.election.core.models.Elector;
import com.mohalim.election.core.utils.Constants;
import com.mohalim.election.core.utils.ViewModelProviderFactory;
import com.mohalim.election.databinding.FragmentElectorsBinding;
import com.mohalim.election.ui.main.MainActivity;
import com.mohalim.election.ui.main.MainViewModel;
import com.pixplicity.easyprefs.library.Prefs;

import javax.inject.Inject;

public class ElectorsFragment extends BaseFragment {
    private static final String TAG = "ElectorsFragment";
    @Inject
    ViewModelProviderFactory factory;

    private ElectorsViewModel mViewModel;
    private FragmentElectorsBinding binding;
    private int errors = 0;

    public static ElectorsFragment newInstance() {
        return new ElectorsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentElectorsBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this, factory).get(ElectorsViewModel.class);

        binding.nameTV.setText(Prefs.getString(Constants.NAME, ""));
        binding.logoutTv.setOnClickListener(this);
        binding.newElectorBtn.setOnClickListener(this);
        binding.recordsBtn.setOnClickListener(this);
        binding.resetBtn.setOnClickListener(this);

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

        }else if (view.getId() == binding.resetBtn.getId()){
            binding.idET.setText("");
            binding.nameET.setText("");
            binding.phoneET.setText("");
            binding.familyET.setText("");
        }else if (view.getId() == binding.recordsBtn.getId()){
            // Records
        }else if (view.getId() == binding.newElectorBtn.getId()){
            validate();
            if (errors > 0) return;
            binding.loading.setVisibility(View.VISIBLE);
            Elector elector = new Elector();
            elector.setId(binding.idET.getText().toString());
            elector.setName(binding.nameET.getText().toString());
            elector.setPhone(binding.phoneET.getText().toString());
            elector.setFamily(binding.familyET.getText().toString());

            mViewModel.addNewElector(getActivity(),binding, elector);
        }
    }

    private void validate() {
        errors = 0;
        if (binding.idET.getText().toString().equals("")){
            binding.idET.setError("يجب ادخال الرقم القومي بشكل صحصح");
            errors++;
        }

        if (binding.idET.getText().toString().length() > 14 || binding.idET.getText().toString().length() < 14){
            binding.idET.setError("يجب ادخال الرقم القومي بشكل صحصح");
            errors++;
        }

        if (binding.nameET.getText().toString().equals("")){
            binding.nameET.setError("يجب ادخال الاسم بشكل صحصح");
            errors++;
        }

        if (binding.familyET.getText().toString().equals("")){
            binding.familyET.setError("يجب ادخال العائلة بشكل صحيح");
            errors++;
        }
    }
}