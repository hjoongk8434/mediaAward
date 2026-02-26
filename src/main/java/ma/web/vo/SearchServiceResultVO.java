package ma.web.vo;

import ma.web.dto.MultiSearchDTO;
import ma.web.dto.MultiSearchResultDTO;

public class SearchServiceResultVO {
    private boolean authenticated = false;
    private boolean hasSearchData = false;
    private String imgUrl;
    private MultiSearchDTO multiSearchData;

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public boolean isHasSearchData() {
        return hasSearchData;
    }

    public void setHasSearchData(boolean hasSearchData) {
        this.hasSearchData = hasSearchData;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public MultiSearchDTO getMultiSearchData() {
        return multiSearchData;
    }

    public void setMultiSearchData(MultiSearchDTO multiSearchData) {
        this.multiSearchData = multiSearchData;
    }

    @Override
    public String toString() {
        return "SearchServiceResultVO{" +
                "authenticated=" + authenticated +
                ", imgUrl='" + imgUrl + '\'' +
                ", multiSearchData=" + multiSearchData +
                '}';
    }
}
