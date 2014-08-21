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

    /**
     * Private constructor to create a new collection.
     *
     * @param context Application context.
     */
    private SweetSpotCollection(Context context) {
        mSerializer = new SweetSpotJSONSerializer(context, FILENAME);

        try {
            mSweetSpots = mSerializer.loadSweetSpots();
        } catch (Exception exception) {
            Log.e(TAG, "Error loading crimes: ", exception);
        }
    }

    /**
     * Public singleton constructor.
     *
     * @param context Application context.
     * @return Singleton instance of the collection.
     */
    public static SweetSpotCollection get(Context context) {
        if (sSweetSpotCollection == null) {
            sSweetSpotCollection = new SweetSpotCollection(context.getApplicationContext());
        }

        return sSweetSpotCollection;
    }

    /**
     * Get all SweetSpots.
     *
     * @return All SweetSpots.
     */
    public ArrayList<SweetSpot> getSweetSpots() {
        return mSweetSpots;
    }

    /**
     * Add a new SweetSpot to the collection.
     *
     * @param sweetSpot SweetSpot to add to the collection.
     */
    public void add(SweetSpot sweetSpot) {
        mSweetSpots.add(sweetSpot);
    }

    /**
     * Remove a SweetSpot from the collection.
     *
     * @param uuid UUID of the SweetSpot to remove.
     */
    public void remove(UUID uuid) {
        for (int i = 0; i < mSweetSpots.size(); i++) {
            if (mSweetSpots.get(i).getUUID().equals(uuid)) {
                mSweetSpots.remove(i);
                return;
            }
        }
    }

    /**
     * Find a specific SweetSpot.
     *
     * @param uuid UUID of the SweetSpot to find.
     * @return SweetSpot or null.
     */
    public SweetSpot get(UUID uuid) {
        for (SweetSpot s : mSweetSpots) {
            if (s.getUUID().equals(uuid)) {
                return s;
            }
        }

        return null;
    }

    /**
     * Persist all SweetSpots to disk.
     *
     * @return Was the operation successful?
     */
    public boolean saveSweetSpots() {
        try {
            mSerializer.saveSweetSpots(mSweetSpots);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
