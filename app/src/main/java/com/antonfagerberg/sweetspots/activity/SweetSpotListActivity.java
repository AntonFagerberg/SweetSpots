package com.antonfagerberg.sweetspots.activity;

import android.app.Fragment;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import com.antonfagerberg.sweetspots.R;
import com.antonfagerberg.sweetspots.fragment.ListFragment;

public class SweetSpotListActivity extends SingleFragmentActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list, menu);
        return true;
    }

    @Override
    protected Fragment createFragment() {
        return new ListFragment();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create:
                Intent intent = new Intent(this, SweetSpotCreateActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
