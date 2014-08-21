package com.antonfagerberg.sweetspots.model;

import android.net.Uri;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public class SweetSpot {
    private UUID mUuid;
    private String
        mTitle,
        mDescription;
    private Uri mImageUri;
    private double
        longitude = 0d,
        latitude = 0d;
    private static final String
        JSON_ID = "id",
        JSON_TITLE = "title",
        JSON_DESCRIPTION = "description",
        JSON_IMAGE_URI = "image_uri",
        JSON_LATITUDE = "latitude",
        JSON_LONGITUDE = "longitude";

    public SweetSpot(String title, String description, Uri imageUri, double longitude, double latitude) {
        mTitle = title;
        mDescription = description;
        mUuid = UUID.randomUUID();
        mImageUri = imageUri;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public SweetSpot(JSONObject jsonObject) throws JSONException {
        mUuid = UUID.fromString(jsonObject.getString(JSON_ID));
        mTitle = jsonObject.getString(JSON_TITLE);
        mDescription = jsonObject.getString(JSON_DESCRIPTION);
        String imageUri = jsonObject.getString(JSON_IMAGE_URI);
        mImageUri = imageUri.equals("") ? null : Uri.parse(imageUri);
        longitude = jsonObject.getDouble(JSON_LONGITUDE);
        latitude = jsonObject.getDouble(JSON_LATITUDE);
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(JSON_ID, mUuid.toString());
        jsonObject.put(JSON_TITLE, mTitle);
        jsonObject.put(JSON_DESCRIPTION, mDescription);
        jsonObject.put(JSON_IMAGE_URI, mImageUri == null ? "" : mImageUri.toString());
        jsonObject.put(JSON_LATITUDE, latitude);
        jsonObject.put(JSON_LONGITUDE, longitude);
        return jsonObject;
    }

    public String toString() {
        return mTitle;
    }

    public String getTitle() {
        return mTitle;
    }

    public UUID getUUID() { return mUuid; }

    public String getDescription() {
        return mDescription;
    }

    public Uri getUri() {
        return mImageUri;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
