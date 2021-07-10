package com.example.mystory;

public class data {
    String name;
    String num;
    String photo;

    public data() {
    }

    public data(String name, String num, String photo) {
        this.name = name;
        this.num = num;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
