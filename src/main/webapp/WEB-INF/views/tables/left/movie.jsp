<%--
  Created by IntelliJ IDEA.
  User: kya9t
  Date: 25. 12. 24.
  Time: 오후 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<div class="mainMiniBox">
    <table class="rankBox">
        <thead>
        <tr>
            <th colspan="3">영화 인기 TOP 5</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${result.authenticated and result.hasTmdbMovieData}">
                <c:forEach var="moviePopular" items="${result.moviePopularData.results}" end="4" varStatus="index">
                    <tr>
                        <td><img class="poster" src="${imgBaseUrl}${moviePopular.poster_path}" alt="poster"/></td>
                        <td><c:out value="${moviePopular.title}" default="데이터 없음"/></td>
                        <td><img src="/resources/img/svg/heart-fill.svg" alt="heart"/> <span style="color: red; font-weight: bold;"><fmt:formatNumber value="${moviePopular.vote_average}" pattern="0.0" maxFractionDigits="1"/></span> /10</td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="3"> 데이터가 없습니다.</td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
</div>