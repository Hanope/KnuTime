<!-- 내비게이션 바 -->
<nav class="navbar navbar-default fixed-top">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/">Brand</a>
    </div>

    <div class="collapse navbar-collapse" id="navbar-ex1-collapse">
        <ul class="nav navbar-nav">
        <#if currentUser??>
            <#if currentUser.timetables?has_content>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="glyphicon glyphicon-calendar"></i>&nbsp&nbsp모의시간표<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/timetable/new">새로 만들기</a></li>
                        <li role="separator" class="divider"></li>
                        <#list currentUser.timetables as item>
                            <li><a href="/timetable/${item.serialNumber}">${item.name}</a></li>
                        </#list>
                    </ul>
                </li>
            <#else>
                <li><a href="/timetable/new"><i class="glyphicon glyphicon-calendar" aria-hidden="true"></i>&nbsp&nbsp모의시간표</a></li>
            </#if>
        <#else>
            <li><a href="/timetable/new"><i class="glyphicon glyphicon-calendar" aria-hidden="true"></i>&nbsp&nbsp모의시간표</a></li>
        </#if>
            <li><a href="/rating"><i class="glyphicon glyphicon-pencil" aria-hidden="true"></i>&nbsp&nbsp강의평가</a></li>
        </ul>

    <#if !currentUser??>
        <ul class="nav navbar-nav navbar-right">
            <li role="presentation"><a href="/user/create">회원가입 </a></li>
            <li role="presentation"><a href="/login"><i class="glyphicon glyphicon-log-in"></i> 로그인 </a></li>
        </ul>
    <#else>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="fa fa-user"></i>&nbsp&nbsp${currentUser.nickName}<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="#"><span class="fa fa-gears"></span>&nbsp&nbsp설정</a></li>
                    <li>
                        <form action="/logout" method="post">
                        <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                            <button type="submit" class="btn btn-link btn-logout"><span class="glyphicon glyphicon-log-out"></span>&nbsp&nbsp로그아웃</button>
                        </form>
                    </li>
                </ul>
            </li>
        </ul>
    </#if>

    </div>
</nav>