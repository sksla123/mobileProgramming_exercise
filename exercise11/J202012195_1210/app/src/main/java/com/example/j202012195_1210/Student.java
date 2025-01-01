package com.example.j202012195_1210;

public class Student {
    private String hakgwa;
    private String hakbun;
    private String name;

    public Student(String hakgwa, String hakbun, String name) {
        this.hakgwa = hakgwa;
        this.hakbun = hakbun;
        this.name = name;
    }

    public String getHakgwa() {
        return hakgwa;
    }

    public void setHakgwa(String hakgwa) {
        this.hakgwa = hakgwa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHakbun() {
        return hakbun;
    }

    public void setHakbun(String hakbun) {
        this.hakbun = hakbun;
    }

}
