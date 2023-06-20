package com.example.restaurantfinder;

import com.example.restaurantfinder.Shop.Shop;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

//Search response for API call
@Root(name = "results", strict = false)
public class SearchResponse {
    @ElementList(name = "shop", inline = true, required = false)
    private List<Shop> shops;

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }
}


