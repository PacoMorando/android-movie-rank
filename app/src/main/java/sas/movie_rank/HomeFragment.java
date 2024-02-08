package sas.movie_rank;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sas.movie_rank.databinding.FragmentHomeBinding;
import sas.movie_rank.services.TheMDBService;
import sas.movie_rank.services.Trending;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private final TrendingRecyclerAdapter trendingRecyclerAdapter;

    public HomeFragment() {
        this.trendingRecyclerAdapter = new TrendingRecyclerAdapter();
        this.trendingRecyclerAdapter.setItemClickListener(itemId -> {
            this.showMovieDetail(itemId);
            Toast.makeText(getContext(), "Picaste el id" + itemId, Toast.LENGTH_SHORT).show();
        });
    }

    private void showMovieDetail(String itemId) {
        Bundle result = new Bundle();
        result.putString("movieId", itemId);
        this.getParentFragmentManager().setFragmentResult("requestKey", result);
        this.getParentFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.home_container, MovieDetailFragment.class, null)
                .commit();
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
        this.getTrending();
        return this.binding.getRoot();
    }

    private void getTrending() {
        Call<Trending> call = TheMDBService.TheMDBApi.getInstance().getTrending(getString(R.string.api_key));
        call.enqueue(new Callback<Trending>() {
            @Override
            public void onResponse(Call<Trending> call, Response<Trending> response) {
                if (response.isSuccessful()) {
                    Trending trending = response.body();
                    List<Trending.Result> trendingResults = trending != null ? trending.getResults() : null;
                    if (trendingResults != null) {
                        trendingRecyclerAdapter.setTrendingResults(trendingResults);
                        trendingRecyclerAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<Trending> call, Throwable t) {
                System.out.println("!!!!FALLO LA CONEXION CON LA API!!!");
            }
        });
    }
}