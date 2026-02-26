function home(){
    return window.location.href = "/";
}

function addSearchList() {
    var totalPage = Number($("#totalPage").val());
    var params = new Object();
    params.page = Number($("#page").val()) +1;
    params.keyword = $("#keyword").val();

    var paramsJson = JSON.parse(JSON.stringify(params));

    $.ajax({
        url : "/search/addList",
        type: "POST",
        data: paramsJson
    }).done(function(result) {
        $("#page").val(params.page);
        var searchListTemplate = $("#addSearchListTemplate").html();
        var addList =Handlebars.compile(searchListTemplate);
        var addListHtml = addList(result);
        $("#searchBoxList").append(addListHtml);
        if(params.page === totalPage){
           $("#pageBtn").css("display", "none");
        }
    }).fail(function(result) {
        console.log("Fail !!!");
        console.log(result);
    });
}