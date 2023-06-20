package com.example.restaurantfinder.Shop;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "sub_genre", strict = false)
public class SubGenre implements Serializable {
    @Element(name = "code")
    private String code;

    @Element(name = "name")
    private String name;

}
