package sas.movie_rank.services;

import com.squareup.moshi.Json;

public class MovieDetail {
    @Json(name = "title")
    String title;
    @Json(name = "tagline")
    String tagline;
    @Json(name = "poster_path")
    String posterPath;
    @Json(name = "backdrop_path")
    String backdropPath;
    @Json(name = "vote_average")
    double voteAverage;
}
