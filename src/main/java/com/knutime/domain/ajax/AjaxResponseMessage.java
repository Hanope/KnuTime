package com.knutime.domain.ajax;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AjaxResponseMessage {

    private String status;
    private String message;

    public void success() {
        this.status = "success";
    }

    public void fail() {
        this.status = "fail";
    }
}
