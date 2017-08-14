package com.knutime.domain.ajax;

import com.knutime.domain.timetable.Timetable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimetableResponse extends AjaxResponse {

    private Timetable result;
}
