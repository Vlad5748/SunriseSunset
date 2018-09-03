package com.vladikinc.sunrisesunset.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.vladikinc.sunrisesunset.R;
import com.vladikinc.sunrisesunset.api.RetrofitHelper;
import com.vladikinc.sunrisesunset.gps.GPS;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final int RC_LOCATION = 1;
    private static final int RC_SEARCH = 2;

    @BindView(R.id.sunrise_text_view)
    TextView mSunriseTextView;

    @BindView(R.id.sunset_text_view)
    TextView mSunsetTextView;

    @BindView(R.id.solar_noon_text_view)
    TextView mSolarNoonTextView;

    @BindView(R.id.day_length_text_view)
    TextView mDayLengthTextView;

    @BindView(R.id.update_button)
    Button mUpdateButton;

    @BindView(R.id.search_layout)
    View mSearchView;

    @BindView(R.id.search_location_text_view)
    TextView mSearchLocationTextView;

    @BindView(R.id.search_sunrise_text_view)
    TextView mSearchSunriseTextView;

    @BindView(R.id.search_sunset_text_view)
    TextView mSearchSunsetTextView;

    @BindView(R.id.search_solar_noon_text_view)
    TextView mSearchSolarNoonTextView;

    @BindView(R.id.search_day_length_text_view)
    TextView mSearchDayLengthTextView;

    private GPS mGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mGPS = new GPS(this);
        requestLocationPermissions();
    }

    @OnClick(R.id.update_button)
    public void updateLocation() {
        mGPS.updateLocation()
                .flatMap(location -> RetrofitHelper.getService()
                        .getResponse((float) location.getLatitude(), (float) location.getLongitude(), 0))
                .map(results -> results.getResults() != null ? results.getResults() : fail())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> {
                            SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");
                            mSunriseTextView.setText(sdfDate.format(list.getSunrise()));
                            mSunsetTextView.setText(sdfDate.format(list.getSunset()));
                            mSolarNoonTextView.setText(sdfDate.format(list.getSolarNoon()));
                            Date day_length_date = new Date(list.getDayLength() * 1000);
                            mDayLengthTextView.setText(sdfDate.format(day_length_date));
                        },
                        Throwable::printStackTrace
                );
    }

    private <R> R fail() {
        throw new RuntimeException();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) &&
                (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED)) {
            updateLocation();
        }
    }

    public void requestLocationPermissions() {
        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) ||
                (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, RC_LOCATION);
        } else {
            updateLocation();
        }
    }

    @OnClick(R.id.search_button)
    public void search() {
        try {
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                    .setFilter(new AutocompleteFilter.Builder()
                            .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                            .build())
                    .build(this);

            startActivityForResult(intent, RC_SEARCH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SEARCH && resultCode == RESULT_OK) {
            Place place = PlaceAutocomplete.getPlace(this, data);

            Single.just(place)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSuccess(p -> mSearchLocationTextView.setText("Search location: " + p.getName()))
                    .observeOn(Schedulers.computation())
                    .map(Place::getLatLng)
                    .flatMap(location -> RetrofitHelper.getService()
                            .getResponse((float) location.latitude, (float) location.longitude, 0))
                    .map(results -> results.getResults() != null ? results.getResults() : fail())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            result -> {
                                SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");
                                mSearchSunriseTextView.setText(sdfDate.format(result.getSunrise()));
                                mSearchSunsetTextView.setText(sdfDate.format(result.getSunset()));
                                mSearchSolarNoonTextView.setText(sdfDate.format(result.getSolarNoon()));
                                Date day_length_date = new Date(result.getDayLength() * 1000);
                                mSearchDayLengthTextView.setText(sdfDate.format(day_length_date));
                                mSearchLocationTextView.setVisibility(View.VISIBLE);
                                mSearchView.setVisibility(View.VISIBLE);
                            },
                            Throwable::printStackTrace
                    );
        }
    }
}

