package com.example.eatsnow;

import java.util.List;

public class DailyMenu {
    private int daily_menu_id;
    private String name;
    private String start_date;
    private String end_date;
    private List<Dishes> dishesList;

    //Constructor Function
    public DailyMenu(int daily_menu_id, String name, String start_date, String end_date, List<Dishes> dishesList){
        this.daily_menu_id = daily_menu_id;
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.dishesList = dishesList;
    }

    //Getter Functions
    public int getDaily_menu_id() {
        return daily_menu_id;
    }

    public String getName() {
        return name;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public List<Dishes> getDishesList() {
        return dishesList;
    }
}
