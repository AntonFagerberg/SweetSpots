package com.antonfagerberg.sweetspots.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.antonfagerberg.sweetspots.R;
import com.antonfagerberg.sweetspots.helper.ImageHelper;
import com.antonfagerberg.sweetspots.model.SweetSpot;
import com.antonfagerberg.sweetspots.model.SweetSpotCollection;

import java.io.File;
import java.util.UUID;

public class DetailsFragment extends Fragment {
    public static final String EXTRA_SWEET_SPOT_ID = "com.antonfagerberg.sweetspot.sweet_spot_id";
    private SweetSpot mSweetSpot;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        UUID uuid = (UUID) getArguments().getSerializable(DetailsFragment.EXTRA_SWEET_SPOT_ID);
        mSweetSpot = SweetSpotCollection.get(getActivity()).get(uuid);
        getActivity().setTitle(getString(R.string.details));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        TextView titleTextView = (TextView) view.findViewById(R.id.sweetSpotDetailsTitle);
        titleTextView.setText(mSweetSpot.getTitle());

        TextView descriptionTextView = (TextView) view.findViewById(R.id.sweetSpotDetailsDescription);
        descriptionTextView.setText(mSweetSpot.getDescription());

        final FrameLayout imageFrame = (FrameLayout) view.findViewById(R.id.sweetSpotDetailsImageFrame);
        final ImageView imageView = (ImageView) view.findViewById(R.id.sweetSpotDetailsImage);

        imageFrame.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                imageFrame.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                Uri imageUri = mSweetSpot.getUri();

                if (imageUri != null && (new File(imageUri.getPath())).isFile()) {
                    Bitmap scaledBitmap = ImageHelper.resize(imageUri, imageFrame.getWidth());
                    imageView.setImageBitmap(scaledBitmap);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
            }
        });

        return view;
    }
}
