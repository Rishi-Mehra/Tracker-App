package com.rm.myapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModel {

@SerializedName("code")
@Expose
private Integer code;
@SerializedName("token")
@Expose
private String token;
@SerializedName("message")
@Expose
private String message;
@SerializedName("loginID")
@Expose
private String loginID;
@SerializedName("loginStatus")
@Expose
private String loginStatus;

public Integer getCode() {
return code;
}

public void setCode(Integer code) {
this.code = code;
}

public String getToken() {
return token;
}

public void setToken(String token) {
this.token = token;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public String getLoginID() {
return loginID;
}

public void setLoginID(String loginID) {
this.loginID = loginID;
}

public String getLoginStatus() {
return loginStatus;
}

public void setLoginStatus(String loginStatus) {
this.loginStatus = loginStatus;
}

}