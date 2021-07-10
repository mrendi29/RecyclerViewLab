package com.example.recyclerviewlab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    List<Movie> movieList;
    Context context;
    public MovieAdapter(Context context, List<Movie> movieList){
        this.context = context;
        this.movieList = movieList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View movieView = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);

        return new ViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull  MovieAdapter.ViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        RelativeLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.rvLayout);
        }


        public void bind(final Movie movie){
            String imageUrl = movie.getPosterPath();
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            new MovieImageTask(ivPoster).execute(imageUrl);
//            new MovieImageTask(ivPoster).execute(imageUr);
//            MovieImageTask loadImage = new MovieImageTask(ivPoster).onPostExecute();
//            Glide.with(context)
//                .load(imageUrl)
//                .into(ivPoster);
        }
    }
}
