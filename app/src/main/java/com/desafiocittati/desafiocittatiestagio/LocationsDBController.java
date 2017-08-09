package com.desafiocittati.desafiocittatiestagio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thayo on 09/08/2017.
 */

public class LocationsDBController {
    private SQLiteDatabase db;
    private LocationsOpenHelper locationsDBOpenHelper;

    public LocationsDBController(Context context){
        locationsDBOpenHelper = new LocationsOpenHelper(context);
    }

    public void clearAllLocations() {

        locationsDBOpenHelper.getWritableDatabase().delete(Constants.RESTAURANT_TABLE, null, null);
        locationsDBOpenHelper.getWritableDatabase().delete(Constants.BANK_TABLE, null, null);
        locationsDBOpenHelper.getWritableDatabase().delete(Constants.GAS_STATION_TABLE, null, null);
        locationsDBOpenHelper.getWritableDatabase().delete(Constants.PUBLIC_ZONE_TABLE, null, null);
        locationsDBOpenHelper.getWritableDatabase().delete(Constants.SHOP_TABLE, null, null);

    }


    public String insertLocationsRestaurant(LatLng latLng){
        long check =0;
        SQLiteDatabase db = locationsDBOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.LATITUDE_RESTAURANT_COLUMN, latLng.latitude);
        values.put(Constants.LONGITUDE_RESTAURANT_COLUMN, latLng.longitude);


        check = db.insert(Constants.RESTAURANT_TABLE, null, values);

        db.close();

