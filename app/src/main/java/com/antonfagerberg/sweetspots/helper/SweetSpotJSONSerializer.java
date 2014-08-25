package com.antonfagerberg.sweetspots.helper;

import android.content.Context;

import com.antonfagerberg.sweetspots.model.SweetSpot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class SweetSpotJSONSerializer {
    private Context mContext;
    private String mFilename;

    /**
     * Constructor to create a SweetSpot to JSON serializer.
     *
     * @param context Application context.
     * @param filename Name of file to save to disk.
     */
    public SweetSpotJSONSerializer(Context context, String filename) {
        mContext = context;
        mFilename = filename;
    }

    /**
     * Persist all SweetSpots to disk in JSON format.
     *
     * @param sweetSpots List of SweetSpots.
     * @throws JSONException
     * @throws IOException
     */
    public void saveSweetSpots(ArrayList<SweetSpot> sweetSpots) throws JSONException, IOException {
        JSONArray jsonArray = new JSONArray();

        for (SweetSpot sweetSpot : sweetSpots) {
            jsonArray.put(sweetSpot.toJSON());
        }

        Writer writer = null;

        try {
            OutputStream outputStream = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(outputStream);
            writer.write(jsonArray.toString());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * Load all persisted SweetSpots from disk.
     *
     * @return List of SweetSpots.
     * @throws IOException
     * @throws JSONException
     */
    public ArrayList<SweetSpot> loadSweetSpots() throws IOException, JSONException {
        ArrayList<SweetSpot> sweetSpots = new ArrayList<SweetSpot>();

        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = mContext.openFileInput(mFilename);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder jsonString = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                jsonString.append(line);
            }

            JSONArray jsonArray = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            for (int i = 0; i < jsonArray.length(); i++) {
                sweetSpots.add(new SweetSpot(jsonArray.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            // Ignore, happens when starting fresh...
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }

        return sweetSpots;
    }
}
