package com.antonfagerberg.sweetspots.helper;

import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MediaHelper {
    /**
     * Get URI to the media file which will be created.
     *
     * @return URI to the media file.
     */
    public static Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputMediaFile());
    }

    /**
     * Create a new file which will be used to store the image.
     *
     * @return Java File.
     */
    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "SweetSpots");

        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdirs();
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(String.format("%s%sIMG_%s.jpg", mediaStorageDir.getPath(), File.separator, timeStamp));
    }
}