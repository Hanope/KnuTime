package com.knutime.domain.ajax;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseSummaryResponse extends AjaxResponse{

    private List<?> result;
}
