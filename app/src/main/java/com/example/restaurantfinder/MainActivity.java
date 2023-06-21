package com.example.restaurantfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.restaurantfinder.Shop.Shop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {
    private final String API_KEY = "9279b9f5a83163fb";
    private double LATITUDE = 35.652832;
    private double LONGITUDE = 139.839478;
    private static int RANGE = 1;
    private static final int ORDER = 3;
    private static final int COUNT = 25;
    private static String GENRE ="G004";
    private LocationManager locationManager;
    private LocationListener locationListener;
    private List<Shop> shopList = new ArrayList<>();

    Button btnSearch;

    Spinner spnrGenre;
    Spinner spnrRange;

    EditText edtSearch;

    String KEYWORD = "";

    String genreSelected = "すべて";

    Boolean genreIsSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSearch = findViewById(R.id.edtSearch);
        //現在位置GPSで取得

        locationManager = (LocationManager) getSystemService(MainActivity.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LATITUDE = location.getLatitude();
                LONGITUDE = location.getLongitude();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };


        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchShop();
            }
        });

        spnrGenre = findViewById(R.id.genreSpnr);
        spnrGenre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                genreSelected = spnrGenre.getSelectedItem().toString();
                if (genreSelected.equals("すべて")){
                    genreIsSelected = false;
                } else {
                    genreIsSelected = true;
                    GENRE = getGenreCode(genreSelected);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });

        spnrRange = findViewById(R.id.rangeSpnr);
        spnrRange.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = spnrRange.getSelectedItem().toString();
                RANGE = getRange(selected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        } else {
            //Request the permission from the user
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, "このアプリが正しく機能するには、位置情報の許可が必要です。", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                }
            } else {
                Toast.makeText(this, "位置情報の許可が拒否されました。 アプリの一部の機能が動作しない可能性があります。", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager.removeUpdates(locationListener);
        }

    }

    private void searchShop(){
        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://webservice.recruit.co.jp/hotpepper/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        // Create an instance of the MyApi interface
        MyApi myApi = retrofit.create(MyApi.class);

        Call<SearchResponse> call;
        if (genreIsSelected && edtSearch.getText().toString().equals("")){
            call = myApi.getShopsGenre(API_KEY, LATITUDE, LONGITUDE, RANGE, ORDER, COUNT, GENRE);
//            Log.i("call__", "1");
//            Log.i("LOCATION", "lat: " + LATITUDE + "lng: " + LONGITUDE);
        } else if (genreIsSelected && edtSearch.getText().toString().length() != 0 ) {
            call = myApi.getShopsKeywordAndGenre(API_KEY, LATITUDE, LONGITUDE, RANGE, ORDER, COUNT, GENRE, KEYWORD);
//            Log.i("call__", "2");
//            Log.i("LOCATION", "lat: " + LATITUDE + "lng: " + LONGITUDE);
        } else if (!genreIsSelected && edtSearch.getText().toString().length() != 0 ) {
            call = myApi.getShopsKeyword(API_KEY, LATITUDE, LONGITUDE, RANGE, ORDER, COUNT, KEYWORD);
//            Log.i("call__", "3");
//            Log.i("LOCATION", "lat: " + LATITUDE + "lng: " + LONGITUDE);
        } else {
            call = myApi.getShops(API_KEY, LATITUDE, LONGITUDE, RANGE, ORDER, COUNT);
//            Log.i("call__", "4");
//            Log.i("LOCATION", "lat: " + LATITUDE + "lng: " + LONGITUDE);
        }

        // Perform the network request asynchronously
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, retrofit2.Response<SearchResponse> response) {
                if (response.isSuccessful()) {
                    SearchResponse searchResponse = response.body();
                    if (searchResponse != null && searchResponse.getShops() != null) {
                        // Retrieve the list of shops from the response
                        shopList = searchResponse.getShops();

                        // Pass the list of shops to the ListActivity
                        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                        intent.putExtra("shopList", (Serializable) shopList);
                        intent.putExtra("LAT", LATITUDE);
                        intent.putExtra("LNG", LONGITUDE);

                        Log.i("Button response", "response");
                        startActivity(intent);

                        Log.d("API_RESPONSE", searchResponse.toString());
                    } else {
//                        Toast.makeText(MainActivity.this, "0 件", Toast.LENGTH_LONG).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("検索失敗")
                                .setMessage("条件に会うレストラン見つかりません。")
                                .setIcon(R.mipmap.ic_launcher)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    }
                } else {
                    Log.e("API_ERROR", response.message());
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                // Handle network failure
                Log.e("CallFailure", "API call failed", t);
            }
        });

    }

    private String getGenreCode(String genreStr) {
        switch (genreStr){
            case "居酒屋":
                GENRE = "G001";
                break;
            case "ダイニングバー・バル":
                GENRE = "G002";
                break;
            case "創作料理":
                GENRE = "G003";
                break;
            case "和食":
                GENRE = "G004";
                break;
            case "洋食":
                GENRE = "G005";
                break;
            case "イタリアン・フレンチ":
                GENRE = "G006";
                break;
            case "中華":
                GENRE = "G007";
                break;
            case "焼肉・ホルモン":
                GENRE = "G008";
                break;
            case "韓国料理":
                GENRE = "G017";
                break;
            case "アジア・エスニック料理":
                GENRE = "G009";
                break;
            case "各国料理":
                GENRE = "G010";
                break;
            case "カラオケ・パーティ":
                GENRE = "G011";
                break;
            case "バー・カクテル":
                GENRE = "G012";
                break;
            case "ラーメン":
                GENRE = "G013";
                break;
            case "お好み焼き・もんじゃ":
                GENRE = "G016";
                break;
            case "カフェ・スイーツ":
                GENRE = "G014";
                break;
            case "その他グルメ":
                GENRE = "G015";
                break;
            default:
                GENRE = "";
                break;
        }

        return GENRE;
    }

    private int getRange(String range){
        switch (range){
            case "300m":
                RANGE = 1;
                break;
            case "500m":
                RANGE = 2;
                break;
            case "1000m":
                RANGE = 3;
                break;
            case "2000m":
                RANGE = 4;
                break;
            case "3000m":
                RANGE = 5;
                break;
            default:
                RANGE = 4;
                break;
        }

        return RANGE;
    }
}
