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
    <!-- 일정표 스타일 -->
    <link href="/css/timetable.css" rel="stylesheet">

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

    <div class="row">
        <div class="col-lg-9 col-md-9 col-sm-9">
            <!-- 일정표 -->
            <div class="panel-group">
                <div class="panel">
                    <div class="panel-heading">
                        <h3>${timetable.name}</h3>
                    </div>
                    <div class="panel-body">
                        <div class="cd-schedule loading">
                            <div class="timeline">
                                <ul>
                                    <li><span>09:00</span></li>
                                    <li><span>09:30</span></li>
                                    <li><span>10:00</span></li>
                                    <li><span>10:30</span></li>
                                    <li><span>11:00</span></li>
                                    <li><span>11:30</span></li>
                                    <li><span>12:00</span></li>
                                    <li><span>12:30</span></li>
                                    <li><span>13:00</span></li>
                                    <li><span>13:30</span></li>
                                    <li><span>14:00</span></li>
                                    <li><span>14:30</span></li>
                                    <li><span>15:00</span></li>
                                    <li><span>15:30</span></li>
                                    <li><span>16:00</span></li>
                                    <li><span>16:30</span></li>
                                    <li><span>17:00</span></li>
                                    <li><span>17:30</span></li>
                                    <li><span>18:00</span></li>
                                </ul>
                            </div> <!-- .timeline -->

                            <div class="events">
                                <ul>
                                    <li class="events-group">
                                        <div class="top-info"><span>월요일</span></div>
                                        <ul id="event-mon">

                                        </ul>
                                    </li>

                                    <li class="events-group">
                                        <div class="top-info"><span>화요일</span></div>
                                        <ul id="event-tue">

                                        </ul>
                                    </li>

                                    <li class="events-group">
                                        <div class="top-info"><span>수요일</span></div>
                                        <ul id="event-wed">

                                        </ul>
                                    </li>

                                    <li class="events-group">
                                        <div class="top-info"><span>목요일</span></div>
                                        <ul id="event-thu">

                                        </ul>
                                    </li>

                                    <li class="events-group">
                                        <div class="top-info"><span>금요일</span></div>
                                        <ul id="event-fri">

                                        </ul>
                                    </li>
                                </ul>
                            </div>

                            <div class="event-modal">
                                <header class="header">
                                    <div class="content">
                                        <span class="event-date"></span>
                                        <h3 class="class-name"></h3>
                                        <h5 class="professor-name"></h5>
                                        <h5 class="class-room"></h5>
                                    </div>

                                    <div class="header-bg"></div>
                                </header>

                                <div class="body">
                                    <div class="event-info"></div>
                                    <div class="body-bg"></div>
                                </div>

                                <a href="#" class="close">Close</a>
                            </div>

                            <div class="cover-layer"></div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 일정표 종료 -->
        </div>

        <!-- 검색창 -->
        <div class="col-lg-3 col-md-3 col-sm-3">
            <div class="search-form">
                <div class="input-group">
                    <input type="text" class="form-control" id="course-text" placeholder="과목명 입력">
                    <span class="input-group-btn">
                        <button type="button" class="btn btn-success" id="course-btn">검색</button>
                    </span>
                </div>
            </div>

            <!-- 검색 결과-->
            <div class="container-fluid search-result">
                <ul class="course-result list-group"></ul>
            </div>
        </div>

    </div>

    <!-- jQuery javascript 로드-->
    <script src="/webjars/jquery/3.2.1/dist/jquery.min.js"></script>
    <!-- bootstrap javascript 로드 -->
    <script src="/webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="/js/modernizr.js"></script>
    <script src="/js/timetable.js"></script>
</body>
</html>