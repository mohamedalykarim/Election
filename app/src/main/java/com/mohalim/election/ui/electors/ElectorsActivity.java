package com.mohalim.election.ui.electors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mohalim.election.R;
import com.mohalim.election.ui.electors.ElectorsFragment;

public class ElectorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electors);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ElectorsFragment.newInstance())
                    .commitNow();
        }
    }
}