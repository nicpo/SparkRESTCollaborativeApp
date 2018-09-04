package com.np;

import com.univocity.parsers.annotations.NullString;
import com.univocity.parsers.annotations.Parsed;

import java.io.Serializable;

public class FlightDataStringBean implements Serializable {
    @Parsed(index = 0)
    public String year;

    @Parsed(index = 1)
    public String month;

    @Parsed(index = 2)
    public String dayOfMonth;

    @Parsed(index = 3)
    public String dayOfWeek;

    @Parsed(index = 4)
    public String depTime;

    @Parsed(index = 5)
    public String csrDepTime;

    @Parsed(index = 6)
    public String arrTime;

    @Parsed(index = 7)
    public String csrArrTime;

    @Parsed(index = 8)
    public String uniqueCarrier;

    @Parsed(index = 9)
    public String flightNum;

    @Parsed(index = 10)
    public String tailNum;

    @Parsed(index = 11)
    public String actualElapsedTime;

    @Parsed(index = 12)
    public String crsElapsedTime;

    @Parsed(index = 13)
    public String airTime;

    @Parsed(index = 14)
    public String arrDelay;

    @Parsed(index = 15)
    public String delayed;

    @Parsed(index = 16)
    public String depDelay;

    @Parsed(index = 17)
    public String origin;

    @Parsed(index = 18)
    public String dest;

    @Parsed(index = 19)
    public String distance;

    @Parsed(index = 20)
    public String taxiIn;

    @Parsed(index = 21)
    public String taxiOut;

    @Parsed(index = 22)
    public String cancelled;

    @Parsed(index = 23)
    public String cancellationCode;

    @Parsed(index = 24)
    public String diverted;

    @Parsed(index = 25)
    public String carrierDelay;

    @Parsed(index = 26)
    public String weatherDelay;

    @Parsed(index = 27)
    public String nasDelay;

    @Parsed(index = 28)
    public String securityDelay;

    @Parsed(index = 29)
    public String lateAircraftDelay;

    public FlightDataStringBean() {
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(String dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getCsrDepTime() {
        return csrDepTime;
    }

    public void setCsrDepTime(String csrDepTime) {
        this.csrDepTime = csrDepTime;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public String getCsrArrTime() {
        return csrArrTime;
    }

    public void setCsrArrTime(String csrArrTime) {
        this.csrArrTime = csrArrTime;
    }

    public String getUniqueCarrier() {
        return uniqueCarrier;
    }

    public void setUniqueCarrier(String uniqueCarrier) {
        this.uniqueCarrier = uniqueCarrier;
    }

    public String getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }

    public String getTailNum() {
        return tailNum;
    }

    public void setTailNum(String tailNum) {
        this.tailNum = tailNum;
    }

    public String getActualElapsedTime() {
        return actualElapsedTime;
    }

    public void setActualElapsedTime(String actualElapsedTime) {
        this.actualElapsedTime = actualElapsedTime;
    }

    public String getCrsElapsedTime() {
        return crsElapsedTime;
    }

    public void setCrsElapsedTime(String crsElapsedTime) {
        this.crsElapsedTime = crsElapsedTime;
    }

    public String getAirTime() {
        return airTime;
    }

    public void setAirTime(String airTime) {
        this.airTime = airTime;
    }

    public String getArrDelay() {
        return arrDelay;
    }

    public void setArrDelay(String arrDelay) {
        this.arrDelay = arrDelay;
    }

    public String getDelayed() {
        return delayed;
    }

    public void setDelayed(String delayed) {
        this.delayed = delayed;
    }

    public String getDepDelay() {
        return depDelay;
    }

    public void setDepDelay(String depDelay) {
        this.depDelay = depDelay;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTaxiIn() {
        return taxiIn;
    }

    public void setTaxiIn(String taxiIn) {
        this.taxiIn = taxiIn;
    }

    public String getTaxiOut() {
        return taxiOut;
    }

    public void setTaxiOut(String taxiOut) {
        this.taxiOut = taxiOut;
    }

    public String getCancelled() {
        return cancelled;
    }

    public void setCancelled(String cancelled) {
        this.cancelled = cancelled;
    }

    public String getCancellationCode() {
        return cancellationCode;
    }

    public void setCancellationCode(String cancellationCode) {
        this.cancellationCode = cancellationCode;
    }

    public String getDiverted() {
        return diverted;
    }

    public void setDiverted(String diverted) {
        this.diverted = diverted;
    }

    public String getCarrierDelay() {
        return carrierDelay;
    }

    public void setCarrierDelay(String carrierDelay) {
        this.carrierDelay = carrierDelay;
    }

    public String getWeatherDelay() {
        return weatherDelay;
    }

    public void setWeatherDelay(String weatherDelay) {
        this.weatherDelay = weatherDelay;
    }

    public String getNasDelay() {
        return nasDelay;
    }

    public void setNasDelay(String nasDelay) {
        this.nasDelay = nasDelay;
    }

    public String getSecurityDelay() {
        return securityDelay;
    }

    public void setSecurityDelay(String securityDelay) {
        this.securityDelay = securityDelay;
    }

    public String getLateAircraftDelay() {
        return lateAircraftDelay;
    }

    public void setLateAircraftDelay(String lateAircraftDelay) {
        this.lateAircraftDelay = lateAircraftDelay;
    }

    @Override
    public String toString() {
        return "FlightDataBean{" +
                "year=" + year +
                ", dayOfMonth=" + dayOfMonth +
                ", dayOfWeek=" + dayOfWeek +
                ", depTime=" + depTime +
                ", csrDepTime=" + csrDepTime +
                ", arrTime=" + arrTime +
                ", csrArrTime=" + csrArrTime +
                ", uniqueCarrier='" + uniqueCarrier + '\'' +
                ", flightNum=" + flightNum +
                ", tailNum='" + tailNum + '\'' +
                ", actualElapsedTime=" + actualElapsedTime +
                ", crsElapsedTime=" + crsElapsedTime +
                ", airTime=" + airTime +
                ", arrDelay=" + arrDelay +
                ", delayed=" + delayed +
                ", depDelay=" + depDelay +
                ", origin='" + origin + '\'' +
                ", dest='" + dest + '\'' +
                ", distance=" + distance +
                ", taxiIn=" + taxiIn +
                ", taxiOut=" + taxiOut +
                ", cancelled=" + cancelled +
                ", cancellationCode=" + cancellationCode +
                ", diverted=" + diverted +
                ", carrierDelay=" + carrierDelay +
                ", weatherDelay=" + weatherDelay +
                ", nasDelay=" + nasDelay +
                ", securityDelay=" + securityDelay +
                ", lateAircraftDelay=" + lateAircraftDelay +
                '}';
    }
}
