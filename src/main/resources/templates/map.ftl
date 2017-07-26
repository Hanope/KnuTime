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

    <div class="container-fluid map-container">
        <div id="map"></div>
    </div>



<!-- jQuery javascript 로드-->
<script src="/webjars/jquery/3.2.1/dist/jquery.min.js"></script>
<!-- bootstrap javascript 로드 -->
<script src="/webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- naver map 로드 -->
<script src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=EgxMtxqNqz647eTycxYX"></script>
<!-- naver map script -->
<script>
    var map = new naver.maps.Map('map', {
        center: new naver.maps.LatLng(35.886913, 128.608571),
        zoom: 12
    });

    var marker = new naver.maps.Marker({
        position: new naver.maps.LatLng(35.886913, 128.608571),
        map: map
    });
</script>
</body>
</html>