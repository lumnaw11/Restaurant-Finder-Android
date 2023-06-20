package com.example.restaurantfinder;

import static com.example.restaurantfinder.R.id.resultList2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.restaurantfinder.Shop.Shop;
import java.util.List;

public class ShopListFragment extends Fragment {
    private CustomAdapter adapter;
    private ListView listView;
    private List<Shop> shopList;
    private double LATITUDE;
    private double LONGITUDE;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shop_list, container, false);

        // Initialize views
        listView = rootView.findViewById(resultList2);

        // Get data from activity intent
        Intent intent = getActivity().getIntent();
        shopList = (List<Shop>) intent.getSerializableExtra("shopList");
        LATITUDE = intent.getDoubleExtra("LAT", 35);
        LONGITUDE = intent.getDoubleExtra("LNG", 139);

        // Set up the list view and adapter
        adapter = new CustomAdapter(getActivity(), R.layout.row_item, shopList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(getActivity(), DetailActivity.class);
                Shop shop = shopList.get(i);
                intent1.putExtra("shopItem", shop);
                startActivity(intent1);
            }
        });

        return rootView;
    }

    public class CustomAdapter extends ArrayAdapter<Shop> {
        private List<Shop> shopList;
        private int layoutResourceId;
        private LayoutInflater inflater;

        public CustomAdapter(Context context, int layoutResourceId, List<Shop> shopList) {
            super(context, layoutResourceId, shopList);
            this.layoutResourceId = layoutResourceId;
            this.shopList = shopList;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            CustomAdapter.ViewHolder holder;

            if (row == null) {
                row = inflater.inflate(layoutResourceId, parent, false);

                holder = new CustomAdapter.ViewHolder();
                holder.nameTextView = row.findViewById(R.id.txtName);
                holder.logoImage = row.findViewById(R.id.imgLogo);
                holder.shopAccess = row.findViewById(R.id.txtListAcess);
                holder.budgetTextView = row.findViewById(R.id.txtBudget);
                holder.distance = row.findViewById(R.id.txtDistance);

                row.setTag(holder);
            } else {
                holder = (CustomAdapter.ViewHolder) row.getTag();
            }

            // Get the Shop object for the current position
            Shop shop = shopList.get(position);

            // Set the data to the views
            holder.nameTextView.setText(shop.getName());
            holder.shopAccess.setText(shop.getMobileAccess());
            holder.budgetTextView.setText(shop.getBudget().getName());
            String dist = String.valueOf(calculateDistance(shop.getLat(),shop.getLng()));
            holder.distance.setText(dist + "m");
            String mobilePhoto = shop.getPhotos().get(0).getMobile().getL();
//            Log.i("Shop.getName","shop__" + mobilePhoto);

            Glide.with(getActivity()).load(mobilePhoto).into(holder.logoImage);

            return row;
        }
        private class ViewHolder {
            TextView nameTextView;
            ImageView logoImage;
            TextView shopAccess;
            TextView budgetTextView;
            TextView distance;
        }
    }

    private int calculateDistance(String strLat, String strLng) {
        Double lat2 = Double.parseDouble(strLat);
        Double lng2 = Double.parseDouble(strLng);
        double theta = LONGITUDE - lng2;
        double dist = Math.sin(deg2rad(LATITUDE)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(LATITUDE)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515 * 1609.344 ;
//        Log.i("dist", "dist" + dist);
        return (int)dist;

    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
