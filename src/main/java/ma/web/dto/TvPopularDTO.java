package ma.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties
public class TvPopularDTO extends CommonDTO {

    private List<TvPopularResultDTO> results;

    public List<TvPopularResultDTO> getResults() {
        return results;
    }

    public void setResults(List<TvPopularResultDTO> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "TvPopularDTO{" +
                "results=" + results +
                '}';
    }
}
