package ma.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties
public class MultiSearchDTO extends CommonDTO {
    private List<MultiSearchResultDTO> results;

    public List<MultiSearchResultDTO> getResults() {
        return results;
    }

    public void setResults(List<MultiSearchResultDTO> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "MultiSearchDTO{" +
                "results=" + results +
                '}';
    }
}
