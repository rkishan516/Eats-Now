package com.example.eatsnow;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper methods related to requesting and receiving Data from Zomato API.
 */
public final class QueryUtils {

    /** Tag for the log messages */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    public static List<Collection> fetchCollectionData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Collection}s
        List<Collection> collections = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link Collection}s
        return collections;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return a list of {@link Collection} objects that has been built up from
     * parsing the given JSON response.
     */
    private static List<Collection> extractFeatureFromJson(String collectionJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(collectionJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding collection_item to
        List<Collection> collections = new ArrayList<>();

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the JSON response string
            JSONArray collectionArray = new JSONArray(collectionJSON);

            // For each collection_item in the collectionArray, create an {@link Collection} object
            for (int i = 0; i < collectionArray.length(); i++) {


                // Get a single collection_item at position i within the list of collections
                JSONObject currentCollection = collectionArray.getJSONObject(i);

                // Extract the value for the key called "collection_id"
                int collection_id = currentCollection.getInt("collection_id");

                // Extract the value for the key called "title"
                String title = currentCollection.getString("title");

                // Extract the value for the key called "url"
                String url = currentCollection.getString("url");

                // Extract the value for the key called "discription"
                String discription = currentCollection.getString("discription");

                // Extract the value for the key called "image_url"
                String image_url = currentCollection.getString("image_url");

                // Extract the value for the key called "res_count"
                int res_count = currentCollection.getInt("res_count");

                // Extract the value for the key called "share_url"
                String share_url = currentCollection.getString("share_url");

                // Create a new {@link Collection} object with the collection_id, title, url,discription
                // image_url, res_count and share_url from the JSON response.
                Collection collection = new Collection(collection_id,title,url,discription,image_url,res_count,share_url);

                // Add the new {@link Collection} to the list of collections.
                collections.add(collection);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the collection_item JSON results", e);
        }

        // Return the list of earthquakes
        return collections;
    }

}
