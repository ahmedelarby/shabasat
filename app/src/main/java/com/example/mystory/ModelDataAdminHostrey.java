package com.example.mystory;

public class ModelDataAdminHostrey {
    String name;
    String time;
    String KeyRequste;
    String PaymentType;
    String pic;

    public ModelDataAdminHostrey() {
    }

    public ModelDataAdminHostrey(String name, String time, String keyRequste, String paymentType, String pic) {
        this.name = name;
        this.time = time;
        KeyRequste = keyRequste;
        PaymentType = paymentType;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKeyRequste() {
        return KeyRequste;
    }

    public void setKeyRequste(String keyRequste) {
        KeyRequste = keyRequste;
    }

    public String getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(String paymentType) {
        PaymentType = paymentType;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
