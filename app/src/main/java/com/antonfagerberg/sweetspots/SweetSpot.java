package com.antonfagerberg.sweetspots;

import java.util.UUID;

public class SweetSpot {
    private UUID mId;
    private String
        mTitle,
        mDescription;

    public SweetSpot(String title, String description) {
        mTitle = title;
        mDescription = description;
        mId = UUID.randomUUID();
    }

    public String toString() {
        return mTitle;
    }

    public String getTitle() {
        return mTitle;
    }
}
