package com.np;

import com.univocity.parsers.annotations.Parsed;

import java.io.Serializable;

public class TwitterBean implements Serializable {
    @Parsed(index = 0, defaultNullRead = "0")
    public Integer grp;

    @Parsed(index = 1, defaultNullRead = "0")
    public Integer f1;

    @Parsed(index = 2, defaultNullRead = "0")
    public Integer f2;

    @Parsed(index = 3, defaultNullRead = "0")
    public Integer f3;

    @Parsed(index = 4, defaultNullRead = "0")
    public Integer f4;

    @Parsed(index = 5, defaultNullRead = "0")
    public Integer f5;

    @Parsed(index = 6, defaultNullRead = "0")
    public Integer f6;

    @Parsed(index = 7, defaultNullRead = "0")
    public Integer f7;

    @Parsed(index = 8, defaultNullRead = "0")
    public Integer f8;

    @Parsed(index = 9, defaultNullRead = "0")
    public Integer f9;

    @Parsed(index = 10, defaultNullRead = "0")
    public Integer f10;

    public TwitterBean() {
    }

    public Integer getGrp() {
        return grp;
    }

    public void setGrp(Integer grp) {
        this.grp = grp;
    }

    public Integer getF1() {
        return f1;
    }

    public void setF1(Integer f1) {
        this.f1 = f1;
    }

    public Integer getF2() {
        return f2;
    }

    public void setF2(Integer f2) {
        this.f2 = f2;
    }

    public Integer getF3() {
        return f3;
    }

    public void setF3(Integer f3) {
        this.f3 = f3;
    }

    public Integer getF4() {
        return f4;
    }

    public void setF4(Integer f4) {
        this.f4 = f4;
    }

    public Integer getF5() {
        return f5;
    }

    public void setF5(Integer f5) {
        this.f5 = f5;
    }

    public Integer getF6() {
        return f6;
    }

    public void setF6(Integer f6) {
        this.f6 = f6;
    }

    public Integer getF7() {
        return f7;
    }

    public void setF7(Integer f7) {
        this.f7 = f7;
    }

    public Integer getF8() {
        return f8;
    }

    public void setF8(Integer f8) {
        this.f8 = f8;
    }

    public Integer getF9() {
        return f9;
    }

    public void setF9(Integer f9) {
        this.f9 = f9;
    }

    public Integer getF10() {
        return f10;
    }

    public void setF10(Integer f10) {
        this.f10 = f10;
    }

    @Override
    public String toString() {
        return "TwitterBean{" +
                "grp=" + grp +
                ", f1=" + f1 +
                ", f2=" + f2 +
                ", f3=" + f3 +
                ", f4=" + f4 +
                ", f5=" + f5 +
                ", f6=" + f6 +
                ", f7=" + f7 +
                ", f8=" + f8 +
                ", f9=" + f9 +
                ", f10=" + f10 +
                '}';
    }
}
