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
<#include "./navbar.ftl">

<div class="row">
    <div class="col-sm-6">
        <div class="container-fluid">
            <div class="container-fluid search-result">
                <ul class="course-result list-group"><li class="list-group-item"><p class="list-course-title course-list">문화 기술 개론</p><p class="list-course-code course-list">CLTR263001</p><p class="list-course-instructor course-list">김항준</p><p class="list-course-location course-list">공대9호관418</p><p class="list-course-time course-list">월1A1B2A/수7A7B8A</p></li><li class="list-group-item"><p class="list-course-title course-list">물리학 I</p><p class="list-course-code course-list">CLTR213030</p><p class="list-course-instructor course-list">이성엽</p><p class="list-course-location course-list">공대9호관417</p><p class="list-course-time course-list">화2B3A3B/목1A1B2A</p></li><li class="list-group-item"><p class="list-course-title course-list">실용화법</p><p class="list-course-code course-list">CLTR003004</p><p class="list-course-instructor course-list">정수진</p><p class="list-course-location course-list">공대9호관417</p><p class="list-course-time course-list">월2B3A3B/금1A1B2A</p></li><li class="list-group-item"><p class="list-course-title course-list">소프트웨어와 문제해결
                </p><p class="list-course-code course-list">COMP209003</p><p class="list-course-instructor course-list">김항준</p><p class="list-course-location course-list">공대12호관207</p><p class="list-course-time course-list">수1A1B2A2B3A3B4A4B</p></li><li class="list-group-item"><p class="list-course-title course-list">알고리즘1</p><p class="list-course-code course-list">COMP319003</p><p class="list-course-instructor course-list">유관우</p><p class="list-course-location course-list">공대9호관418</p><p class="list-course-time course-list">월2A2B3A/목8B9A9B</p></li><li class="list-group-item"><p class="list-course-title course-list">고고학실습</p><p class="list-course-code course-list">ARAN316001</p><p class="list-course-instructor course-list">이성주</p><p class="list-course-location course-list">제4합동강의동403</p><p class="list-course-time course-list">월8B9A9B/수8B9A9B</p></li><li class="list-group-item"><p class="list-course-title course-list">동물생리학</p><p class="list-course-code course-list">ANSC432001</p><p class="list-course-instructor course-list">손종경</p><p class="list-course-location course-list">생물학관318</p><p class="list-course-time course-list">화8B9A9B/목8B9A9B</p></li><li class="list-group-item"><p class="list-course-title course-list">컴퓨터학개론</p><p class="list-course-code course-list">ITEC201009</p><p class="list-course-instructor course-list">이호경</p><p class="list-course-location course-list">IT대학4호관(제2학생회관)101</p><p class="list-course-time course-list">월7A7B8A/수8B9A9B</p></li></ul>
            </div>
        </div>
    </div>
    <div class="col-sm-6">
        <div class="panel panel-group">
            <div class="panel-heading">
                <h2>현재 신청 과목</h2>
            </div>
            <div class="panel-body">

            </div>
        </div>
    </div>
</div>

<!-- jQuery javascript 로드-->
<script src="/webjars/jquery/3.2.1/dist/jquery.min.js"></script>
<!-- bootstrap javascript 로드 -->
<script src="/webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script>
    $('.login').click(function() {
        $.ajax({
            url: "/test/ajax",
            success: function(result) {
                console.log(result);
            },
            error: function(result) {
                console.log(result);
            }

        });
    });
</script>
</body>
</html>