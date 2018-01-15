package com.example.testzedkz;

/**
 * Created by nurik on 13.01.2018.
 */

public class DataList {

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public DataList(String firstName, String email, String photo, String lastName, String gender, String ipAdress, String work, String workPosition) {
        this.firstName = firstName + " " + lastName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.ipAdress = ipAdress;
        this.work = work;
        this.workPosition = workPosition;
        this.photo = photo;
    }
    public String firstName;
    public String email;
    public String lastName;
    public String gender;
    public String ipAdress;
    public String photo;

    public String getWork() {
        return work;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public String work;
    public String workPosition;
}
