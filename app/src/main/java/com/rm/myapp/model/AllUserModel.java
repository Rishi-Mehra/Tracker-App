package com.rm.myapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllUserModel {

@SerializedName("data")
@Expose
private List<Datum> data = null;
@SerializedName("message")
@Expose
private String message;
@SerializedName("code")
@Expose
private Integer code;

public List<Datum> getData() {
return data;
}

public void setData(List<Datum> data) {
this.data = data;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public Integer getCode() {
return code;
}

public void setCode(Integer code) {
this.code = code;
}
    public class Datum {

        @SerializedName("userName")
        @Expose
        private String userName;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("loginID")
        @Expose
        private String loginID;
        @SerializedName("phoneNumber")
        @Expose
        private String phoneNumber;
        @SerializedName("loginStatus")
        @Expose
        private String loginStatus;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("latitude")
        @Expose
        private Object latitude;
        @SerializedName("longitude")
        @Expose
        private Object longitude;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getLoginID() {
            return loginID;
        }

        public void setLoginID(String loginID) {
            this.loginID = loginID;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getLoginStatus() {
            return loginStatus;
        }

        public void setLoginStatus(String loginStatus) {
            this.loginStatus = loginStatus;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
        }

    }

}