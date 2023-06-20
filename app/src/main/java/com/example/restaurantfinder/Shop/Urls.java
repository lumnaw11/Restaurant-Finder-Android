package com.example.restaurantfinder.Shop;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "urls", strict = false)
public class Urls implements Serializable {
    @Element(name = "pc")
    private String pc;

    // Getters and setters...

    public String getPc() {
        return pc;
    }
}
