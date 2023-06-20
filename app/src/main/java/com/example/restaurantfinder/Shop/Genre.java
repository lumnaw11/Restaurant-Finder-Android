package com.example.restaurantfinder.Shop;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "genre", strict = false)
public class Genre implements Serializable {
    @Element(name = "code")
    private String code;

    @Element(name = "name")
    private String name;

    @Element(name = "catch")
    private String genreCatch;

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
