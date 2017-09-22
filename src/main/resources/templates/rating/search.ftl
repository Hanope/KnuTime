<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Page Description">
    <meta name="author" content="knuTime">
    <title>Page Title</title>

    <!-- 스타일 리셋 -->
    <link href="/css/reset.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="/webjars/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <!-- 내비게이션 바 스타일 -->
    <link href="/css/knutime.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<#-- 내비게이션 바 include -->
<#include "../navbar.ftl">

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-8">
            <div class="container-fluid">
                <div class="recent-rating">
                    <div class="recent-rating-heading">
                        <h3>최신 강의평가</h3>
                    </div>
                    <div class="recent-rating-content">
                    <#assign c_date = .now>
                    <#assign next_diff = -1>
                    <#list ratingPage.content as rating>
                        <#assign r_date = rating.dateTime>
                        <#assign diff = c_date?time?long - r_date?time?long>
                        <#assign diff_day = diff / (24 * 60 * 60 * 1000)>
                        <#if next_diff != diff_day>
                            <hr>
                        ${diff_day?floor}일전
                        </#if>
                        <div class="recent-rating-container">
                            <span class="label label-success">${rating.course.semester}</span>
                            <a href="/course/${rating.course.id}"><strong>${rating.course.title}</strong></a>
                            <span>${rating.course.instructor}</span>
                            <#list 1..rating.star as i>
                                <span class="fa fa-star"></span>
                            </#list>
                            <span class="rating-comment">${rating.comment}</span>
                        </div>
                        <#assign next_diff = diff_day>
                    </#list>
                    </div>
                    <div class="recent-rating-bottom">
                        <ul class="pager">
                        <#if !ratingPage.first>
                            <li class="prev"><a href="?page=${ratingPage.number-1}">이전 <span aria-hidden="true">&larr;</span></a></li>
                        </#if>
                        <#if !ratingPage.last>
                            <li class="next"><a href="?page=${ratingPage.number+1}">다음 <span aria-hidden="true">&rarr;</span></a></li>
                        </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="container-fluid">
                <div class="rating-search-heading">
                    <h3>강의평가 검색</h3>
                </div>
                <div class="rating-search-body">
                    <div class="input-group">
                        <input type="text" class="form-control" id="rating-search" placeholder="검색어 입력">
                        <span class="input-group-btn">
                            <button class="btn btn-default rating-search-btn" type="button">검색</button>
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery javascript 로드-->
<script src="/webjars/jquery/3.2.1/dist/jquery.min.js"></script>
<!-- bootstrap javascript 로드 -->
<script src="/webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script>
    $('.rating-search-btn').click(function() {
        var param = $('#rating-search').val();

        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '/api/rating/' + param,
            dataType: 'json',
            success: function(data) {
                console.log(data);
            }
        });
    });

    $('#rating-search').on('input', function() {
        var course = $('#rating-search').val();

        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '/api/course/search/' + course,
            dataType: 'json',
            success: function(data) {
                var result = data['result'];

                $('#course-datalist').empty();

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