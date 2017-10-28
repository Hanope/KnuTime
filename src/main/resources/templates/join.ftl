<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html>

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

    <section class="section-join-form">
        <div class="row register-form">
            <div class="col-md-8 col-md-offset-2">
                <form class="form-horizontal custom-form" action="/user/create" method="post">
                    <h1>회원가입 </h1>
                    <div class="form-group">
                        <div class="col-sm-4 label-column">
                            <label class="control-label" for="email-input-field">Email </label>
                        </div>
                        <div class="col-sm-6 input-column">
                            <input class="form-control" name="email" id="email" type="email">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4 label-column">
                            <label class="control-label" for="pawssword-input-field">Password </label>
                        </div>
                        <div class="col-sm-6 input-column">
                            <input class="form-control" name="password" id="password"  type="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4 label-column">
                            <label class="control-label" for="repeat-pawssword-input-field">Repeat Password </label>
                        </div>
                        <div class="col-sm-6 input-column">
                            <input class="form-control" name="passwordRepeated" id="passwordRepeeated" type="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4 label-column">
                            <label class="control-label" for="alias-input-field">별명 </label>
                        </div>
                        <div class="col-sm-6 input-column">
                            <input class="form-control" name="nickName" id="nickName" type="text">
                        </div>
                    </div>
                    <button class="btn btn-default submit-button" type="submit">회원가입 </button>
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
    </section>
    <script src="/assets/js/jquery.min.js"></script>
    <script src="/assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>