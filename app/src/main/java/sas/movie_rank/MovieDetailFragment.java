package sas.movie_rank;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sas.movie_rank.databinding.FragmentMovieDetailBinding;
import sas.movie_rank.services.MovieDetail;
import sas.movie_rank.services.TheMDBService;

public class MovieDetailFragment extends Fragment {
    private FragmentMovieDetailBinding binding;

    public MovieDetailFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentMovieDetailBinding.inflate(inflater, container, false);
        this.setMovieDetails();
        return this.binding.getRoot();
    }

    private void setMovieDetails() {
        this.getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                binding.id.setText(result.getString("movieId"));
                Call<MovieDetail> call = TheMDBService.TheMDBApi.getInstance().getMovieDetail(result.getString("movieId"), getString(R.string.api_key));
                call.enqueue(new Callback<MovieDetail>() {
                    @Override
                    public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                        if (response.isSuccessful()){
                            MovieDetail movieDetail = response.body();
                            binding.title.setText(movieDetail.getTitle());
                            binding.tagline.setText(movieDetail.getTagline());
                            binding.voteAverage.setText(movieDetail.getVoteAverage());
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieDetail> call, Throwable t) {
                        System.out.println("!!!!FALLO LA CONEXION CON LA API!!!");
                    }
                });
            }
        });
    }
}