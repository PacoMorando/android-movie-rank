package sas.movie_rank.services;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class TheMDBService {
    private static String API_KEY;
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String SEARCH_MOVIE = "search/movie?query=";
    private static final String TRENDING = "trending/movie/day?&page=1&";
    private static final Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .build();

    public TheMDBService(String apiKey) {
        API_KEY = apiKey;
    }

    public interface ServiceInterface {
        @GET(TRENDING + "{apiKey}")
        Call<Trending> getTrending(@Path("apiKey") String apiKey);

        //Call<Trending> getTrending(@Query("q") String productToSearch, @Query("limit") int resultsSize);
        @GET("movie/{movieId}?{apiKey}")
        Call<MovieDetail> getMovieDetail(@Path("movieId") String movieId, @Path("apiKey") String apiKey);
    }

    public static class TheMDBApi {
        private static ServiceInterface retrofitService;

        public static ServiceInterface getInstance() {
            if (retrofitService == null) {
                retrofitService = retrofit.create(ServiceInterface.class);
            }
            return retrofitService;
        }
    }

}