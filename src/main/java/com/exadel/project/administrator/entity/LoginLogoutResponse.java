package com.exadel.project.administrator.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LoginLogoutResponse {

    private String responseStatus;
    private List<String> roleList = new ArrayList<>();

    public LoginLogoutResponse() {
    }

    public LoginLogoutResponse(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public LoginLogoutResponse(String responseStatus, List<String> roleList) {
        this.responseStatus = responseStatus;
        this.roleList = roleList;
    }
}
