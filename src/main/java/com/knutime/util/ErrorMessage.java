package com.knutime.util;

public interface ErrorMessage {
    String ERR_UNAUTHORIZED_USER = "인증되지 않는 사용자입니다.";
    String ERR_NOT_FOUND_TABLE = "시간표가 존재하지 않습니다.";
    String ERR_NOT_FOUND_COURSE = "과목이 존재하지 않습니다.";
    String ERR_BAD_ACCESS = "잘못된 접근입니다.";
    String ERR_EXISTS_COURSE = "과목이 중복됩니다.";
    String ERR_EXISTS_TIME = "시간이 중복됩니다.";
}
