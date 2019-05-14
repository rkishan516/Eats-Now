package com.example.eatsnow;

public class Dishes {
    private int dish_id;
    private String name;
    private String price;

    //Constructor Function
    public Dishes(int dish_id, String name, String price) {
        this.dish_id = dish_id;
        this.name = name;
        this.price = price;
    }

    //Getter Functions
    public int getDish_id() {
        return dish_id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
