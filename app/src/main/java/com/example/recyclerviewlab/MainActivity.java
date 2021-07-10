package com.example.recyclerviewlab;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private static final String MOVIE_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=250f1c88864857ef8232c5af7b730dec";

    List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvMovies = findViewById(R.id.rvMovies);

        movieList = new ArrayList<>();

        final MovieAdapter movieAdapter = new MovieAdapter(this, movieList);

        rvMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvMovies.setHasFixedSize(true);

        rvMovies.setAdapter(movieAdapter);

        MovieClient.getRequest(MOVIE_URL, (statusCode, response) -> {
            try {
                JSONArray movieJsonArray = response.getJSONArray("results");
                movieList.addAll(Movie.fromJsonArray(movieJsonArray));
                movieAdapter.notifyDataSetChanged();
                Log.d("MyApp", "Sucess");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

    }
}
