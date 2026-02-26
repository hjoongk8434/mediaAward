<%--
  Created by IntelliJ IDEA.
  User: kya9t
  Date: 26. 1. 13.
  Time: 오후 4:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<div id="searchBoxList">
    <c:choose>
        <c:when test="${searchResult.authenticated and searchResult.hasSearchData}">
            <c:forEach var="searchData" items="${searchResult.multiSearchData.results}" varStatus="index">
                <div class="searchMainBox">
                    <div class="searchMainPoster">
                        <img class="searchPoster" src="${imgBaseUrl}${searchData.poster_path}" alt="poster"/>
                    </div>
                    <div class="searchMainText">
                        <table>
                            <tr>
                                <td class="searchTitle" rowspan="2"><c:out value="${searchData.title}" default="데이터 없음"/></td>
                                <td class="searchPopular"><img src="/resources/img/svg/heart-fill.svg" alt="heart"/> <span style="color: red; font-weight: bold;"><fmt:formatNumber value="${searchData.vote_average}" pattern="0.0" maxFractionDigits="1"/></span> /10</td>
                            </tr>
                            <tr>
                                <td class="searchType">
                                    <c:choose>
                                        <c:when test="${searchData.media_type eq 'tv'}">드라마</c:when>
                                        <c:otherwise>영화</c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr>
                                <td class="searchContent" colspan="2">
                                    ${searchData.overview}
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div class="searchMainBox">
                <h1>데이터가 없습니다.</h1>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<c:if test="${searchResult.multiSearchData.total_pages gt 1}">
    <div id="pageBtn">
        <input type="button" value="더 보기" id="pageNext" onclick="addSearchList()">
    </div>
</c:if>

<div class="floatButtons">
    <div class="homeBtn" onclick="home()"><img src="/resources/img/svg/house-door-fill.svg" alt="home"/></div>
</div>

<script id="addSearchListTemplate" type="text/x-handlebars-template">
{{#each data}}
    <div class="searchMainBox">
        <div class="searchMainPoster">
            <img class="searchPoster" src="{{imgUrl}}" alt="poster">
        </div>
        <div class="searchMainText">
            <table>
                <tbody>
                    <tr>
                        <td class="searchTitle" rowspan="2">{{title}}</td>
                        <td class="searchPopular"><img src="/resources/img/svg/heart-fill.svg" alt="heart"> <span style="color: red; font-weight: bold;">{{average}}</span> /10</td>
                    </tr>
                    <tr>
                        <td class="searchType">
                            {{type}}
                        </td>
                    </tr>
                    <tr>
                        <td class="searchContent" colspan="2">
                            {{overview}}
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
{{/each}}
</script>