package com.example.mystory;

public class modeldatataswya {
    String Balance;
    String Requestname;
    String key;
    String name;

    public modeldatataswya() {
    }

    public modeldatataswya(String balance, String requestname, String key, String name) {
        Balance = balance;
        Requestname = requestname;
        this.key = key;
        this.name = name;
    }

    public String getBalance() {
        return Balance;
    }

    public void setBalance(String balance) {
        Balance = balance;
    }

    public String getRequestname() {
        return Requestname;
    }

    public void setRequestname(String requestname) {
        Requestname = requestname;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
