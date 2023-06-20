package com.example.restaurantfinder;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.restaurantfinder.Shop.Shop;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.restaurantfinder.databinding.ActivityMapsBinding;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static double SHOP_LATITUDE = 35;
    private static double SHOP_LONGITUDE = 139;
    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    private List<Shop> shopList;

    private Shop shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        shop = (Shop) intent.getSerializableExtra("shop");

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        UiSettings ui = mMap.getUiSettings();
        ui.setCompassEnabled(true);
        ui.setRotateGesturesEnabled(true);
        ui.setScrollGesturesEnabled(true);
        ui.setZoomControlsEnabled(true);

        LatLng shopLocation = new LatLng(Double.parseDouble(shop.getLat()), Double.parseDouble(shop.getLng()));
        mMap.addMarker(new MarkerOptions().position(shopLocation).title(shop.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(shopLocation, 15.5f));

    }
}