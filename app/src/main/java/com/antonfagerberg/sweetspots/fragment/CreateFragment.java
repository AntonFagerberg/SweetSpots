package com.antonfagerberg.sweetspots.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.antonfagerberg.sweetspots.R;
import com.antonfagerberg.sweetspots.model.SweetSpot;
import com.antonfagerberg.sweetspots.model.SweetSpotCollection;

import java.util.UUID;

public class CreateFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(getString(R.string.create));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create, container, false);
    }

    public void setImage() {
        Log.d("test", "yolo");
    }
}
