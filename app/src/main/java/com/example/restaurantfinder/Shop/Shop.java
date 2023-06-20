package com.example.restaurantfinder.Shop;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

@Root(name = "shop", strict = false)
public class Shop implements Serializable {
    @Element(name = "id")
    private String id;

    @Element(name = "name")
    private String name;

    @Element(name = "logo_image")
    private String logoImage;

    @Element(name = "name_kana")
    private String nameKana;

    @Element(name = "address")
    private String address;

    @Element(name = "station_name")
    private String stationName;

    @Element(name = "lat")
    private String lat;

    @Element(name = "lng")
    private String lng;

    @Element(name = "genre")
    private Genre genre;

    @Element(name = "sub_genre", required = false)
    private SubGenre subGenre;

    @Element(name = "budget")
    private Budget budget;

    @Element(name = "budget_memo", required = false)
    private String budgetMemo;

    @Element(name = "catch", required = false)
    private String shopCatch;

    @Element(name = "capacity", required = false)
    private int capacity;

    @Element(name = "access")
    private String access;

    public Urls getUrls() {
        return urls;
    }

    @Element(name = "mobile_access")
    private String mobileAccess;

    @Element(name = "urls")
    private Urls urls;

    @Element(name = "open")
    private String open;

    @Element(name = "close")
    private String close;

    @Element(name = "party_capacity", required = false)
    private int partyCapacity;

    @Element(name = "wifi")
    private String wifi;

    @Element(name = "other_memo", required = false)
    private String otherMemo;

    @Element(name = "shop_detail_memo", required = false)
    private String shopDetailMemo;

    @Element(name = "wedding", required = false)
    private String wedding;

    @Element(name = "free_drink", required = false)
    private String freeDrink;

    @Element(name = "free_food", required = false)
    private String freeFood;

    @Element(name = "private_room", required = false)
    private String privateRoom;

    @Element(name = "horigotatsu", required = false)
    private String horigotatsu;

    @Element(name = "tatami", required = false)
    private String tatami;

    @Element(name = "card", required = false)
    private String card;

    @Element(name = "non_smoking", required = false)
    private String nonSmoking;

    @Element(name = "charter", required = false)
    private String charter;

    @Element(name = "parking")
    private String parking;

    @Element(name = "barrier_free", required = false)
    private String barrierFree;

    @Element(name = "show", required = false)
    private String show;

    @Element(name = "karaoke", required = false)
    private String karaoke;

    @Element(name = "band", required = false)
    private String band;

    @Element(name = "tv", required = false)
    private String tv;

    @Element(name = "english", required = false)
    private String english;

    @Element(name = "pet", required = false)
    private String pet;

    @Element(name = "child", required = false)
    private String child;

    @ElementList(name = "coupon_urls", inline = true, required = false)
    private List<String> couponUrls;

    @ElementList(name = "photo", inline = true)
    private List<Photo> photos;

    @Element(name = "course", required = false)
    private String course;

    @Element(name = "lunch", required = false)
    private String lunch;

    @Element(name = "midnight", required = false)
    private String midnight;

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getMobileAccess() {
        return mobileAccess;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }
}
