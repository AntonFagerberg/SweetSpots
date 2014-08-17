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
    private static final String
        JSON_ID = "id",
        JSON_TITLE = "title",
        JSON_DESCRIPTION = "description",
        JSON_IMAGE_URI = "image_uri";

    public SweetSpot(String title, String description, Uri imageUri) {
        mTitle = title;
        mDescription = description;
        mUuid = UUID.randomUUID();
        mImageUri = imageUri;
    }

    public SweetSpot(JSONObject jsonObject) throws JSONException {
        mUuid = UUID.fromString(jsonObject.getString(JSON_ID));
        mTitle = jsonObject.getString(JSON_TITLE);
        mDescription = jsonObject.getString(JSON_DESCRIPTION);
        String imageUri = jsonObject.getString(JSON_IMAGE_URI);
        mImageUri = imageUri.equals("") ? null : Uri.parse(imageUri);
    }

    public String toString() {
        return mTitle;
    }

    public String getTitle() {
        return mTitle;
    }

    public UUID getId() { return mUuid; }

    public String getDescription() {
        return mDescription;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(JSON_ID, mUuid.toString());
        jsonObject.put(JSON_TITLE, mTitle);
        jsonObject.put(JSON_DESCRIPTION, mDescription);
        jsonObject.put(JSON_IMAGE_URI, mImageUri == null ? "" : mImageUri.toString());
        return jsonObject;
    }

    public Uri getUri() {
        return mImageUri;
    }
}
