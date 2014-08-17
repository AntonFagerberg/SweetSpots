package com.antonfagerberg.sweetspots.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.antonfagerberg.sweetspots.R;
import com.antonfagerberg.sweetspots.model.SweetSpot;
import com.antonfagerberg.sweetspots.model.SweetSpotCollection;

public class CreateFragment extends Fragment {
    private ImageView imageView;
    private FrameLayout imageFrame;
    private Uri imageUri;
    private TextView titleView, descriptionView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(getString(R.string.create));
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
                SweetSpot sweetSpot = new SweetSpot(titleView.getText().toString(), descriptionView.getText().toString(), imageUri);
                SweetSpotCollection.get(getActivity()).add(sweetSpot);
                getActivity().finish();
            }
        });
        return view;
    }

    public void setImage(Uri imageUri) {
        this.imageUri = imageUri;
        Bitmap imageBitmap = BitmapFactory.decodeFile(imageUri.getPath());

        int targetWidth = imageFrame.getWidth();
        int originalWidth = imageBitmap.getWidth();
        float scaleFactor = (float) targetWidth / originalWidth;
        int targetHeight = (int) (imageBitmap.getHeight() * scaleFactor);

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(imageBitmap, targetWidth, targetHeight, true);

        imageView.setImageBitmap(scaledBitmap);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }
}
