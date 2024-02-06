package sas.movie_rank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import sas.movie_rank.services.Trending;

public class TrendingRecyclerAdapter extends RecyclerView.Adapter<TrendingRecyclerAdapter.ItemHolder> {
    private ArrayList<Trending.Result> trendingResults;

    public TrendingRecyclerAdapter() {
        this.trendingResults = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trending, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        holder.title.setText(trendingResults.get(position).getTitle());
        holder.releasedDate.setText(trendingResults.get(position).getReleaseDate());
        holder.overview.setText(trendingResults.get(position).getOverview());
        holder.setPoster(trendingResults.get(position).getPosterPath());
    }

    @Override
    public int getItemCount() {
        return this.trendingResults.size();
    }

    public void setTrendingResults(List<Trending.Result> trendingResults) {
        this.trendingResults = (ArrayList<Trending.Result>) trendingResults;
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView releasedDate;
        TextView overview;
        ImageView poster;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.title);
            this.releasedDate = itemView.findViewById(R.id.released_date);
            this.overview = itemView.findViewById(R.id.overview);
            this.poster = itemView.findViewById(R.id.poster);
            //this.setPoster();
        }

        private void setPoster(String posterPath) {
            Picasso.get().load("https://image.tmdb.org/t/p/w200" + posterPath).into(this.poster, new Callback() {
                @Override
                public void onSuccess() {
                    poster.setBackground(null);
                    System.out.println("loaded image");
                }

                @Override
                public void onError(Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
