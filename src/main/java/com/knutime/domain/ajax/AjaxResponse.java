package com.knutime.domain.ajax;

import lombok.Getter;
import lombok.Setter;

@Getter
public class AjaxResponse {

    private String message;

    public void success() {
        this.message = "success";
    }

    public void fail() {
        this.message = "fail";
    }
}
