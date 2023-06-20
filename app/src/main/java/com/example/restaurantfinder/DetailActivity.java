package com.example.restaurantfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.restaurantfinder.R.id;
import com.example.restaurantfinder.Shop.Shop;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        Shop shopItem = (Shop) intent.getSerializableExtra("shopItem");

        TextView txtName = findViewById(R.id.txtResName);
        txtName.setText(shopItem.getName());

        TextView txtAccess = findViewById(R.id.txtAccess);
        txtAccess.setText(shopItem.getMobileAccess());

        TextView txtLocation = findViewById(R.id.txtLocation);
        txtLocation.setText(shopItem.getAddress());
        txtLocation.setPaintFlags(txtLocation.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, MapsActivity.class);
                intent.putExtra("shop", shopItem);
                startActivity(intent);
            }
        });


        TextView txtResHours = findViewById(R.id.txtResHours);
        txtResHours.setText(shopItem.getOpen());

        TextView txtBudget = findViewById(R.id.txtResBudget);
        txtBudget.setText(shopItem.getBudget().getName());

        TextView txtParking = findViewById(R.id.txtParking);
        txtParking.setText("駐車：" + shopItem.getParking());

        TextView txtWifi = findViewById(R.id.txtWifi);
        txtWifi.setText("WiFi： " + shopItem.getWifi());

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView txtUrl = findViewById(id.txtUrl);
        txtUrl.setPaintFlags(txtLocation.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(shopItem.getUrls().getPc()));
                startActivity(intent);
            }
        });

        ImageView imgBanner = findViewById(R.id.imgResImage);
        String imgUrl = shopItem.getPhotos().get(0).getMobile().getL();

        Glide.with(this).load(imgUrl).into(imgBanner);

    }
}