package com.xunmeng.jpush.entity;

public class UserEntity {
    private String open_id;
    private String union_id;
    private String nickname;
    private String avatar;
    private String mobile;
    private String subscribe;
    private String subscribe_time;
    private int gender; // 0 f 1 m
    private String city;
    private String province;
    private String country;
    private String remark;

    public UserEntity(String open_id, String union_id, String nickname,
                      String avatar, String mobile, String subscribe,
                      String subscribe_time, int gender, String city, String province,
                      String country, String remark) {
        super();
        this.open_id = open_id;
        this.union_id = union_id;
        this.nickname = nickname;
        this.avatar = avatar;
        this.mobile = mobile;
        this.subscribe = subscribe;
        this.subscribe_time = subscribe_time;
        this.gender = gender;
        this.city = city;
        this.province = province;
        this.country = country;
        this.remark = remark;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getUnion_id() {
        return union_id;
    }

    public void setUnion_id(String union_id) {
        this.union_id = union_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public String getSubscribe_time() {
        return subscribe_time;
    }

    public void setSubscribe_time(String subscribe_time) {
        this.subscribe_time = subscribe_time;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    @Override
    public String toString() {
        return "{\"open_id\":\"" + open_id + "\", \"union_id\":\"" + union_id
                + "\", \"nickname\":\"" + nickname + "\", \"avatar\":\""
                + avatar + "\", \"mobile\":\"" + mobile
                + "\", \"subscribe\":\"" + subscribe
                + "\", subscribe_time\":\"" + subscribe_time
                + "\", \"gender\":\"" + gender + "\", \"city\":\"" + city
                + "\", \"province\":\"" + province + "\", \"country\":\""
                + country + "\", \"remark\":\"" + remark + "\"}";
    }

}
