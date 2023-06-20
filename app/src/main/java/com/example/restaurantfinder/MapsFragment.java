package com.example.restaurantfinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.restaurantfinder.Shop.Shop;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsFragment extends Fragment implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter {

    private static double LATITUDE = 35;
    private static double LONGITUDE = 139;
    private GoogleMap mMap;

    private List<Shop> shopList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Intent intent = getActivity().getIntent();
        shopList = (List<Shop>) intent.getSerializableExtra("shopList");
        LATITUDE = intent.getDoubleExtra("LAT", 35);
        LONGITUDE = intent.getDoubleExtra("LNG", 139);

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling ActivityCompat#requestPermissions here to request the missing permissions.
            return;
        }
        mMap.setMyLocationEnabled(true);


        UiSettings ui = mMap.getUiSettings();
        ui.setCompassEnabled(true);
        ui.setRotateGesturesEnabled(true);
        ui.setScrollGesturesEnabled(true);
        ui.setZoomControlsEnabled(true);

        for (Shop shop : shopList) {
            LatLng shopLocation = new LatLng(Double.parseDouble(shop.getLat()), Double.parseDouble(shop.getLng()));
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(shopLocation)
                    .title(shop.getName());

            mMap.addMarker(markerOptions);

        }


        if (!shopList.isEmpty()) {
            LatLng firstShopLocation = new LatLng(Double.parseDouble(shopList.get(0).getLat()), Double.parseDouble(shopList.get(0).getLng()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstShopLocation, 15.5f));
        }

        mMap.setInfoWindowAdapter(this);
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(@NonNull Marker marker) {
                String shopName = marker.getTitle();
                Shop selectedShop = null;

                // Find the selected shop from the shopList
                for (Shop shop : shopList) {
                    if (shop.getName().equals(shopName)) {
                        selectedShop = shop;
                        break;
                    }
                }
                // Handle the click event and navigate to DetailActivity
                if (selectedShop != null) {
                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    intent.putExtra("shopItem", selectedShop);
                    startActivity(intent);
                }
            }
        });
    }

    @Nullable
    @Override
    public View getInfoContents(@NonNull Marker marker) {
        return null;
    }

    @Nullable
    @Override
    public View getInfoWindow(@NonNull Marker marker) {

        View infoWindow = getLayoutInflater().inflate(R.layout.custom_info_window, null);
        TextView shopNameTextView = infoWindow.findViewById(R.id.shop_name);

        String shopName = marker.getTitle();
        shopNameTextView.setText(shopName);

        return infoWindow;

    }

}
