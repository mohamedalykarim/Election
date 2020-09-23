package com.mohalim.election.ui.electors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.mohalim.election.R;
import com.mohalim.election.core.di.base.BaseActivity;
import com.mohalim.election.databinding.ActivityElectorsBinding;
import com.mohalim.election.ui.electors.ElectorsFragment;

public class ElectorsActivity extends BaseActivity {
    ActivityElectorsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_electors);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ElectorsFragment.newInstance())
                    .commitNow();
        }
    }
}