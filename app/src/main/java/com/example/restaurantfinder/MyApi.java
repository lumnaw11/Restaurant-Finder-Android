package com.example.restaurantfinder;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApi {
    @GET("gourmet/v1/")
    Call<SearchResponse> getShops(
            @Query("key") String apiKey,
            @Query("lat") double latitude,
            @Query("lng") double longitude,
            @Query("range") int range,
            @Query("order") int order,
            @Query("count") int count
    );

    @GET("gourmet/v1/")
    Call<SearchResponse> getShopsGenre(
            @Query("key") String apiKey,
            @Query("lat") double latitude,
            @Query("lng") double longitude,
            @Query("range") int range,
            @Query("order") int order,
            @Query("count") int count,
            @Query("genre") String genre
    );

    @GET("gourmet/v1/")
    Call<SearchResponse> getShopsKeywordAndGenre(
            @Query("key") String apiKey,
            @Query("lat") double latitude,
            @Query("lng") double longitude,
            @Query("range") int range,
            @Query("order") int order,
            @Query("count") int count,
            @Query("genre") String genre,
            @Query("keyword") String keyword
    );

    @GET("gourmet/v1/")
    Call<SearchResponse> getShopsKeyword(
            @Query("key") String apiKey,
            @Query("lat") double latitude,
            @Query("lng") double longitude,
            @Query("range") int range,
            @Query("order") int order,
            @Query("count") int count,
            @Query("keyword") String keyword
    );

}