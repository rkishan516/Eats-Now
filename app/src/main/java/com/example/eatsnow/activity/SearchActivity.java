package com.example.eatsnow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.eatsnow.R;
import com.example.eatsnow.common.Analytics;
import com.example.eatsnow.fragment.Search;
import com.example.eatsnow.model.search.Restaurant;

import timber.log.Timber;

public class SearchActivity extends AppCompatActivity implements Search.ISearchAcion {

    private static final String FTAG_SEARCH_FRAGMENT = "fragmentSearch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Search fragment = (Search) getSupportFragmentManager().findFragmentByTag(FTAG_SEARCH_FRAGMENT);

        if(null == fragment) {

            fragment = new Search();

            Bundle bundle = new Bundle();
            bundle.putParcelable("location", getIntent().getParcelableExtra("location"));
            fragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fl_search_activity, fragment, FTAG_SEARCH_FRAGMENT)
                    .commit();
        }

        fragment.setSearchAction(this);

        //Analytics information
        Analytics.setCurrentScreen(this, "Search Activity");
    }

    @Override
    public void onRestaurantSelected(Restaurant restaurant) {

        Timber.d(restaurant.getName());
        Intent launchIntent = new Intent(this, RestaurantDetailsActivity.class);
        launchIntent.putExtra("resid", restaurant.getId());
        launchIntent.putExtra("rName", restaurant.getName());
        launchIntent.putExtra("rCuisine", restaurant.getCuisines());
        startActivity(launchIntent);
    }
}
