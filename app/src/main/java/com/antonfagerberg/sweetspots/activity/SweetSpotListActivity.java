package com.antonfagerberg.sweetspots.activity;

import android.app.Fragment;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import com.antonfagerberg.sweetspots.R;
import com.antonfagerberg.sweetspots.fragment.ListFragment;

public class SweetSpotListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ListFragment();
    }


    // Not used yet


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
        switch (item.getItemId()) {
            case R.id.action_create:
                Intent intent = new Intent(this, SweetSpotCreateActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
