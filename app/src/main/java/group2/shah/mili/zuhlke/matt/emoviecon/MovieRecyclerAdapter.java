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

/**
 * Recycler adapter for the viewholder for a movie scroll Recycler adapter is the best choice
 * since it loads three at a time and the Lists can be 800+ in size
 *
 * @author Matt Zuhlke
 * @version 1.0
 */
public class MovieRecyclerAdapter
        extends RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder> {

    private List<EMovieCon> movies;
    private Context mainContext;

    /**
     * Internal class for the holder that holds the movie content
     */
    class MovieViewHolder extends RecyclerView.ViewHolder {
        View rvView;
        TextView movieTitle;
        TextView movieDescr;
        ImageView movieThumb;

        /**
         * View holder constructor
         *
         * @param v View to apply the holder to
         */
        MovieViewHolder(View v) {
            super(v);
            rvView = v.findViewById(R.id.video_view);
            movieTitle = v.findViewById(R.id.movie_title);
            movieDescr = v.findViewById(R.id.movie_descr_short);
            movieThumb = v.findViewById(R.id.movie_thumbnail);
        }
    }

    /**
     * Adapter Constructor that applies the EMovieCon List to be added
     *
     * @param movies List of movies to be added to the recycler view
     */
    MovieRecyclerAdapter(List<EMovieCon> movies) {
        this.movies = movies;
    }

    /**
     * Inflates the view from the activity passed in and sets the layout as movie_view
     * Also sets the activity context for the class
     *
     * @param parent   ViewGroup that will have the recycler view applied
     * @param viewType Type of view that is going to applied to the group (not used, defaulted)
     * @return MovieRecyclerAdapter.MovieViewHolder Returns a newly constructed MovieViewHolder
     * applied with the view.
     */
    @Override
    @NonNull
    public MovieRecyclerAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_view, parent, false);
        mainContext = parent.getContext();
        return new MovieViewHolder(v);
    }

    /**
     * Binds the data to the view holder and sets images and onlicklisteners to the content
     * Easier to set onclick listeners here than to create another view layout if we were to
     * set it in onCreateViewHolder. Elevation is set so this content is on top of the viewholder
     * and background
     *
     * @param holder   View holder that actually has the content from internal class
     * @param position the index of the array to pull the movie from
     */
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

    /**
     * Sets the onlicklistener with the extra variables in the intent
     * to identify the movie
     *
     * @param position which movie out of the list the listener is applied for.
     * @return View.OnClickListener
     */
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

    /**
     * Returns the movie size
     *
     * @return int Movies list size
     */
    @Override
    public int getItemCount() {
        return movies.size();
    }
}
