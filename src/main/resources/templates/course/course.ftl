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
    <!-- portlet 스타일 -->
    <link href="/css/portlet.css" rel="stylesheet">
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
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-7 margin-bottom-30">
            <!-- BEGIN Portlet PORTLET-->
            <div class="portlet">
                <div class="portlet-title">
                    <div class="caption caption-red">
                        <i class="glyphicon glyphicon-calendar"></i>
                        <span class="caption-subject text-uppercase">${course.title}</span>
                        <span class="caption-helper">${course.semester}</span>
                    </div>
                    <div class="actions">
                        <div class="btn-group btn-group-xs btn-toggle pull-right">
                            <button class="btn btn-info btn-action active" data-toggle="tab" data-target="#a">개요</button>
                            <button class="btn btn-info btn-action" data-toggle="tab" data-target="#b">강의계획서</button>
                            <button class="btn btn-info btn-action" data-toggle="tab" data-target="#c">강의스케쥴</button>
                        </div>
                    </div>
                </div>
                <div class="portlet-body tab-content">
                    <div class="tab-pane active" id="a">
                        <h4>${course.instructor} 교수님</h4>
                        <div class="panel-body">
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>개설대학</td>
                                    <td>
                                    <#if course.department??>${course.department}</#if>
                                    </td>
                                </tr>
                                <tr>
                                    <td>교과구분</td>
                                    <td>
                                    <#if course.categories??>${course.categories}</#if>
                                    </td>
                                </tr>
                                <tr>
                                    <td>학점</td>
                                    <td>
                                    <#if course.credits??>${course.credits}</#if>
                                    </td>
                                </tr>
                                <tr>
                                    <td>수업시간</td>
                                    <td>
                                    <#if course.hours??>${course.hours}</#if>
                                    </td>
                                </tr>
                                <tr>
                                    <td>언어</td>
                                    <td>
                                    <#if course.language??>${course.language}</#if>
                                    </td>
                                </tr>
                                <tr>
                                    <td>강의실</td>
                                    <td>
                                    <#if course.location??>${course.location}</#if>
                                    </td>
                                </tr>
                                <tr>
                                    <td>면담시간</td>
                                    <td>
                                    <#if course.officeHours??>${course.officeHours}</#if>
                                    </td>
                                </tr>
                                <tr>
                                    <td>이메일/연락처</td>
                                    <td>
                                    <#if course.phoneEmail??>${course.phoneEmail}</#if>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- panel A end -->
                    <div class="tab-pane" id="b">
                        <div class="panel-group" id="accordion_reg" role="tablist" aria-multiselectable="true">
                            <div class="panel panel-default">
                                <div class="panel-heading" role="tab" id="headingOne_reg">
                                    <h4 class="panel-title">
                                        <a role="button" data-toggle="collapse" data-parent="#accordion_reg" href="#collapseOne_reg" aria-expanded="true" aria-controls="collapseOne_reg">
                                            강의개요 및 목적
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseOne_reg" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne_reg">
                                    <div class="panel-body">
                                    <#if course.courseInfo.goals??>${course.courseInfo.goals}</#if>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading" role="tab" id="headingTwo_reg">
                                    <h4 class="panel-title">
                                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion_reg" href="#collapseTwo_reg" aria-expanded="false" aria-controls="collapseTwo_reg">
                                            교재 및 참고문헌
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseTwo_reg" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo_reg">
                                    <div class="panel-body">
                                    <#if course.courseInfo.textbook??>${course.courseInfo.textbook}</#if>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading" role="tab" id="headingThree_reg">
                                    <h4 class="panel-title">
                                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion_reg" href="#collapseThree_reg" aria-expanded="false" aria-controls="collapseThree_reg">
                                            강의진행 방법 및 활용매체
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseThree_reg" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree_reg">
                                    <div class="panel-body">
                                    <#if course.courseInfo.description??>${course.courseInfo.description}</#if>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading" role="tab" id="headingFour_reg">
                                    <h4 class="panel-title">
                                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion_reg" href="#collapseFour_reg" aria-expanded="false" aria-controls="collapseFour_reg">
                                            과제, 평가방법, 선수과목
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseFour_reg" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour_reg">
                                    <div class="panel-body">
                                    <#if course.courseInfo.criteria??>${course.courseInfo.criteria}</#if>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading" role="tab" id="headingFive_reg">
                                    <h4 class="panel-title">
                                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion_reg" href="#collapseFive_reg" aria-expanded="false" aria-controls="collapseFive_reg">
                                            수강에 특별히 참고할 사항
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseFive_reg" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive_reg">
                                    <div class="panel-body">
                                    <#if course.courseInfo.notice??>${course.courseInfo.notice}</#if>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading" role="tab" id="headingSix_reg">
                                    <h4 class="panel-title">
                                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion_reg" href="#collapseSix_reg" aria-expanded="false" aria-controls="collapseSix_reg">
                                            장애학생을 위한 학습지원 사항
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseSix_reg" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix_reg">
                                    <div class="panel-body">
                                    <#if course.courseInfo.disabilities??>${course.courseInfo.disabilities}</#if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- panel B end -->
                    <div class="tab-pane" id="c">
                        <h5>작업중</h5>
                    </div>
                    <!-- panel C end -->
                </div>
            </div>
        </div>
        <div class="col-sm-5 margin-bottom-30">
            <!-- BEGIN Portlet PORTLET-->
            <div class="portlet">
                <div class="portlet-title">
                    <div class="caption caption-green">
                        <i class="glyphicon glyphicon-education"></i>
                        <span class="caption-subject text-uppercase">강의이야기</span>
                        <span class="caption-helper">이 강의에 대해 궁금한 점이나 할 말을 남겨주세요.</span>
                    </div>
                </div>
                <div class="portlet-title">
                    <h5>아직 강의이야기가 없어요 ㅠ_ㅠ</h5>
                </div>
                <div>
                    <input type="text">
                    <button class="btn btn-default">댓글 남기기</button>
                </div>
            </div>
        </div>
    </div>
    <!-- END Portlet PORTLET-->
    <div class="col-xs-5 margin-bottom-30">
        <!-- BEGIN Portlet PORTLET-->
        <div class="portlet">
            <div class="portlet-title">
                <div class="caption caption-purple">
                    <i class="glyphicon glyphicon-pencil"></i>
                    <span class="caption-subject text-uppercase">강의평가</span>
                    <span class="caption-helper">이 강의에 대해 궁금한 점이나 할 말을 남겨주세요.</span>
                </div>
            </div>
            <div class="portlet-body">
            <#if ratings?has_content>
                <#list ratings.content as rating>
                    <div class="recent-rating-container">
                        <span>
                            <#list 1..rating.star as i>
                                <span class="fa fa-star"></span>
                            </#list>
                        </span>
                        <span class="rating-comment">${rating.comment}</span>
                    </div>
                </#list>
                <div class="recent-rating-bottom">
                <#-- AJAX로 처리 -->
                    <ul class="pager">
                        <#if !ratings.first>
                            <li class="prev"><a href="?page=${ratings.number-1}">이전 <span aria-hidden="true">&larr;</span></a></li>
                        </#if>
                        <#if !ratings.last>
                            <li class="next"><a href="?page=${ratings.number+1}">다음 <span aria-hidden="true">&rarr;</span></a></li>
                        </#if>
                    </ul>
                </div>
            <#else>
                <h5>아직 강의평가가 없어요</h5>
                <a href="#"><h5>지금, 첫 강의평가를 남겨주세요!</h5></a>
            </#if>
            </div>
            <div class="portlet-foot">
                <div class="row">
                    <div class="col-lg-6">
                        <span>별점</span>
                        <select name="star" id="star">
                            <option value="1">★</option>
                            <option value="2">★★</option>
                            <option value="3">★★★</option>
                            <option value="4">★★★★</option>
                            <option value="5">★★★★★</option>
                        </select>
                        <div class="input-group">
                            <input type="text" id="rating-comment" class="form-control">
                            <span class="input-group-btn">
                                <button class="btn btn-default btn-write" type="button">작성</button>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- jQuery javascript 로드-->
<script src="/webjars/jquery/3.2.1/dist/jquery.min.js"></script>
<!-- bootstrap javascript 로드 -->
<script src="/webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
    $('.btn-action').click(function() {
        $('.btn-action').removeClass('active');
        $(this).addClass('active');
    });

    $('.btn-write').click(function() {
        var pathname = $(location).attr('pathname');
        var courseId = pathname.substring(pathname.lastIndexOf('/') + 1);
        var comment = $('#rating-comment').val();
        var star = $('#star').val();

        var data = { 'comment': comment, 'star': star };

        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: '/api/rating/' + courseId,
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(result) {
                if (result['status'] == 'fail') {
                    alert(result['message']);
                    location.href = result['url'];
                } else {
                    alert(result['message']);
                }
            }
        });
    })
</script>
</body>

</html>