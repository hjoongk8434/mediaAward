package ma.web.vo;

import ma.web.dto.MoviePopularDTO;
import ma.web.dto.TvPopularDTO;

public class HomeServiceResultVO {

    private boolean authenticated = false;
    private boolean hasTmdbMovieData = false;
    private boolean hasTmdbTvData = false;
    private String imgUrl;
    private MoviePopularDTO moviePopularData;
    private TvPopularDTO tvPopularData;

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public boolean isHasTmdbMovieData() {
        return hasTmdbMovieData;
    }

    public void setHasTmdbMovieData(boolean hasTmdbMovieData) {
        this.hasTmdbMovieData = hasTmdbMovieData;
    }

    public boolean isHasTmdbTvData() {
        return hasTmdbTvData;
    }

    public void setHasTmdbTvData(boolean hasTmdbTvData) {
        this.hasTmdbTvData = hasTmdbTvData;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public MoviePopularDTO getMoviePopularData() {
        return moviePopularData;
    }

    public void setMoviePopularData(MoviePopularDTO moviePopularData) {
        this.moviePopularData = moviePopularData;
    }

    public TvPopularDTO getTvPopularData() {
        return tvPopularData;
    }

    public void setTvPopularData(TvPopularDTO tvPopularData) {
        this.tvPopularData = tvPopularData;
    }
}
