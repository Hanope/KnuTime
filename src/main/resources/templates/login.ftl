<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>knutime</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto+Slab:300,400|Roboto:300,400,700">
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
    <#include "./navbar.ftl">

    <section class="section-login-form">
        <div class="row login-form">
            <div class="col-md-4 col-md-offset-4">
                <h2 class="text-center form-heading">로그인 </h2>
                <form class="custom-form" action="/login" method="post">
                    <div class="form-group">
                        <input class="form-control" name="email" id="email" type="email" placeholder="Email">
                    </div>
                    <div class="form-group">
                        <input class="form-control" name="password" id="password" type="password" placeholder="Password">
                    </div>
                    <div class="form-group"></div>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" name="remember-me" id="remember-me"><strong>자동 로그인</strong></label>
                    </div>
                    <button class="btn btn-default btn-block submit-button" type="submit">Login </button>
                </form>

                <#if error.isPresent()>
                    <p>이메일 혹은 비밀번호가 올바르지 않습니다.</p>
                </#if>
            </div>
        </div>
    </section>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/Animated-numbers-section.js"></script>
    <script src="assets/js/timetable.js"></script>
</body>

</html>