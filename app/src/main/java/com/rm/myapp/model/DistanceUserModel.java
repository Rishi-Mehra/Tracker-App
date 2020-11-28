package com.rm.myapp.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DistanceUserModel {

    @SerializedName("userList")
    @Expose
    private List<UserList> userList = null;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<UserList> getUserList() {
        return userList;
    }

    public void setUserList(List<UserList> userList) {
        this.userList = userList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class UserList {

        @SerializedName("distance")
        @Expose
        private Integer distance;
        @SerializedName("userName")
        @Expose
        private String userName;
        @SerializedName("phoneNumber")
        @Expose
        private String phoneNumber;

        public Integer getDistance() {
            return distance;
        }

        public void setDistance(Integer distance) {
            this.distance = distance;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

    }
}