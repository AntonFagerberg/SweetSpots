package com.antonfagerberg.sweetspots.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.antonfagerberg.sweetspots.R;
import com.antonfagerberg.sweetspots.activity.SweetSpotDetailsActivity;
import com.antonfagerberg.sweetspots.helper.ImageHelper;
import com.antonfagerberg.sweetspots.model.SweetSpot;
import com.antonfagerberg.sweetspots.model.SweetSpotCollection;

import java.io.File;
import java.util.ArrayList;

public class ListFragment extends android.app.ListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(getString(R.string.app_name));

        ArrayList<SweetSpot> mSweetSpots = SweetSpotCollection.get(getActivity()).getSweetSpots();

        SweetSpotAdapter adapter = new SweetSpotAdapter(mSweetSpots);
        setListAdapter(adapter);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Persist SweetSpots to disk.
        SweetSpotCollection.get(getActivity()).saveSweetSpots();
    }

    @Override
    public void onResume() {
        super.onResume();
        setEmptyText(getString(R.string.list_empty));
        ((SweetSpotAdapter) getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        // View a specific SweetSpot.
        SweetSpot sweetSpot = (SweetSpot) (getListAdapter()).getItem(position);
        Intent intent = new Intent(getActivity(), SweetSpotDetailsActivity.class);
        intent.putExtra(DetailsFragment.EXTRA_SWEET_SPOT_ID, sweetSpot.getUUID());
        startActivity(intent);
    }

    private class SweetSpotAdapter extends ArrayAdapter<SweetSpot> {
        public SweetSpotAdapter(ArrayList<SweetSpot> sweetSpots) {
            super(getActivity(), 0, sweetSpots);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_sweet_spot, null);
            }

            SweetSpot sweetSpot = getItem(position);
            Uri imageUri = sweetSpot.getUri();
            ImageView imageView = (ImageView) convertView.findViewById(R.id.sweetSpotListItemImageView);

            // Use the image stored on disk if present - or use default placeholder.
            if (imageUri != null && (new File(imageUri.getPath())).isFile()) {
                Bitmap scaledBitmap = ImageHelper.resize(imageUri, 60);
                imageView.setImageBitmap(scaledBitmap);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                imageView.setImageResource(R.drawable.ic_action_picture);
            }

            TextView titleTextView = (TextView) convertView.findViewById(R.id.sweetSpotListItemTitleTextView);
            titleTextView.setText(sweetSpot.getTitle());

            TextView descriptionTextView = (TextView) convertView.findViewById(R.id.sweetSpotListItemDescriptionTextView);
            descriptionTextView.setText(sweetSpot.getDescription());

            return convertView;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.details, menu);
    }
}
