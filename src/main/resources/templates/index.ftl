<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>knutime</title>
    <link rel="stylesheet" href="/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/fonts/simple-line-icons.min.css">
    <link rel="stylesheet" href="/assets/css/Animated-numbers-section1.css">
    <link rel="stylesheet" href="/assets/css/nav-sticky-top.css">
    <link rel="stylesheet" href="/assets/css/portlet.css">
    <link rel="stylesheet" href="/assets/css/Pretty-Login-Form.css">
    <link rel="stylesheet" href="/assets/css/Pretty-Registration-Form.css">
    <link rel="stylesheet" href="/assets/css/Pretty-Search-Form.css">
    <link rel="stylesheet" href="/assets/css/styles.css">
    <link rel="stylesheet" href="/assets/css/timetable.css">
</head>
<body>

    <#-- 내비게이션 바 include -->
    <#include "./navbar.ftl">

    <section class="slider">
        <div class="carousel slide" data-ride="carousel" data-interval="3000" id="carousel-1">
            <div class="carousel-inner" role="listbox">
                <div class="item active"><img src="/assets/img/1.png" alt="Slide Image"></div>
                <div class="item"><img src="/assets/img/2.png" alt="Slide Image"></div>
            </div>
            <div class="hidden"><a class="left carousel-control" href="#carousel-1" role="button" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i><span class="sr-only">Previous</span></a><a class="right carousel-control" href="#carousel-1" role="button"
                                                                                                                                                                                                                data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i><span class="sr-only">Next</span></a></div>
            <ol class="carousel-indicators">
                <li data-target="#carousel-1" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-1" data-slide-to="1"></li>
            </ol>
        </div>
    </section>
    <section class="wrapper-numbers">
        <div class="container">
            <div class="row countup text-center">
                <div class="col-md-8 col-md-offset-2 header-numbers">
                    <h1>KnuTime </h1>
                    <p>수강신청 전 쉽고 빠르게 모의 시간표를 만들어 보세요.</p>
                </div>
                <div class="col-md-3 col-sm-6 column">
                    <p><i class="icon-graduation" aria-hidden="true"></i></p>
                    <p> <span class="count">2543</span></p>
                    <h2>등록된 강의 </h2></div>
                <div class="col-md-3 col-sm-6 column">
                    <p><i class="icon-pencil" aria-hidden="true"></i></p>
                    <p> <span class="count replay">15234 </span></p>
                    <h2>작성된 강의평 </h2></div>
                <div class="col-md-3 col-sm-6 column">
                    <p><i class="icon-calendar" aria-hidden="true"></i></p>
                    <p> <span class="count">1423 </span></p>
                    <h2>등록된 시간표 </h2></div>
                <div class="col-md-3 col-sm-6 column">
                    <p><i class="icon-user" aria-hidden="true"></i></p>
                    <p> <span class="count">2000 </span></p>
                    <h2>총 이용자 </h2></div>
            </div>
        </div>
    </section>
    <script src="/assets/js/jquery.min.js"></script>
    <script src="/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="/assets/js/Animated-numbers-section.js"></script>
    <script src="/assets/js/timetable.js"></script>
</body>

</html>