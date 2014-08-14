package com.antonfagerberg.sweetspots;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SweetSpotListFragment extends ListFragment {
    private ArrayList<SweetSpot> mSweetSpots;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get host activity
        getActivity().setTitle("SweetSpots");

        mSweetSpots = SweetSpotCollection.get().getSweetSpots();

        SweetSpotAdapter adapter = new SweetSpotAdapter(mSweetSpots);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        SweetSpot sweetSpot = (SweetSpot) (getListAdapter()).getItem(position);
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

            TextView titleTextView = (TextView) convertView.findViewById(R.id.sweetSpotListItemTitleTextView);
            titleTextView.setText(sweetSpot.getTitle());

            TextView descriptionTextView = (TextView) convertView.findViewById(R.id.sweetSpotListItemDescriptionTextView);
            descriptionTextView.setText("This is a very long hard coded description which I will change later");

            return convertView;
        }
    }
}
