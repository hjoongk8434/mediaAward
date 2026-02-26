<%--
  Created by IntelliJ IDEA.
  User: kya9t
  Date: 25. 11. 26.
  Time: 오후 2:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header">
    <form action="/search/main" method="get" target="_self">
        <input id="keyword" name="keyword" type="text" placeholder="무엇을 찾고 있나요?" value="${keyword}"/>
        <button type="submit" id="searchBtn"><img src="/resources/img/svg/search.svg" alt="search"/></button>
    </form>
    <input id="page" name="page" type="hidden" value="${searchResult.multiSearchData.page}"/>
    <input id="totalPage" name="totalPage" type="hidden" value="${searchResult.multiSearchData.total_pages}"/>
</div>
