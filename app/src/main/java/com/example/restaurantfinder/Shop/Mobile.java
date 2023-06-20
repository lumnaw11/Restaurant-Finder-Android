package com.example.restaurantfinder.Shop;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "mobile")
public class Mobile implements Serializable {
    @Element(name = "l", required = false)
    String l;
    @Element(name = "s", required = false)
    String s;

    //Getters and setters
    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
