package com.example.hotel;

public class User {
    String name, phone,address,age,visits;

    public User() {
    }

    public User(String name, String phone, String address, String age, String visits) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.age = age;
        this.visits = visits;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getVisits() {
        return visits;
    }

    public void setVisits(String visits) {
        this.visits = visits;
    }
}
