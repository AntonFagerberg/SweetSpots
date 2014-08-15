package com.antonfagerberg.sweetspots.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class SweetSpotCollection {
    private static SweetSpotCollection sSweetSpotCollection;
    private ArrayList<SweetSpot> mSweetSpots;

    private SweetSpotCollection() {
        mSweetSpots = new ArrayList<SweetSpot>();

        Random r = new Random();

        for (int i = 0; i < 100; i++) {
            SweetSpot s = new SweetSpot(String.valueOf(r.nextLong()), "Now that CrimeFragment fetches a Crime, its view can display that Crime’s data. Update onCreateView(…) to display the Crime’s title and solved status. (The code for displaying the date is already in place.) Excerpt From: Brian Hardy. “Android Programming: The Big Nerd Ranch Guide.” iBooks.");
            mSweetSpots.add(s);
        }
    }

    public static SweetSpotCollection get() {
        if (sSweetSpotCollection == null) {
            sSweetSpotCollection = new SweetSpotCollection();
        }

        return sSweetSpotCollection;
    }

    public ArrayList<SweetSpot> getSweetSpots() {
        return mSweetSpots;
    }

    public SweetSpot getSweetSpot(UUID id) {
        for (SweetSpot s : mSweetSpots) {
            if (s.getId().equals(id)) {
                return s;
            }
        }

        return null;
    }
}
