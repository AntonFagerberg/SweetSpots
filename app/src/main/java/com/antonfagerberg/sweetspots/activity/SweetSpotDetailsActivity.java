package com.antonfagerberg.sweetspots.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.antonfagerberg.sweetspots.R;
import com.antonfagerberg.sweetspots.fragment.DetailsFragment;
import com.antonfagerberg.sweetspots.model.SweetSpot;
import com.antonfagerberg.sweetspots.model.SweetSpotCollection;

import java.util.UUID;

public class SweetSpotDetailsActivity extends SingleFragmentActivity {
    private UUID sweetSpotUUID;

    @Override
    protected Fragment createFragment() {
        Fragment fragment = new DetailsFragment();
        Bundle args = new Bundle();

        sweetSpotUUID = (UUID) getIntent().getSerializableExtra(DetailsFragment.EXTRA_SWEET_SPOT_ID);
        args.putSerializable(DetailsFragment.EXTRA_SWEET_SPOT_ID, sweetSpotUUID);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_map:
                SweetSpot sweetSpot = SweetSpotCollection.get(this).get(sweetSpotUUID);
                Intent intent = new Intent(Intent.ACTION_VIEW);

                if (sweetSpot != null && intent.resolveActivity(getPackageManager()) != null) {
                    // Show the current location in a third-party map software.
                    String geoString = "geo:0,0?q=" + sweetSpot.getLatitude() + "," + sweetSpot.getLongitude() + "(" + sweetSpot.getTitle() + ")";
                    intent.setData(Uri.parse(geoString));
                    startActivity(intent);
                } else {
                    Toast.makeText(this, getString(R.string.map_fail), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.action_remove:
                final Activity currentActivity = this;

                // Display dialog to confirm removal of the current SweetSpot.
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(getString(R.string.confirm_delete))
                    .setPositiveButton(getString(R.string.dialog_yes), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            SweetSpotCollection.get(currentActivity).remove(sweetSpotUUID);
                            Toast.makeText(currentActivity, getString(R.string.sweet_spot_removed), Toast.LENGTH_SHORT).show();
                            currentActivity.finish();
                        }
                    })
                    .setNegativeButton(getString(R.string.dialog_no), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(currentActivity, getString(R.string.remove_canceled), Toast.LENGTH_SHORT).show();
                        }
                    });
                builder.create().show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
