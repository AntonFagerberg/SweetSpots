package com.antonfagerberg.sweetspots.activity;

import android.app.Fragment;
import android.view.Menu;

import com.antonfagerberg.sweetspots.R;
import com.antonfagerberg.sweetspots.fragment.CreateFragment;

public class SweetSpotCreateActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CreateFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create, menu);
        return true;
    }
}
