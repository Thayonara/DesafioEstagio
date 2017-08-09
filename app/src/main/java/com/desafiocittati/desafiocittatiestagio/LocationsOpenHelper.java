package com.desafiocittati.desafiocittatiestagio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.desafiocittati.desafiocittatiestagio.Constants.BANK_TABLE;
import static com.desafiocittati.desafiocittatiestagio.Constants.GAS_STATION_TABLE;
import static com.desafiocittati.desafiocittatiestagio.Constants.LATITUDE_BANK_COLUMN;
import static com.desafiocittati.desafiocittatiestagio.Constants.LATITUDE_GAS_STATION_COLUMN;
import static com.desafiocittati.desafiocittatiestagio.Constants.LATITUDE_PUBLIC_ZONE_COLUMN;
import static com.desafiocittati.desafiocittatiestagio.Constants.LATITUDE_RESTAURANT_COLUMN;
import static com.desafiocittati.desafiocittatiestagio.Constants.LATITUDE_SHOP_COLUMN;
import static com.desafiocittati.desafiocittatiestagio.Constants.LONGITUDE_BANK_COLUMN;
import static com.desafiocittati.desafiocittatiestagio.Constants.LONGITUDE_GAS_STATION_COLUMN;
import static com.desafiocittati.desafiocittatiestagio.Constants.LONGITUDE_PUBLIC_ZONE_COLUMN;
import static com.desafiocittati.desafiocittatiestagio.Constants.LONGITUDE_RESTAURANT_COLUMN;
import static com.desafiocittati.desafiocittatiestagio.Constants.LONGITUDE_SHOP_COLUMN;
import static com.desafiocittati.desafiocittatiestagio.Constants.PUBLIC_ZONE_TABLE;
import static com.desafiocittati.desafiocittatiestagio.Constants.RESTAURANT_TABLE;
import static com.desafiocittati.desafiocittatiestagio.Constants.SHOP_TABLE;
import static com.desafiocittati.desafiocittatiestagio.Constants._ID_BANK;
import static com.desafiocittati.desafiocittatiestagio.Constants._ID_GAS_STATION;
import static com.desafiocittati.desafiocittatiestagio.Constants._ID_PUBLIC_ZONE;
import static com.desafiocittati.desafiocittatiestagio.Constants._ID_SHOP;

/**
 * Created by thayo on 09/08/2017.
 */

public class LocationsOpenHelper extends SQLiteOpenHelper{
    final private Context mContext;


    public LocationsOpenHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RESTAURANT_TABLE = "CREATE TABLE " + RESTAURANT_TABLE+"("+ Constants._ID_RESTAURANT + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                 + LATITUDE_RESTAURANT_COLUMN + " REAL,"
                + LONGITUDE_RESTAURANT_COLUMN + " REAL" +")";

        String CREATE_BANK_TABLE = "CREATE TABLE " + BANK_TABLE+"("+ _ID_BANK + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + LATITUDE_BANK_COLUMN + " REAL,"
                + LONGITUDE_BANK_COLUMN + " REAL" +")";

        String CREATE_GAS_STATION_TABLE = "CREATE TABLE " + GAS_STATION_TABLE+"(" + _ID_GAS_STATION + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + LATITUDE_GAS_STATION_COLUMN + " REAL,"
                + LONGITUDE_GAS_STATION_COLUMN + " REAL" +")";

        String CREATE_PUBLIC_ZONE_TABLE = "CREATE TABLE " + PUBLIC_ZONE_TABLE+"(" + _ID_PUBLIC_ZONE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + LATITUDE_PUBLIC_ZONE_COLUMN + " REAL,"
                + LONGITUDE_PUBLIC_ZONE_COLUMN + " REAL" +")";

        String CREATE_SHOP_TABLE = "CREATE TABLE " + SHOP_TABLE+"(" + _ID_SHOP + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + LATITUDE_SHOP_COLUMN + " REAL,"
                + LONGITUDE_SHOP_COLUMN + " REAL" +")";


        db.execSQL(CREATE_RESTAURANT_TABLE);
        db.execSQL(CREATE_BANK_TABLE);
        db.execSQL(CREATE_GAS_STATION_TABLE);
        db.execSQL(CREATE_PUBLIC_ZONE_TABLE);
        db.execSQL(CREATE_SHOP_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RESTAURANT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + BANK_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + GAS_STATION_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PUBLIC_ZONE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SHOP_TABLE);

        onCreate(db);

    }

    void deleteDatabase() {

        mContext.deleteDatabase(Constants.RESTAURANT_TABLE);
        mContext.deleteDatabase(Constants.BANK_TABLE);
        mContext.deleteDatabase(Constants.GAS_STATION_TABLE);
        mContext.deleteDatabase(Constants.PUBLIC_ZONE_TABLE);
        mContext.deleteDatabase(Constants.SHOP_TABLE);

    }

}
