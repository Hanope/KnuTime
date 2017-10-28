<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>knutime</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/fonts/simple-line-icons.min.css">
    <link rel="stylesheet" href="assets/css/Animated-numbers-section1.css">
    <link rel="stylesheet" href="assets/css/nav-sticky-top.css">
    <link rel="stylesheet" href="assets/css/portlet.css">
    <link rel="stylesheet" href="assets/css/Pretty-Login-Form.css">
    <link rel="stylesheet" href="assets/css/Pretty-Registration-Form.css">
    <link rel="stylesheet" href="assets/css/Pretty-Search-Form.css">
    <link rel="stylesheet" href="assets/css/styles.css">
    <link rel="stylesheet" href="assets/css/timetable.css">
</head>

<body>

<#-- 내비게이션 바 include -->
    <#include "../navbar.ftl">

<div>
    <div class="container conatiner-rating">
        <div class="row">
            <div class="col-md-4">
                <div>
                    <form class="search-form">
                        <div class="input-group">
                            <div class="input-group-addon"><span><i class="glyphicon glyphicon-search"></i></span></div>
                            <input class="form-control" id="rating-search" type="text" placeholder="과목명">
                            <div class="input-group-btn">
                                <button class="btn btn-default rating-search-btn" type="submit">Search </button>
                            </div>
                        </div>
                    </form>
                    <ul class="list-group course-list-group">
                        <li class="list-group-item course-rating-list-group">
                            <div class="course-list-name"><span class="course-name">문화 기술 개론 </span><span class="course-professor">김항준 </span></div>
                            <div class="course-list-info"><span><i class="glyphicon glyphicon-star star"></i><i class="glyphicon glyphicon-star star"></i><i class="glyphicon glyphicon-star star"></i><i class="glyphicon glyphicon-star unstar"></i><i class="glyphicon glyphicon-star unstar"></i></span>
                                <span class="course-semester">2016년 1학기 </span>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-md-8">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><i class="glyphicon glyphicon-education"></i> 최신 강의평 </h3></div>
                    <div class="panel-body">
                        <ul class="list-group">

                            <#list ratingPage.content as rating>
                                <li class="list-group-item course-review-list-group-item">
                                    <div class="row">
                                        <div class="col-md-2">
                                            <span>
                                                <#list 1..rating.star as i>
                                                    <i class="glyphicon glyphicon-star star"></i>
                                                </#list>
                                                <#list 1..(5 - rating.star) as i>
                                                    <i class="glyphicon glyphicon-star unstar"></i>
                                                </#list>
                                            </span>
                                        </div>
                                        <div class="col-md-10">
                                            <div class="course-list-name"><span class="course-name">${rating.course.title} </span><span class="course-professor">${rating.course.instructor} </span></div>
                                            <div class="course-list-review"><span class="course-review">${rating.comment}</span></div>
                                            <div class="course-list-writer"><span class="course-writer">${rating.course.semester} 수강자 </span></div>
                                        </div>
                                    </div>
                                </li>
                            </#list>
                        </ul>
                        <nav>
                            <ul class="pagination">
                                <li><a aria-label="Previous"><span aria-hidden="true">«</span></a></li>
                                <#list 1..ratingPage.size as i>
                                    <li><a href="?page=${i}">${i}</a></li>
                                </#list>
                                <li><a aria-label="Next"><span aria-hidden="true">»</span></a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="col-md-12"></div>
        </div>
    </div>
</div>
<script src="assets/js/jquery.min.js"></script>
<script src="assets/bootstrap/js/bootstrap.min.js"></script>

<script>
    $('.rating-search-btn').click(function(e) {
        e.preventDefault();

        var param = $('#rating-search').val();
        console.log(param);

        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '/api/course/search/' + param,
            dataType: 'json',
            success: function(data) {
                var result = data['result'];

                $('.course-list-group').empty();

                if(typeof(result) == 'string') {
                    $('#course-datalist').append("<option value='" + result +"'>");
                }
                else {
                    for(var i=0; i<result.length; i++)
                        $('#course-datalist').append("<option value='" + result[i]['title'] + "'>");
                }
            }
        });
    });
</script>

</body>

</html>