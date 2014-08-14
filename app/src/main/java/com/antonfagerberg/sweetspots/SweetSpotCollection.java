package com.antonfagerberg.sweetspots;

import java.util.ArrayList;
import java.util.Random;

public class SweetSpotCollection {
    private static SweetSpotCollection sSweetSpotCollection;
    private ArrayList<SweetSpot> mSweetSpots;

    private SweetSpotCollection() {
        mSweetSpots = new ArrayList<SweetSpot>();

        Random r = new Random();

        for (int i = 0; i < 100; i++) {
            SweetSpot s = new SweetSpot(String.valueOf(r.nextLong()), String.valueOf(r.nextLong()));
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
}
