package com.example.eatsnow.dagger;

import com.example.eatsnow.activity.DashboardActivity;
import com.example.eatsnow.activity.SplashActivity;
import com.example.eatsnow.model.RestaurantDetailsModel;
import com.example.eatsnow.model.RestaurantListModel;
import com.example.eatsnow.model.ReviewModel;
import com.example.eatsnow.model.SearchModel;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = NetworkModule.class)
@Singleton
public interface NetworkComponent {

    void inject(SplashActivity splashActivity);
    void inject(DashboardActivity dashboardActivity);

    void inject(RestaurantListModel restaurantListModel);
    void inject(RestaurantDetailsModel restaurantDetailsModel);
    void inject(ReviewModel reviewModel);
    void inject(SearchModel searchModel);
}
