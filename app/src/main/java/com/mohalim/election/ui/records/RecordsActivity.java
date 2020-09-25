package com.mohalim.election.ui.records;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.mohalim.election.R;
import com.mohalim.election.core.di.base.BaseActivity;
import com.mohalim.election.databinding.ActivityElectorsBinding;
import com.mohalim.election.databinding.ActivityRecordsBinding;
import com.mohalim.election.ui.records.RecordsFragment;

public class RecordsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRecordsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_records);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, RecordsFragment.newInstance())
                    .commitNow();
        }
    }
}