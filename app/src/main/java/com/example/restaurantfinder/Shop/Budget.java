package com.example.restaurantfinder.Shop;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "budget", strict = false)
public class Budget implements Serializable {
    @Element(name = "code", required = false)
    private String code;

    @Element(name = "name", required = false)
    private String name;

    @Element(name = "average", required = false)
    private String average;

    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
