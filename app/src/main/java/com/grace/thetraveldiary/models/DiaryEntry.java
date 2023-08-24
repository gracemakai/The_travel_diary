package com.grace.thetraveldiary.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DiaryEntry {
    private String id;
    private String user;
    private String title;
    private String date;

    private String location;

    private String notes;

    public DiaryEntry() {
    }

    public DiaryEntry(String user, String title, String date, String location, String notes) {
        this.user = user;
        this.title = title;
        this.date = date;
        this.location = location;
        this.notes = notes;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getNotes() {
        return notes;
    }

    public String getId() {
        return id;
    }

    public String getUser() {
        return user;
    }


    public Map<String, Object> toJSON(){

        Map<String, Object> jsonObject=  new HashMap<>();

                jsonObject.put("user", getUser());
                jsonObject.put("title", getTitle());
                jsonObject.put("date", getDate());
                jsonObject.put("location", getLocation());
                jsonObject.put("notes", getNotes());

            return jsonObject;


    }
}
