package group2.shah.mili.zuhlke.matt.emoviecon;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;
import java.util.List;

public class MovieRecyclerAdapter
        extends RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder> {

    private List<EMovieCon> movies;
    private Context mainContext;

    class MovieViewHolder extends RecyclerView.ViewHolder {
        View rvView;
        TextView movieTitle;
        TextView movieDescr;
        ImageView movieThumb;

        MovieViewHolder(View v) {
            super(v);
            rvView = v.findViewById(R.id.video_view);
            movieTitle = v.findViewById(R.id.movie_title);
            movieDescr = v.findViewById(R.id.movie_descr_short);
            movieThumb = v.findViewById(R.id.movie_thumbnail);
        }
    }

    MovieRecyclerAdapter(List<EMovieCon> movies) {
        this.movies = movies;
    }

    @Override
    @NonNull
    public MovieRecyclerAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_view, parent, false);
        mainContext = parent.getContext();
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        String title = movies.get(position).title;
        String descr = movies.get(position).descr;

        // Support for overflow on the layout
        if (title.length() > 47) {
            title = title.substring(0, 47) + "...";
        }
        if (descr.length() > 108) {
            descr = descr.substring(0, 108) + "... Click for more info";
        }

        final URL photoURL = movies.get(position).photoId;
        holder.movieThumb.setImageBitmap(TheMovieDBWrapper.getMoviePostBitMap(photoURL));
        holder.movieTitle.setText(title);
        holder.movieDescr.setText(descr);

        holder.movieTitle.setOnClickListener(setOnClickMovieInfo(position));
        holder.movieDescr.setOnClickListener(setOnClickMovieInfo(position));
        holder.movieThumb.setOnClickListener(setOnClickMovieInfo(position));
    }

    private View.OnClickListener setOnClickMovieInfo(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainContext, MovieDetails.class);
                intent.putExtra("MOVIE_ID", movies.get(position).id);
                intent.putExtra("LIST_COUNT", position);
                mainContext.startActivity(intent);
            }
        };
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
