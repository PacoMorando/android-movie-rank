package sas.movie_rank;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sas.movie_rank.databinding.FragmentHomeBinding;
import sas.movie_rank.services.TrendingRecyclerAdapter;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    TrendingRecyclerAdapter trendingRecyclerAdapter;

    public HomeFragment() {
        this.trendingRecyclerAdapter = new TrendingRecyclerAdapter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentHomeBinding.inflate(inflater, container, false);
        this.binding.trendingResView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.binding.trendingResView.setAdapter(this.trendingRecyclerAdapter);
        return this.binding.getRoot();
    }
}