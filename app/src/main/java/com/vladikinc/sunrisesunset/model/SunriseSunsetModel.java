package com.vladikinc.sunrisesunset.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class SunriseSunsetModel {

    @SerializedName("sunrise")
    private Date sunrise;

    @SerializedName("sunset")
    private Date sunset;

    @SerializedName("solar_noon")
    private Date solarNoon;

    @SerializedName("day_length")
    private long dayLength;

    @SerializedName("civil_twilight_begin")
    private Date civilTwilightBegin;

    @SerializedName("civil_twilight_end")
    private Date civilTwilightEnd;

    @SerializedName("nautical_twilight_begin")
    private Date nauticalTwilightBegin;

    @SerializedName("nautical_twilight_end")
    private Date nauticalTwilightEnd;

    @SerializedName("astronomical_twilight_begin")
    private Date astronomicalTwilightBegin;

    @SerializedName("astronomical_twilight_end")
    private Date astronomicalTwilightEnd;

    public SunriseSunsetModel(Date sunrise,
                              Date sunset,
                              Date solarNoon,
                              long dayLength,
                              Date civilTwilightBegin,
                              Date civilTwilightEnd,
                              Date nauticalTwilightBegin,
                              Date nauticalTwilightEnd,
                              Date astronomicalTwilightBegin,
                              Date astronomicalTwilightEnd) {
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.solarNoon = solarNoon;
        this.dayLength = dayLength;
        this.civilTwilightBegin = civilTwilightBegin;
        this.civilTwilightEnd = civilTwilightEnd;
        this.nauticalTwilightBegin = nauticalTwilightBegin;
        this.nauticalTwilightEnd = nauticalTwilightEnd;
        this.astronomicalTwilightBegin = astronomicalTwilightBegin;
        this.astronomicalTwilightEnd = astronomicalTwilightEnd;
    }

    public Date getSunrise() {
        return sunrise;
    }

    public void setSunrise(Date sunrise) {
        this.sunrise = sunrise;
    }

    public Date getSunset() {
        return sunset;
    }

    public void setSunset(Date sunset) {
        this.sunset = sunset;
    }

    public Date getSolarNoon() {
        return solarNoon;
    }

    public void setSolarNoon(Date solarNoon) {
        this.solarNoon = solarNoon;
    }

    public long getDayLength() {
        return dayLength;
    }

    public void setDayLength(long dayLength) {
        this.dayLength = dayLength;
    }

    public Date getCivilTwilightBegin() {
        return civilTwilightBegin;
    }

    public void setCivilTwilightBegin(Date civilTwilightBegin) {
        this.civilTwilightBegin = civilTwilightBegin;
    }

    public Date getCivilTwilightEnd() {
        return civilTwilightEnd;
    }

    public void setCivilTwilightEnd(Date civilTwilightEnd) {
        this.civilTwilightEnd = civilTwilightEnd;
    }

    public Date getNauticalTwilightBegin() {
        return nauticalTwilightBegin;
    }

    public void setNauticalTwilightBegin(Date nauticalTwilightBegin) {
        this.nauticalTwilightBegin = nauticalTwilightBegin;
    }

    public Date getNauticalTwilightEnd() {
        return nauticalTwilightEnd;
    }

    public void setNauticalTwilightEnd(Date nauticalTwilightEnd) {
        this.nauticalTwilightEnd = nauticalTwilightEnd;
    }

    public Date getAstronomicalTwilightBegin() {
        return astronomicalTwilightBegin;
    }

    public void setAstronomicalTwilightBegin(Date astronomicalTwilightBegin) {
        this.astronomicalTwilightBegin = astronomicalTwilightBegin;
    }

    public Date getAstronomicalTwilightEnd() {
        return astronomicalTwilightEnd;
    }

    public void setAstronomicalTwilightEnd(Date astronomicalTwilightEnd) {
        this.astronomicalTwilightEnd = astronomicalTwilightEnd;
    }

    @Override
    public String toString() {
        return "SunriseSunsetModel{" +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                ", solarNoon=" + solarNoon +
                ", dayLength=" + dayLength +
                ", civilTwilightBegin=" + civilTwilightBegin +
                ", civilTwilightEnd=" + civilTwilightEnd +
                ", nauticalTwilightBegin=" + nauticalTwilightBegin +
                ", nauticalTwilightEnd=" + nauticalTwilightEnd +
                ", astronomicalTwilightBegin=" + astronomicalTwilightBegin +
                ", astronomicalTwilightEnd=" + astronomicalTwilightEnd +
                '}';
    }
}