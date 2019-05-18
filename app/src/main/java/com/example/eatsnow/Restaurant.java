package com.example.eatsnow;
import java.util.List;

public class Restaurant{
    private List<Object> offers;
    private int hasOnlineDelivery;
    private List<Object> establishmentTypes;
    private String apikey;
    private int hasTableBooking;
    private String thumb;
    private int averageCostForTwo;
    private String menuUrl;
    private int isDeliveringNow;
    private String deeplink;
    private int priceRange;
    private int switchToOrderMenu;
    private String featuredImage;
    private String url;
    private String cuisines;
    private String eventsUrl;
    private String name;
    private Location location;
    private String currency;
    private String id;
    private String photosUrl;
    private UserRating userRating;

    public List<Object> getOffers(){
        return offers;
    }

    public int getHasOnlineDelivery(){
        return hasOnlineDelivery;
    }

    public List<Object> getEstablishmentTypes(){
        return establishmentTypes;
    }

    public void setApikey(String apikey){
        this.apikey = apikey;
    }

    public String getApikey(){
        return apikey;
    }

    public void setHasTableBooking(int hasTableBooking){
        this.hasTableBooking = hasTableBooking;
    }

    public int getHasTableBooking(){
        return hasTableBooking;
    }

    public void setThumb(String thumb){
        this.thumb = thumb;
    }

    public String getThumb(){
        return thumb;
    }

    public int getAverageCostForTwo(){
        return averageCostForTwo;
    }

    public String getMenuUrl(){
        return menuUrl;
    }

    public int getIsDeliveringNow(){
        return isDeliveringNow;
    }

    public String getDeeplink(){
        return deeplink;
    }

    public int getPriceRange(){
        return priceRange;
    }

    public int getSwitchToOrderMenu(){
        return switchToOrderMenu;
    }

    public String getFeaturedImage(){
        return featuredImage;
    }

    public String getUrl(){
        return url;
    }

    public String getCuisines(){
        return cuisines;
    }

    public void setEventsUrl(String eventsUrl){
        this.eventsUrl = eventsUrl;
    }

    public String getEventsUrl(){
        return eventsUrl;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setLocation(Location location){
        this.location = location;
    }

    public Location getLocation(){
        return location;
    }

    public String getCurrency(){
        return currency;
    }


    public String getId(){
        return id;
    }

    public String getPhotosUrl(){
        return photosUrl;
    }

    public void setUserRating(UserRating userRating){
        this.userRating = userRating;
    }

    public UserRating getUserRating(){
        return userRating;
    }
}