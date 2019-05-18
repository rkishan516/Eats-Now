package com.example.eatsnow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class RestaurantDetailsActivity extends AppCompatActivity {
    private static final String FTAG_DETAILS = "detailsFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        Bundle bundle = new Bundle();
        bundle.putString("resid", getIntent().getStringExtra("resid"));
        bundle.putString("rName", getIntent().getStringExtra("rName"));
        bundle.putString("rCuisine", getIntent().getStringExtra("rCuisine"));

        RestaurantDetails fragment = new RestaurantDetails();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_rest_details_activity_container, fragment, FTAG_DETAILS)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
