package com.vladikinc.sunrisesunset.gps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;

import com.google.android.gms.location.LocationRequest;

import io.reactivex.Single;
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider;

public class GPS {

    private LocationRequest mRequest;
    private ReactiveLocationProvider mLocationProvider;

    public GPS(Context context) {
        mRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setNumUpdates(1)
                .setInterval(30000)
                .setFastestInterval(5000);

        mLocationProvider = new ReactiveLocationProvider(context);
    }

    @SuppressLint("MissingPermission")
    public Single<Location> updateLocation() {
        return mLocationProvider.getUpdatedLocation(mRequest)
                .firstOrError();
    }
}