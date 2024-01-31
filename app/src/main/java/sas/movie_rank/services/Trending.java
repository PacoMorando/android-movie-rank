package sas.movie_rank.services;

import com.squareup.moshi.Json;

import java.util.List;

public class Trending {
    @Json(name = "page")
    private int page;
    @Json(name = "results")
    private List<Result> results;
    @Json(name = "total_pages")
    private int totalPages;
    @Json(name = "total_results")
    private int totalResults;

    public int getPage() {
        return page;
    }

    public List<Result> getResults() {
        return results;
    }

    public int getTotal_pages() {
        return totalPages;
    }

    public int getTotal_results() {
        return totalResults;
    }

    public static class Result {
        @Json(name = "id")
        private String id;
        @Json(name = "poster_path")
        private String posterPath;
        @Json(name = "title")
        private String title;
        @Json(name = "release_date")
        private String releaseDate;
        @Json(name = "overview")
        private String overview;

        public String getId() {
            return id;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public String getTitle() {
            return title;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public String getOverview() {
            return overview;
        }

    }
}
