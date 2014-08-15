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


    // Not used yet


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }
}
