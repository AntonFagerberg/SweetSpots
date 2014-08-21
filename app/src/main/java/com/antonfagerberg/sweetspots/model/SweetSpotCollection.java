package com.antonfagerberg.sweetspots.model;

import android.content.Context;
import android.util.Log;

import com.antonfagerberg.sweetspots.helper.SweetSpotJSONSerializer;

import java.util.ArrayList;
import java.util.UUID;

public class SweetSpotCollection {
    private static SweetSpotCollection sSweetSpotCollection;
    private ArrayList<SweetSpot> mSweetSpots;
    private SweetSpotJSONSerializer mSerializer;
    private static final String
        TAG = "SweetSpotCollection",
        FILENAME = "sweetspots.json";

    private SweetSpotCollection(Context context) {
        mSerializer = new SweetSpotJSONSerializer(context, FILENAME);

        try {
            mSweetSpots = mSerializer.loadSweetSpots();
        } catch (Exception exception) {
            Log.e(TAG, "Error loading crimes: ", exception);
        }
    }

    public static SweetSpotCollection get(Context context) {
        if (sSweetSpotCollection == null) {
            sSweetSpotCollection = new SweetSpotCollection(context.getApplicationContext());
        }

        return sSweetSpotCollection;
    }

    public ArrayList<SweetSpot> getSweetSpots() {
        return mSweetSpots;
    }

    public void add(SweetSpot sweetSpot) {
        mSweetSpots.add(sweetSpot);
    }

    public SweetSpot get(UUID id) {
        for (SweetSpot s : mSweetSpots) {
            if (s.getId().equals(id)) {
                return s;
            }
        }

        return null;
    }

    public boolean saveCrimes() {
        try {
            mSerializer.saveSweetSpots(mSweetSpots);
            Log.i(TAG, "Saved SweetSpots to file!");
            return true;
        } catch (Exception exception) {
            Log.e(TAG, "Error saving SweetSpots: ", exception);
            return false;
        }
    }
}
