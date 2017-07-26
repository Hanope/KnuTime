<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="error" type="java.util.Optional<String>" -->
<#import "/spring.ftl" as spring>
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

<!-- 회원가입 폼 -->
<div class="container">
    <div class="col-md-12">
        <div class="page-header">
            <h2>회원가입</h2>
        </div>
        <form class="form-horizontal" action="/user/create" name="form" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="email">이메일</label>
                <div class="col-sm-6">
                    <input type="email" class="form-control" id="email" name="email" placeholder="이메일" required>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="password">비밀번호</label>
                <div class="col-sm-6">
                    <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호 재입력" required>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="passwordRepeated">비밀번호 재입력</label>
                <div class="col-sm-6">
                    <input type="password" class="form-control" id="passwordRepeated" name="passwordRepeated" placeholder="비밀번호 재입력" required>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="nickName">별명</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="nickName" name="nickName" placeholder="별명" required>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-12 text-center">
                    <button type="submit" class="btn btn-primary">회원가입</button>
                </div>
            </div>
        </form>

    <@spring.bind "form" />
    <#if spring.status.error>
        <ul>
            <#list spring.status.errorMessages as error>
                <li>${error}</li>
            </#list>
        </ul>
    </#if>

    </div>
</div>


<!-- jQuery javascript 로드-->
<script src="/webjars/jquery/3.2.1/dist/jquery.min.js"></script>
<!-- bootstrap javascript 로드 -->
<script src="/webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>