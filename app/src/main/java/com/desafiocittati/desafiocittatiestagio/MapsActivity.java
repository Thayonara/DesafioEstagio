package com.desafiocittati.desafiocittatiestagio;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ImageView ivRestaurant, ivBank, ivGasStation,ivPublicZone, ivShop;
    private FrameLayout frameRestaurant, frameBank, frameGasStation, framePublicZone, frameShop;
    private  Map<Integer,List<LatLng>> locations = new HashMap<Integer, List<LatLng>>();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


         sharedPreferences = getSharedPreferences(Constants.SHAREDPREFERENCES
                , Context.MODE_PRIVATE);
         editor = sharedPreferences.edit();

        ivRestaurant = (ImageView) findViewById(R.id.iv_restaurant);
        ivBank = (ImageView) findViewById(R.id.iv_bank);
        ivGasStation = (ImageView) findViewById(R.id.iv_gas);
        ivPublicZone = (ImageView) findViewById(R.id.iv_public);
        ivShop = (ImageView) findViewById(R.id.iv_shop);

        frameRestaurant = (FrameLayout) findViewById(R.id.triangle1);
        frameBank = (FrameLayout) findViewById(R.id.triangle2);
        frameGasStation = (FrameLayout) findViewById(R.id.triangle3);
        framePublicZone = (FrameLayout) findViewById(R.id.triangle4);
        frameShop = (FrameLayout) findViewById(R.id.triangle5);

        ivRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisibilityMode(Constants.RESTAURANT);
            }
        });

        ivBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisibilityMode(Constants.BANK);
            }
        });

        ivGasStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisibilityMode(Constants.GAS_STATION);
            }
        });

        ivPublicZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisibilityMode(Constants.PUBLIC_ZONE);
            }
        });

        ivShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisibilityMode(Constants.SHOP);
            }
        });

    }


    @Override
    protected void onResume() {
        locations = new HashMap<Integer, List<LatLng>>();


        if(sharedPreferences.getBoolean(Constants.LOADED_JSON, false)) {
            locations.put(1, new LocationsDBController(getApplication()).getAllLocationsRestaurant(getApplication()));
            locations.put(2, new LocationsDBController(getApplication()).getAllLocationsBank(getApplication()));
            locations.put(3, new LocationsDBController(getApplication()).getAllLocationsGasStation(getApplication()));
            locations.put(4, new LocationsDBController(getApplication()).getAllLocationsPublicZone(getApplication()));
            locations.put(5, new LocationsDBController(getApplication()).getAllLocationsShop(getApplication()));
            if (!(sharedPreferences.getBoolean(Constants.SELECTED_RESTAURANT, true))) {
                frameRestaurant.setVisibility(View.INVISIBLE);
                deleteCategoryTemp(1);
            }
            if (!(sharedPreferences.getBoolean(Constants.SELECTED_BANK, true))) {
                frameBank.setVisibility(View.INVISIBLE);

                deleteCategoryTemp(2);
            }
            if (!(sharedPreferences.getBoolean(Constants.SELECTED_GAS_STATION, true))) {
                frameGasStation.setVisibility(View.INVISIBLE);

                deleteCategoryTemp(3);
            }
            if (!(sharedPreferences.getBoolean(Constants.SELECTED_PUBLIC_ZONE, true))) {
                framePublicZone.setVisibility(View.INVISIBLE);

                deleteCategoryTemp(4);
            }
            if (!(sharedPreferences.getBoolean(Constants.SELECTED_SHOP, true))) {
                frameShop.setVisibility(View.INVISIBLE);

                deleteCategoryTemp(5);
            }
        }
        super.onResume();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        mMap.getUiSettings().setZoomControlsEnabled(true);

        if(!(sharedPreferences.getBoolean(Constants.LOADED_JSON, false))){
            locations = new DataParser().parse(getApplication());
            editor.putBoolean(Constants.LOADED_JSON, true);
            editor.apply();
        }

        for(int i = 1; i <= 5; i++){
            if(locations.get(i) != null){
                for(int j = 0; j < locations.get(i).size(); j++){
                    addMarker(locations.get(i).get(j),i);
                }
            }
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-8.055368, -34.870393), 12));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void addMarker(LatLng latLng, int category){
        MarkerOptions marker = new MarkerOptions();
        marker.position(latLng);
        if(category ==1){
            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

        } else if( category ==2){
            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));

        }else if( category ==3){
            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));

        }else if( category ==4){
            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));

        }else if( category ==5){
            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

        }
        mMap.addMarker(marker);

    }

    public void deleteCategoryTemp(int category){
        locations.remove(category);
    }

    public void addCategoryTemp(int category){
        switch (category){
            case 1:
                locations.put(category, new LocationsDBController(getApplication()).getAllLocationsRestaurant(getApplication()));
                break;
            case 2:
                locations.put(category, new LocationsDBController(getApplication()).getAllLocationsBank(getApplication()));
                break;
            case 3:
                locations.put(category, new LocationsDBController(getApplication()).getAllLocationsGasStation(getApplication()));
                break;
            case 4:
                locations.put(category, new LocationsDBController(getApplication()).getAllLocationsPublicZone(getApplication()));
                break;
            case 5:
                locations.put(category, new LocationsDBController(getApplication()).getAllLocationsShop(getApplication()));
                break;
            default:
                Log.i("Main", "Categoria não válida");
        }
    }

    public void updateMap(){
        mMap.clear();
        for(int i = 1; i <= 5; i++){
            if(locations.get(i) != null){
                for(int j = 0; j < locations.get(i).size(); j++){
                    addMarker(locations.get(i).get(j),i);
                }
            }
        }
        if(locations.get(5) == null){
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-8.055368, -34.870393), 15));

        } else{
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-8.055368, -34.870393), 12));

        }
    }


    public void setVisibilityMode(String mode){
        switch (mode){
            case "restaurant":

                if(frameRestaurant.getVisibility() == View.VISIBLE){

                    if(locations.size()>1) {
                        editor.putBoolean(Constants.SELECTED_RESTAURANT, false);
                        editor.apply();
                        frameRestaurant.setVisibility(View.INVISIBLE);
                        deleteCategoryTemp(1);
                    } else{
                        Toast.makeText(getApplication(), "Ação não permitida", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    editor.putBoolean(Constants.SELECTED_RESTAURANT, true);
                    editor.apply();
                    frameRestaurant.setVisibility(View.VISIBLE);
                    addCategoryTemp(1);

                }
                updateMap();
                break;
            case "bank":
                if(frameBank.getVisibility() == View.VISIBLE){
                    if(locations.size()>1) {
                        editor.putBoolean(Constants.SELECTED_BANK, false);
                        editor.apply();
                        frameBank.setVisibility(View.INVISIBLE);
                        deleteCategoryTemp(2);
                    } else{
                        Toast.makeText(getApplication(), "Ação não permitida", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    editor.putBoolean(Constants.SELECTED_BANK, true);
                    editor.apply();
                    frameBank.setVisibility(View.VISIBLE);
                    addCategoryTemp(2);

                }
                updateMap();
                break;
            case "gas_stations":

                if(frameGasStation.getVisibility() == View.VISIBLE){
                    if(locations.size()>1) {
                        editor.putBoolean(Constants.SELECTED_GAS_STATION, false);
                        editor.apply();
                        frameGasStation.setVisibility(View.INVISIBLE);
                        deleteCategoryTemp(3);
                    } else{
                        Toast.makeText(getApplication(), "Ação não permitida", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    editor.putBoolean(Constants.SELECTED_GAS_STATION, true);
                    editor.apply();
                    frameGasStation.setVisibility(View.VISIBLE);
                    addCategoryTemp(3);

                }
                updateMap();
                break;
            case "public_zone":

                if(framePublicZone.getVisibility() == View.VISIBLE){
                    if(locations.size()>1) {
                        editor.putBoolean(Constants.SELECTED_PUBLIC_ZONE, false);
                        editor.apply();
                        framePublicZone.setVisibility(View.INVISIBLE);
                        deleteCategoryTemp(4);
                    } else{
                        Toast.makeText(getApplication(), "Ação não permitida", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    editor.putBoolean(Constants.SELECTED_PUBLIC_ZONE, true);
                    editor.apply();
                    framePublicZone.setVisibility(View.VISIBLE);
                    addCategoryTemp(4);


                }
                updateMap();
                break;
            case "shop":

                if(frameShop.getVisibility() == View.VISIBLE){
                    if(locations.size()>1) {
                        editor.putBoolean(Constants.SELECTED_SHOP, false);
                        editor.apply();
                        frameShop.setVisibility(View.INVISIBLE);
                        deleteCategoryTemp(5);
                    } else{
                        Toast.makeText(getApplication(), "Ação não permitida", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    editor.putBoolean(Constants.SELECTED_SHOP, true);
                    editor.apply();
                    frameShop.setVisibility(View.VISIBLE);
                    addCategoryTemp(5);

                }
                updateMap();
                break;
            default:
                Log.i("Main", "Modo não válido");
        }
    }
}
