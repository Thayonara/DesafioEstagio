package com.desafiocittati.desafiocittatiestagio;

import android.app.ActionBar;
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
    private ImageView im1, im2, im3,im4, im5;
    private FrameLayout f1, f2, f3, f4, f5;
    private  Map<Integer,List<LatLng>> locations = new HashMap<Integer, List<LatLng>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        im1 = (ImageView) findViewById(R.id.iv_restaurant);
        im2 = (ImageView) findViewById(R.id.iv_bank);
        im3 = (ImageView) findViewById(R.id.iv_gas);
        im4 = (ImageView) findViewById(R.id.iv_public);
        im5 = (ImageView) findViewById(R.id.iv_shop);

        f1 = (FrameLayout) findViewById(R.id.triangle1);
        f2 = (FrameLayout) findViewById(R.id.triangle2);
        f3 = (FrameLayout) findViewById(R.id.triangle3);
        f4 = (FrameLayout) findViewById(R.id.triangle4);
        f5 = (FrameLayout) findViewById(R.id.triangle5);

        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisibilityMode("restaurant");
            }
        });

        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisibilityMode("bank");
            }
        });

        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisibilityMode("gas_stations");
            }
        });

        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisibilityMode("public_zone");
            }
        });

        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisibilityMode("shop");
            }
        });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.getUiSettings().setZoomControlsEnabled(true);

        locations = new DataParser().parse(getApplication());

        for(int i = 1; i <= locations.size(); i++){

            for(int j = 0; j < locations.get(i).size(); j++){
                addMarker(locations.get(i).get(j),i);
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
        List<LatLng> categoryDatas = new DataParser().parse(getApplication()).get(category);
        locations.put(category, categoryDatas);
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

                if(f1.getVisibility() == View.VISIBLE){

                    if(locations.size()>1) {
                        f1.setVisibility(View.INVISIBLE);
                        deleteCategoryTemp(1);
                    } else{
                        Toast.makeText(getApplication(), "Ação não permitida", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    f1.setVisibility(View.VISIBLE);
                    addCategoryTemp(1);

                }
                updateMap();
                break;
            case "bank":
                if(f2.getVisibility() == View.VISIBLE){
                    if(locations.size()>1) {
                        f2.setVisibility(View.INVISIBLE);
                        deleteCategoryTemp(2);
                    } else{
                        Toast.makeText(getApplication(), "Ação não permitida", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    f2.setVisibility(View.VISIBLE);
                    addCategoryTemp(2);

                }
                updateMap();
                break;
            case "gas_stations":

                if(f3.getVisibility() == View.VISIBLE){
                    if(locations.size()>1) {
                        f3.setVisibility(View.INVISIBLE);
                        deleteCategoryTemp(3);
                    } else{
                        Toast.makeText(getApplication(), "Ação não permitida", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    f3.setVisibility(View.VISIBLE);
                    addCategoryTemp(3);

                }
                updateMap();
                break;
            case "public_zone":

                if(f4.getVisibility() == View.VISIBLE){
                    if(locations.size()>1) {
                        f4.setVisibility(View.INVISIBLE);
                        deleteCategoryTemp(4);
                    } else{
                        Toast.makeText(getApplication(), "Ação não permitida", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    f4.setVisibility(View.VISIBLE);
                    addCategoryTemp(4);


                }
                updateMap();
                break;
            case "shop":

                if(f5.getVisibility() == View.VISIBLE){
                    if(locations.size()>1) {
                        f5.setVisibility(View.INVISIBLE);
                        deleteCategoryTemp(5);
                    } else{
                        Toast.makeText(getApplication(), "Ação não permitida", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    f5.setVisibility(View.VISIBLE);
                    addCategoryTemp(5);

                }
                updateMap();
                break;
            default:
                Log.i("Main", "Modo não válido");
        }
    }
}
