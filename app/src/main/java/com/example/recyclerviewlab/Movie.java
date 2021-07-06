package com.example.recyclerviewlab;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    String posterPath;
    String title;
    String overview;

    //Empty constructor needed for parcel
    public Movie() {
    }

    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws  JSONException{
        List<Movie> movies = new ArrayList<>();

        for (int i = 0; i< movieJsonArray.length(); ++i){
            // Each time we add a new Movie Json Object the constructor is called and each value is populated
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        // The List is then returned into the other method which it was called.
        return  movies;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }
}
