package com.example.eatsnow;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper methods related to requesting and receiving Data from Zomato API.
 */
public final class DailyMenuUtils extends Network{

    /**
     * Create a private constructor because no one should ever create a {@link DailyMenuUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of CollectionUtils is not needed).
     */
    private DailyMenuUtils(){
    }

    /**
     * Create a fetchDailyMenu because fetch the dishes
     * @param requestUrl
     * @return List of {@link DailyMenu}
     */
    public static List<DailyMenu> fetchDailyMenu(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link DailyMenu}s
        List<DailyMenu> dailyMenus = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link Collection}s
        return dailyMenus;
    }

    /**
     * Return a list of {@link DailyMenu} objects that has been built up from
     * parsing the given JSON response.
     */
    private static List<DailyMenu> extractFeatureFromJson(String dailyMenuJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(dailyMenuJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding collection to
        List<DailyMenu> dailyMenus = new ArrayList<>();

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(dailyMenuJSON);

            JSONArray dailyMenuArray = baseJsonResponse.getJSONArray("daily_menu");

            // For each collection in the collectionArray, create an {@link Collection} object
            for (int i = 0; i < dailyMenuArray.length(); i++) {


                // Get a single collection at position i within the list of DailyMenu
                JSONObject currentDailyMenu = dailyMenuArray.getJSONObject(i);

                // Extract the value for the key called "daily_menu_id"
                int daily_menu_id = currentDailyMenu.getInt("daily_menu_id");

                // Extract the value for the key called "name"
                String name = currentDailyMenu.getString("name");

                // Extract the value for the key called "start_date"
                String start_date = currentDailyMenu.getString("start_date");

                // Extract the value for the key called "end_date"
                String end_date = currentDailyMenu.getString("end_date");

                // Extract the Array of Key called "dishes"
                JSONArray currenDailyMenuDishes = currentDailyMenu.getJSONArray("dishes");

                // New List for Dishes
                List<Dishes> dishesList = new ArrayList<>();

                // Extract all the dishes and store in dishesList
                for(int j=0;j < currenDailyMenuDishes.length();j++){

                    // Get a single collection at position i within the list of Dishes
                    JSONObject currentDish = currenDailyMenuDishes.getJSONObject(i);

                    // Extract the value for the key called "dish_id"
                    int dish_id = currentDish.getInt("dish_id");

                    // Extract the value for the key called "name"
                    String dishName = currentDish.getString("name");

                    // Extract the value for the key called "price"
                    String price = currentDish.getString("price");

                    // Create a new {@link dish} object with the dish_id, dishName
                    // price from the JSON response.
                    Dishes dish = new Dishes(dish_id,dishName,price);

                    // Add the new {@link dish} to the list of dishesList.
                    dishesList.add(dish);
                }

                // Create a new {@link DailyMenu} object with the daily_menu_id, name, start_date
                // end_date, dishesList from the JSON response.
                DailyMenu dailyMenu = new DailyMenu(daily_menu_id,name,start_date,end_date,dishesList);

                // Add the new {@link dailyMenu} to the list of dailyMenus.
                dailyMenus.add(dailyMenu);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("DailyMenuUtils", "Problem parsing the collection JSON results", e);
        }

        // Return the list of dailyMenu
        return dailyMenus;
    }

}
