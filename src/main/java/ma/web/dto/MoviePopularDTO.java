package ma.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties
public class MoviePopularDTO extends CommonDTO {

    private List<MoviePopularResultDTO> results;

    public List<MoviePopularResultDTO> getResults() {
        return results;
    }

    public void setResults(List<MoviePopularResultDTO> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "MoviePopularDTO{" +
                "results=" + results +
                '}';
    }
}
