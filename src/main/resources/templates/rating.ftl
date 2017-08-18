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

    <style>
        .btn-success {
            width:50pt; height:22pt; margin-right:5px
        }

        .btn-primary {
            width:50pt; height:22pt; margin-right:5px
        }

        #span_speci {
            margin-right: 0.5em;
            margin-left: 0.5em;
            font-family: 돋움체;
            font-weight: 500;
        }

        #s_right {
            width: 300px;
            height: 800px;
            float: right;
        }

        #search_bar {
            height: 40px;
            width: 270px;
            border: 1px solid #1b5ac2;
            background: #ffffff;
        }

        input {
            text-align: left;
            font-size: 16px;
            width: 217.5px;
            height: 39px;
            padding: 10px;
            border 0px;
            outline: non;
            float: left;
        }

        #search_but {
            width: 50px;
            height: 39.5px;
            border: 0px;
            background: #1b5ac2;
            outline: none;
            float: right;
            color: #ffffff;
        }

        div.stars {
            width: 270px;
            display: inline-block;
        }

        input.star {
            display: none;
        }

        label.star {
            float: left;
            padding: 5px;
            font-size: 10px;
            color: #444;
            transition: all .2s;
        }

        input.star:checked ~ label.star:before {
            content: '\f005';
            color: #FD4;
            transition: all .25s;
        }

        input.star-5:checked ~ label.star:before {
            color: #FE7;
            text-shadow: 0 0 20px #952;
        }

        input.star-1:checked ~ label.star:before { color: #F62; }

        label.star:hover { transform: rotate(-15deg) scale(1.3); }

        label.star:before {
            content: '\f006';
            font-family: FontAwesome;
        }
    </style>
</head>
<body>

<#-- 내비게이션 바 include -->
    <#include "./navbar.ftl">

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-8">
            <div class="container-fluid">
                <div class="recent-rating">
                    <h3>최신 강의평가</h3>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="container-fluid">
                <div class="rating-search-heading">
                    <h3>강의평가 검색</h3>
                </div>
                <div class="rating-search-body">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="검색어 입력">
                        <span class="input-group-btn">
                                <button class="btn btn-primary" type="button">검색</button>
                            </span>
                    </div>
                </div>
            </div>
            <div class="container-fluid">
                <div class="recent-rating-story-heading">
                    <h3>최근 강의 이야기</h3>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 작업 시작 -->
<div id="s_right">
    <h3>강의평가 검색</h3>
    <div id="search_bar">
        <input type="text" placeholder="검색어 입력">
        <button type="submit" id="search_but">검색</button>
    </div>
    <h3>최근 강의 이야기</h3>
    <div>
        <ul style="list-style-type: square;">
            <li>통계학</li>
            <p>"나름 괜찮은거 같애요"</p>
            <li>공학수학2</li>
            <p>"설명을 못해서 학생들한테 물어봐여...쉅준비좀 하셧으면...까먹어서 똑같은내용...ㅎㄷㄷㅜㅜ"</p>
            <li>신호및시스템</li>
            <p>"과제없긴뭐가없냐 2주만에 과제3개쨰구만 디지겠다"</p>
            <li>영상비평</li>
            <p>"이거 신청해했는데 폐강이에요?"</p>
            <li>연극의 이해</li>
            <p>"국어와 매체언어"</p>
            <li>연극의 이해</li>
            <p>"국어와 매체언어"</p>
            <li>의료사회사업론</li>
            <p>"사회복지개론"</p>
            <li>의료사회사업론</li>
            <p>"사회복지개론"</p>
            <li>기초전자물리학실험1</li>
            <p>"EECS448008"</p>
            <li>논리와비판적사고</li>
            <p>"CLTR045024"</p>
        </ul>
    </div>
    <h3>최근 검색어</h3>
    <div>
        <ul style="list-style-type: square;">
            <li>공학수학2</li>
            <li>의료사회사업론</li>
            <li>문화기술개론</li>
            <li>신호및시스템</li>
            <li>네트워크프로그래밍</li>
        </ul>
    </div>
</div>
<h1>최신 강의평가</h1>
<div>
    <p>2일전</p>
    <hr>
    <p class="rating">
        <button class="btn btn-success">20171</button>
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
        <span class="stars">
                <form action="">
                    <input class="star star-5" id="star-5" type="radio" name="star"/>
                    <label class="star star-5" for="star-5"></label>
                    <input class="star star-4" id="star-4" type="radio" name="star"/>
                    <label class="star star-4" for="star-4"></label>
                    <input class="star star-3" id="star-3" type="radio" name="star"/>
                    <label class="star star-3" for="star-3"></label>
                    <input class="star star-2" id="star-2" type="radio" name="star"/>
                    <label class="star star-2" for="star-2"></label>
                    <input class="star star-1" id="star-1" type="radio" name="star"/>
                    <label class="star star-1" for="star-1"></label>
                </form>
            </span>
        <span>화학2 정종화</span>
        <span id="span_speci">어려워요</span>
    </p>
    <p>
        <button class="btn btn-success">20171</button>
        <img src="별표평점5.png", width="70", height="20">
        <span>교직실무 권총현</span>
        <span id="span_speci">교수님은 참 좋은 분입니다. 학생들을 아끼는 것이 느껴지고, 교직 생활에 매우...</span>
    </p>
    <p>
        <button class="btn btn-success">20171</button>
        <img src="별표평점1.5.png", width="70", height="20">
        <span>한국고대사 주보돈</span>
        <span id="span_speci">교수님이 이해하기 쉽게 수업을 잘하십니다. 시험은 좀 범주가 넓어서 채점기준이 어렵습니다.</span>
    </p>
    <p>
        <button class="btn btn-success">20171</button>
        <img src="별표평점5.png", width="70", height="20">
        <span>인간의 삶과 사회복지 신창환</span>
        <span id="span_speci">교수님이 강의를 잘 하십니다. 재밌고, 여러모로 생각할 기회를 얻을 수 있는 수업이에요.</span>
    </p>
    <p>
        <button class="btn btn-success">20171</button>
        <img src="별표평점0.5.png", width="70", height="20">
        <span>한국사 최홍조</span>
        <span id="span_speci">설명은 잘하시지만 그만큼 말이 많으세요.</span>
    </p>
    <p>
        <button class="btn btn-success">20171</button>
        <img src="별표평점0.5.png", width="70", height="20">
        <span>현대사회와 윤리 김덕수</span>
        <span id="span_speci">강의가 재밌고, 시험도 깔끔하게 나옵니다.</span>
    </p>
    <p>
        <button class="btn btn-success">20171</button>
        <img src="별표평점1.png", width="70", height="20">
        <span>심리학의 이해 박나영</span>
        <span id="span_speci">시험 범위가 많아서 좀 부담스러웠지만 공부만 다한다면 문제는 다풀수있음.</span>
    </p>
</div>
<div>
    <p>5일전</p>
    <hr>
    <p>
        <button class="btn btn-success">20171</button>
        <img src="별표평점1.png", width="70", height="20">한국경제론 이상락
    </p>
</div>
<div>
    <p>6일전</p>
    <hr>
    <p>
        <button class="btn btn-primary">20162</button>
        <img src="별표평점5.png", width="70", height="20">자료구조 김구진
    </p>
</div>

<!-- jQuery javascript 로드-->
<script src="/webjars/jquery/3.2.1/dist/jquery.min.js"></script>
<!-- bootstrap javascript 로드 -->
<script src="/webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>