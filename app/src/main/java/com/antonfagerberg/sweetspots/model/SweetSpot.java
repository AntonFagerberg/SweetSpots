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

    /**
     * Constructor to create a new SweetSpot.
     *
     * @param title Title.
     * @param description Description.
     * @param imageUri URI to the image on disk.
     * @param longitude Location longitude.
     * @param latitude Location latitude.
     */
    public SweetSpot(String title, String description, Uri imageUri, double longitude, double latitude) {
        mTitle = title;
        mDescription = description;
        mUuid = UUID.randomUUID();
        mImageUri = imageUri;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * Constructor to create a new SweetSpot from JSON.
     * This is used when loading persisted SweetSpots from the disk.
     *
     * @param jsonObject JSON Object.
     * @throws JSONException
     */
    public SweetSpot(JSONObject jsonObject) throws JSONException {
        mUuid = UUID.fromString(jsonObject.getString(JSON_ID));
        mTitle = jsonObject.getString(JSON_TITLE);
        mDescription = jsonObject.getString(JSON_DESCRIPTION);
        String imageUri = jsonObject.getString(JSON_IMAGE_URI);
        mImageUri = imageUri.equals("") ? null : Uri.parse(imageUri);
        longitude = jsonObject.getDouble(JSON_LONGITUDE);
        latitude = jsonObject.getDouble(JSON_LATITUDE);
    }

    /**
     * Create a JSON representation of the SweetSpot.

     * @return JSON representation of the SweetSpot.
     * @throws JSONException
     */
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

    /**
     * Get the title of the SweetSpot.
     *
     * @return Title of the SweetSpot.
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Get the UUID of the SweetSpot.
     *
     * @return UUID of the SweetSpot.
     */
    public UUID getUUID() {
        return mUuid;
    }

    /**
     * Get the description of the SweetSpot.
     *
     * @return Description of the SweetSpot.
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * Get the URI to the image stored on disk.
     *
     * @return URI to the image.
     */
    public Uri getUri() {
        return mImageUri;
    }

    /**
     * Get the location longitude.
     *
     * @return Location longitude.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Get the location latitude.
     *
     * @return Location latitude.
     */
    public double getLatitude() {
        return latitude;
    }
}
