package com.example.mystory;

public class datarec3 {
    String caseActivie;
    String num;
    String duration;
    String keyofregistration;
    String keysubscriber;

    public datarec3() {
    }

    public datarec3(String caseActivie, String num, String duration, String keyofregistration, String keysubscriber) {
        this.caseActivie = caseActivie;
        this.num = num;
        this.duration = duration;
        this.keyofregistration = keyofregistration;
        this.keysubscriber = keysubscriber;
    }

    public String getCaseActivie() {
        return caseActivie;
    }

    public void setCaseActivie(String caseActivie) {
        this.caseActivie = caseActivie;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getKeyofregistration() {
        return keyofregistration;
    }

    public void setKeyofregistration(String keyofregistration) {
        this.keyofregistration = keyofregistration;
    }

    public String getKeysubscriber() {
        return keysubscriber;
    }

    public void setKeysubscriber(String keysubscriber) {
        this.keysubscriber = keysubscriber;
    }
}
