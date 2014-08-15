package com.antonfagerberg.sweetspots.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.antonfagerberg.sweetspots.R;
import com.antonfagerberg.sweetspots.model.SweetSpot;
import com.antonfagerberg.sweetspots.model.SweetSpotCollection;

import java.util.UUID;

public class DetailsFragment extends Fragment {
    public static final String EXTRA_SWEET_SPOT_ID = "com.antonfagerberg.sweetspot.sweet_spot_id";
    private SweetSpot mSweetSpot;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        UUID uuid = (UUID) getArguments().getSerializable(DetailsFragment.EXTRA_SWEET_SPOT_ID);
        mSweetSpot = SweetSpotCollection.get().getSweetSpot(uuid);
        getActivity().setTitle(getString(R.string.details));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        TextView titleTextView = (TextView) view.findViewById(R.id.sweetSpotDetailsTitle);
        titleTextView.setText(mSweetSpot.getTitle());

        TextView descriptionTextView = (TextView) view.findViewById(R.id.sweetSpotDetailsDescription);
        descriptionTextView.setText(mSweetSpot.getDescription());

        return view;
    }
}
