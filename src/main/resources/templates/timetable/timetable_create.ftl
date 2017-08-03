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
        <div class="container timetable-container">
            <div class="panel-group">
                <div class="panel panel-success">
                    <div class="panel-heading">시간표 만들기</div>
                    <div class="panel-body">
                        <form action="/timetable/new" method="post">
                            <div class="form-group">
                                <label for="timetable_name">시간표 이름</label>
                                <input type="text" name="name" class="form-control" placeholder="시간표 이름" required>
                            </div>
                            <div class="form-group">
                                <label for="timetable_semester">시간표 학기</label>
                                <select class="form-control" name="semester">
                                    <option>----- 학기 선택 -----</option>
                                    <option value="20171">2017년 1학기</option>
                                    <option value="20172">2017년 2학기</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary center-block">만들기</button>
                        </form>
                    </div>
                </div>
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