        if(check == -1){
            return "Error";
        } else{
            return "DB created";
        }
    }

    public String insertLocationsBank(LatLng latLng){
        long check =0;
        SQLiteDatabase db = locationsDBOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.LATITUDE_BANK_COLUMN, latLng.latitude);
        values.put(Constants.LONGITUDE_BANK_COLUMN, latLng.longitude);


        check = db.insert(Constants.BANK_TABLE, null, values);

        db.close();

        if(check == -1){
            return "Error";
        } else{
            return "DB created";
        }
    }

    public String insertLocationsGasStations(LatLng latLng){
        long check =0;
        SQLiteDatabase db = locationsDBOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.LATITUDE_GAS_STATION_COLUMN, latLng.latitude);
        values.put(Constants.LONGITUDE_GAS_STATION_COLUMN, latLng.longitude);


        check = db.insert(Constants.GAS_STATION_TABLE, null, values);

        db.close();

        if(check == -1){
            return "Error";
        } else{
            return "DB created";
        }
    }

    public String insertLocationsPublicZone(LatLng latLng){
        long check =0;
        SQLiteDatabase db = locationsDBOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.LATITUDE_PUBLIC_ZONE_COLUMN, latLng.latitude);
        values.put(Constants.LONGITUDE_PUBLIC_ZONE_COLUMN, latLng.longitude);


        check = db.insert(Constants.PUBLIC_ZONE_TABLE, null, values);

        db.close();

        if(check == -1){
            return "Error";
        } else{
            return "DB created";
        }
    }


    public String insertLocationsshop(LatLng latLng){
        long check =0;
        SQLiteDatabase db = locationsDBOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.LATITUDE_SHOP_COLUMN, latLng.latitude);
        values.put(Constants.LONGITUDE_SHOP_COLUMN, latLng.longitude);


        check = db.insert(Constants.SHOP_TABLE, null, values);

        db.close();

        if(check == -1){
            return "Error";
        } else{
            return "DB created";
        }
    }


    public Cursor loadCoordinatesRestaurant(Context context){
        SQLiteDatabase db;
        LocationsOpenHelper locationsOpenHelper;
        locationsOpenHelper = new LocationsOpenHelper(context);

        Cursor cursor;
        db = locationsOpenHelper.getReadableDatabase();

        cursor = db.query(Constants.RESTAURANT_TABLE, Constants.RESTAURANT_COLUMNS, null, null, null, null, null, null);

        if(cursor!=null && cursor.getCount() > 0){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor loadCoordinatesBank(Context context){
        SQLiteDatabase db;
        LocationsOpenHelper locationsOpenHelper;
        locationsOpenHelper = new LocationsOpenHelper(context);

        Cursor cursor;
        db = locationsOpenHelper.getReadableDatabase();

        cursor = db.query(Constants.BANK_TABLE, Constants.BANK_COLUMNS, null, null, null, null, null, null);

        if(cursor!=null && cursor.getCount() > 0){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor loadCoordinatesGasStation(Context context){
        SQLiteDatabase db;
        LocationsOpenHelper locationsOpenHelper;
        locationsOpenHelper = new LocationsOpenHelper(context);

        Cursor cursor;
        db = locationsOpenHelper.getReadableDatabase();

        cursor = db.query(Constants.GAS_STATION_TABLE, Constants.GAS_STATION_COLUMNS, null, null, null, null, null, null);

        if(cursor!=null && cursor.getCount() > 0){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor loadCoordinatesPublicZone(Context context){
        SQLiteDatabase db;
        LocationsOpenHelper locationsOpenHelper;
        locationsOpenHelper = new LocationsOpenHelper(context);

        Cursor cursor;
        db = locationsOpenHelper.getReadableDatabase();

        cursor = db.query(Constants.PUBLIC_ZONE_TABLE, Constants.PUBLIC_ZONE_COLUMNS, null, null, null, null, null, null);

        if(cursor!=null && cursor.getCount() > 0){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    public Cursor loadCoordinatesShop(Context context){
        SQLiteDatabase db;
        LocationsOpenHelper locationsOpenHelper;
        locationsOpenHelper = new LocationsOpenHelper(context);

        Cursor cursor;
        db = locationsOpenHelper.getReadableDatabase();

        cursor = db.query(Constants.SHOP_TABLE, Constants.SHOP_COLUMNS, null, null, null, null, null, null);

        if(cursor!=null && cursor.getCount() > 0){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    public List<LatLng> getAllLocationsRestaurant(Context context) {
        List<LatLng> locations = new ArrayList<LatLng>();
        Cursor cursor = loadCoordinatesRestaurant(context);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                LatLng location = new LatLng(cursor.getDouble(0),cursor.getDouble(1));

                // Adding contact to list
                locations.add(location);
            } while (cursor.moveToNext());
        }
        cursor.close();

        // return contact list
        return locations;
    }

    public List<LatLng> getAllLocationsBank(Context context) {
        List<LatLng> locations = new ArrayList<LatLng>();
        Cursor cursor = loadCoordinatesBank(context);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                LatLng location = new LatLng(cursor.getDouble(0),cursor.getDouble(1));

                // Adding contact to list
                locations.add(location);
            } while (cursor.moveToNext());
        }
        cursor.close();

        // return contact list
        return locations;
    }


    public List<LatLng> getAllLocationsGasStation(Context context) {
        List<LatLng> locations = new ArrayList<LatLng>();
        Cursor cursor = loadCoordinatesGasStation(context);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                LatLng location = new LatLng(cursor.getDouble(0),cursor.getDouble(1));

                // Adding contact to list
                locations.add(location);
            } while (cursor.moveToNext());
        }
        cursor.close();

        // return contact list
        return locations;
    }


    public List<LatLng> getAllLocationsPublicZone(Context context) {
        List<LatLng> locations = new ArrayList<LatLng>();
        Cursor cursor = loadCoordinatesPublicZone(context);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                LatLng location = new LatLng(cursor.getDouble(0),cursor.getDouble(1));

                // Adding contact to list
                locations.add(location);
            } while (cursor.moveToNext());
        }
        cursor.close();

        // return contact list
        return locations;
    }

    public List<LatLng> getAllLocationsShop(Context context) {
        List<LatLng> locations = new ArrayList<LatLng>();
        Cursor cursor = loadCoordinatesShop(context);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                LatLng location = new LatLng(cursor.getDouble(0),cursor.getDouble(1));

                // Adding contact to list
                locations.add(location);
            } while (cursor.moveToNext());
        }
        cursor.close();

        // return contact list
        return locations;
    }


    public boolean isRestaurantExists(LatLng latLng ) {
        SQLiteDatabase db = locationsDBOpenHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Constants.RESTAURANT_TABLE
                        + " WHERE latitude_restaurant_column = '" + latLng.latitude + "'"  + " AND longitude_restaurant_column = '" + latLng.longitude + "'",
                new String[] {});
        boolean exists = (cursor.getCount() > 0);
        db.close();
        return exists;
    }


    public boolean isBankExists(LatLng latLng ) {
        SQLiteDatabase db = locationsDBOpenHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Constants.BANK_TABLE
                        + " WHERE latitude_bank_column = '" + latLng.latitude + "'"  + " AND longitude_bank_column = '" + latLng.longitude + "'",
                new String[] {});
        boolean exists = (cursor.getCount() > 0);
        db.close();
        return exists;
    }

    public boolean isGasStationExists(LatLng latLng ) {
        SQLiteDatabase db = locationsDBOpenHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Constants.GAS_STATION_TABLE
                        + " WHERE latitude_gas_station_column = '" + latLng.latitude + "'"  + " AND longitude_gas_station_column = '" + latLng.longitude + "'",
                new String[] {});
        boolean exists = (cursor.getCount() > 0);
        db.close();
        return exists;
    }

    public boolean isPublicZoneExists(LatLng latLng ) {
        SQLiteDatabase db = locationsDBOpenHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Constants.PUBLIC_ZONE_TABLE
                        + " WHERE latitude_public_zone_column = '" + latLng.latitude + "'"  + " AND longitude_public_zone_column = '" + latLng.longitude + "'",
                new String[] {});
        boolean exists = (cursor.getCount() > 0);
        db.close();
        return exists;
    }

    public boolean isShopExists(LatLng latLng ) {
        SQLiteDatabase db = locationsDBOpenHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Constants.SHOP_TABLE
                        + " WHERE latitude_shop_column = '" + latLng.latitude + "'"  + " AND longitude_shop_column = '" + latLng.longitude + "'",
                new String[] {});
        boolean exists = (cursor.getCount() > 0);
        db.close();
        return exists;
    }
}
