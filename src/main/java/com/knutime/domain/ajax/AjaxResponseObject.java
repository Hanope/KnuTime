package com.knutime.domain.ajax;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AjaxResponseObject {

    private String status;
    private String message;
    private Object result;

    public void success() {
        this.status = "success";
    }

    public void fail() {
        this.status = "fail";
    }
}
