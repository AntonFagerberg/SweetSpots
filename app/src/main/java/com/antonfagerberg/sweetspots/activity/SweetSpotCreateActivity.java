package com.antonfagerberg.sweetspots.activity;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.antonfagerberg.sweetspots.R;
import com.antonfagerberg.sweetspots.fragment.CreateFragment;
import com.antonfagerberg.sweetspots.helper.MediaHelper;

public class SweetSpotCreateActivity extends SingleFragmentActivity {
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;

    @Override
    protected Fragment createFragment() {
        return new CreateFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_camera:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                fileUri = MediaHelper.getOutputMediaFileUri(MediaHelper.MEDIA_TYPE_IMAGE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Image saved to:\n", Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "cancel", Toast.LENGTH_LONG).show();
            } else {
                // Image capture failed, advise user
                Toast.makeText(this, "capture fail", Toast.LENGTH_LONG).show();
            }
        }
    }
}
