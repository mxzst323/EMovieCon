package group2.shah.mili.zuhlke.matt.emoviecon;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MovieRecyclerAdapter
            extends RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder> {

    private List<EConMovie> movies;

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

        MovieRecyclerAdapter(List<EConMovie> movies) {
            this.movies = movies;
        }

        @Override
        @NonNull
        public MovieRecyclerAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                       int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.movie_view, parent, false);
            //v.setOnClickListener(new OnClickPlayListener(parent.getContext(),iteratedSongs.get(count)));
            return new MovieViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
            holder.movieTitle.setText(movies.get(position).title);
            holder.movieDescr.setText(movies.get(position).descr);
            int tmpphotoid = movies.get(position).photoId;
            if(tmpphotoid != 0)
                holder.movieThumb.setImageResource(tmpphotoid);
        }

        @Override
        public int getItemCount() {
            return movies.size();
        }
    }
