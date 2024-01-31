package sas.movie_rank.services;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sas.movie_rank.R;

public class TrendingRecyclerAdapter extends RecyclerView.Adapter<TrendingRecyclerAdapter.ItemHolder> {
    private ArrayList <Trending.Result> trendingResults;
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
    }

    @Override
    public int getItemCount() {
        return 10;
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
        }
    }
}
