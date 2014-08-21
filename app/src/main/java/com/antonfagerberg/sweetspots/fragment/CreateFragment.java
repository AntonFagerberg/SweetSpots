package com.antonfagerberg.sweetspots.fragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.antonfagerberg.sweetspots.R;
import com.antonfagerberg.sweetspots.helper.ImageHelper;
import com.antonfagerberg.sweetspots.model.SweetSpot;
import com.antonfagerberg.sweetspots.model.SweetSpotCollection;

public class CreateFragment extends Fragment {
    private ImageView imageView;
    private FrameLayout imageFrame;
    private Uri imageUri;
    private TextView titleView, descriptionView;
    private double longitude = 0d, latitude = 0d;
    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(getString(R.string.create));

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                longitude = location.getLongitude();
                latitude = location.getLatitude();
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        locationManager.removeUpdates(locationListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create, container, false);

        imageFrame = (FrameLayout) view.findViewById(R.id.sweetSpotCreateImageFrame);
        imageView = (ImageView) view.findViewById(R.id.sweetSpotCreateImage);
        titleView = (TextView) view.findViewById(R.id.sweetSpotCreateTitle);
        descriptionView = (TextView) view.findViewById(R.id.sweetSpotCreateDescription);

        (view.findViewById(R.id.sweetSpotCreateSaveButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SweetSpot sweetSpot = new SweetSpot(titleView.getText().toString(), descriptionView.getText().toString(), imageUri, longitude, latitude);
                SweetSpotCollection.get(getActivity()).add(sweetSpot);
                getActivity().finish();
            }
        });
        return view;
    }

    public void setImage(Uri imageUri) {
        this.imageUri = imageUri;
        Bitmap scaledBitmap = ImageHelper.resize(imageUri, imageFrame.getWidth());

        imageView.setImageBitmap(scaledBitmap);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }
}
