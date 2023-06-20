package com.example.restaurantfinder.Shop;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "pc", strict = false)
public class PcPhoto implements Serializable {
    @Element(name = "l", required = false)
    private String l;

    @Element(name = "m", required = false)
    private String m;

    @Element(name = "s", required = false)
    private String s;

    //Getters and setters
    public String getL() {
        return l;
    }


    public String getM() {
        return m;
    }

    public String getS() {
        return s;
    }
}
