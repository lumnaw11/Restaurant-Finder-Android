package com.example.restaurantfinder.Shop;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "photo")
public class Photo implements Serializable {
    @Element(name = "pc")
    private PcPhoto pc;

    @Element(name = "mobile")
    private Mobile mobile;

    //Getters and setters
    public Mobile getMobile() {
        return mobile;
    }

    public PcPhoto getPc() {
        return pc;
    }
}
