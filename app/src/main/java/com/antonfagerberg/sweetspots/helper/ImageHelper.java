package com.antonfagerberg.sweetspots.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import static android.graphics.Bitmap.createScaledBitmap;

public class ImageHelper {
    public static Bitmap resize(Uri imageUri, int targetWidth) {
        Bitmap imageBitmap = BitmapFactory.decodeFile(imageUri.getPath());

        int originalWidth = imageBitmap.getWidth();
        float scaleFactor = (float) targetWidth / originalWidth;
        int targetHeight = (int) (imageBitmap.getHeight() * scaleFactor);

        return createScaledBitmap(imageBitmap, targetWidth, targetHeight, true);
    }
}
