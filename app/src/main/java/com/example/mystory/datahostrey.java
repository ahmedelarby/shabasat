package com.example.mystory;

public class datahostrey {
    String time;
    String duration;
    String numbercard;
    String rsedafter;
    String typepay;
    String cutraseed;
    String avalibol;

    public datahostrey() {
    }

    public datahostrey(String time, String duration, String numbercard, String rsedafter, String typepay, String cutraseed, String avalibol) {
        this.time = time;
        this.duration = duration;
        this.numbercard = numbercard;
        this.rsedafter = rsedafter;
        this.typepay = typepay;
        this.cutraseed = cutraseed;
        this.avalibol = avalibol;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getNumbercard() {
        return numbercard;
    }

    public void setNumbercard(String numbercard) {
        this.numbercard = numbercard;
    }

    public String getRsedafter() {
        return rsedafter;
    }

    public void setRsedafter(String rsedafter) {
        this.rsedafter = rsedafter;
    }

    public String getTypepay() {
        return typepay;
    }

    public void setTypepay(String typepay) {
        this.typepay = typepay;
    }

    public String getCutraseed() {
        return cutraseed;
    }

    public void setCutraseed(String cutraseed) {
        this.cutraseed = cutraseed;
    }

    public String getAvalibol() {
        return avalibol;
    }

    public void setAvalibol(String avalibol) {
        this.avalibol = avalibol;
    }
}
