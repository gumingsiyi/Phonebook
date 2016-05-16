package com.stiles.model;

public class ContactBean {

    private int Id;
    private String Name;
    private String PhoneNum;
    private String Email;
    private String PinYin;
    private String FirstPinYin;

    public ContactBean() {
    }

    public ContactBean(int id, String name, String phoneNum, String email) {
        Id = id;
        Name = name;
        PhoneNum = phoneNum;
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPinYin() {
        return PinYin;
    }

    public void setPinYin(String pinYin) {
        PinYin = pinYin;
    }

    public String getFirstPinYin() {
        return FirstPinYin;
    }

    public void setFirstPinYin(String firstPinYin) {
        FirstPinYin = firstPinYin;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String toString() {
        return "姓名：" + getName() + "   拼音：" + getPinYin() + "    首字母："
                + getFirstPinYin();

    }
}