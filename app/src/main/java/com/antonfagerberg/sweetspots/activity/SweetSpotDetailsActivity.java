package com.antonfagerberg.sweetspots.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.antonfagerberg.sweetspots.R;
import com.antonfagerberg.sweetspots.fragment.DetailsFragment;
import com.antonfagerberg.sweetspots.fragment.ListFragment;

import java.util.UUID;

public class SweetSpotDetailsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        Fragment fragment = new DetailsFragment();

        Bundle args = new Bundle();
        UUID sweetSpotId = (UUID) getIntent().getSerializableExtra(DetailsFragment.EXTRA_SWEET_SPOT_ID);
        args.putSerializable(DetailsFragment.EXTRA_SWEET_SPOT_ID, sweetSpotId);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
