package com.taotao.rest.util;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CatNode {
    @JsonProperty("n")
    private String n;
    @JsonProperty("u")
    private String u;
    @JsonProperty("i")
    private List<?> i;

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
    }

    public List<?> getI() {
        return i;
    }

    public void setI(List<?> i) {
        this.i = i;
    }
}
