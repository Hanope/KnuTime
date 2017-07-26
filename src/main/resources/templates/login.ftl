<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="error" type="java.util.Optional<String>" -->
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

<!-- 로그인 폼 -->
<div class="container">
    <div class="col-md-12">
        <div class="page-header">
            <h2>로그인</h2>
        </div>
        <form class="form-horizontal" action="/login" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="email">이메일</label>
                <div class="col-sm-6">
                    <input class="form-control" name="email" id="email" type="email" placeholder="이메일">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="password">비밀번호</label>
                <div class="col-sm-6">
                    <input class="form-control" name="password" id="password" type="password" placeholder="비밀번호 재입력">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-12 text-center">
                    <label for="remember-me">자동 로그인</label>
                    <input type="checkbox" name="remember-me" id="remember-me">
                    <button class="btn btn-primary" type="submit">로그인</button>
                </div>
            </div>
        </form>

    <#if error.isPresent()>
        <p>이메일 혹은 비밀번호가 올바르지 않습니다.</p>
    </#if>
    </div>
</div>

<!-- jQuery javascript 로드-->
<script src="/webjars/jquery/3.2.1/dist/jquery.min.js"></script>
<!-- bootstrap javascript 로드 -->
<script src="/webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>