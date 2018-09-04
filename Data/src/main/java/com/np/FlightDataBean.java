package com.np;

import com.univocity.parsers.annotations.NullString;
import com.univocity.parsers.annotations.Parsed;

import java.io.Serializable;

// SELECT arrDelay, depDelay, airTime

public class FlightDataBean implements Serializable {
    @NullString(nulls = { "NA" })
    @Parsed(index = 0, defaultNullRead = "0")
    public Integer year;

    @NullString(nulls = { "NA" })
    @Parsed(index = 1, defaultNullRead = "0")
    public Integer month;

    @NullString(nulls = { "NA" })
    @Parsed(index = 2, defaultNullRead = "0")
    public Integer dayOfMonth;

    @NullString(nulls = { "NA" })
    @Parsed(index = 3, defaultNullRead = "0")
    public Integer dayOfWeek;

    @NullString(nulls = { "NA" })
    @Parsed(index = 4, defaultNullRead = "0")
    public Integer depTime;

    @NullString(nulls = { "NA" })
    @Parsed(index = 5, defaultNullRead = "0")
    public Integer csrDepTime;

    @NullString(nulls = { "NA" })
    @Parsed(index = 6, defaultNullRead = "0")
    public Integer arrTime;

    @NullString(nulls = { "NA" })
    @Parsed(index = 7, defaultNullRead = "0")
    public Integer csrArrTime;

    @Parsed(index = 8)
    public String uniqueCarrier;

    @NullString(nulls = { "NA" })
    @Parsed(index = 9, defaultNullRead = "0")
    public Integer flightNum;

    @Parsed(index = 10)
    public String tailNum;

    @NullString(nulls = { "NA" })
    @Parsed(index = 11, defaultNullRead = "0")
    public Integer actualElapsedTime;

    @NullString(nulls = { "NA" })
    @Parsed(index = 12, defaultNullRead = "0")
    public Integer crsElapsedTime;

    @NullString(nulls = { "NA" })
    @Parsed(index = 13, defaultNullRead = "0")
    public Integer airTime;

    @NullString(nulls = { "NA" })
    @Parsed(index = 14, defaultNullRead = "0")
    public Integer arrDelay;

    @NullString(nulls = { "NA" })
    @Parsed(index = 15, defaultNullRead = "0")
    public Integer delayed;

    @NullString(nulls = { "NA" })
    @Parsed(index = 16, defaultNullRead = "0")
    public Integer depDelay;

    @NullString(nulls = { "NA" })
    @Parsed(index = 17)
    public String origin;

    @Parsed(index = 18)
    public String dest;

    @NullString(nulls = { "NA" })
    @Parsed(index = 19, defaultNullRead = "0")
    public Integer distance;

    @NullString(nulls = { "NA" })
    @Parsed(index = 20, defaultNullRead = "0")
    public Integer taxiIn;

    @NullString(nulls = { "NA" })
    @Parsed(index = 21, defaultNullRead = "0")
    public Integer taxiOut;

    @NullString(nulls = { "NA" })
    @Parsed(index = 22, defaultNullRead = "0")
    public Integer cancelled;

    @Parsed(index = 23, defaultNullRead = "")
    public String cancellationCode;

    @NullString(nulls = { "NA" })
    @Parsed(index = 24, defaultNullRead = "0")
    public Integer diverted;

    @NullString(nulls = { "NA" })
    @Parsed(index = 25, defaultNullRead = "0")
    public Integer carrierDelay;

    @NullString(nulls = { "NA" })
    @Parsed(index = 26, defaultNullRead = "0")
    public Integer weatherDelay;

    @NullString(nulls = { "NA" })
    @Parsed(index = 27, defaultNullRead = "0")
    public Integer nasDelay;

    @NullString(nulls = { "NA" })
    @Parsed(index = 28, defaultNullRead = "0")
    public Integer securityDelay;

    @NullString(nulls = { "NA" })
    @Parsed(index = 29, defaultNullRead = "0")
    public Integer lateAircraftDelay;

    public FlightDataBean() {
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getCancellationCode() {
        return cancellationCode;
    }

    public void setCancellationCode(String cancellationCode) {
        this.cancellationCode = cancellationCode;
    }

    public Integer getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getDepTime() {
        return depTime;
    }

    public void setDepTime(Integer depTime) {
        this.depTime = depTime;
    }

    public Integer getCsrDepTime() {
        return csrDepTime;
    }

    public void setCsrDepTime(Integer csrDepTime) {
        this.csrDepTime = csrDepTime;
    }

    public Integer getArrTime() {
        return arrTime;
    }

    public void setArrTime(Integer arrTime) {
        this.arrTime = arrTime;
    }

    public Integer getCsrArrTime() {
        return csrArrTime;
    }

    public void setCsrArrTime(Integer csrArrTime) {
        this.csrArrTime = csrArrTime;
    }

    public String getUniqueCarrier() {
        return uniqueCarrier;
    }

    public void setUniqueCarrier(String uniqueCarrier) {
        this.uniqueCarrier = uniqueCarrier;
    }

    public Integer getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(Integer flightNum) {
        this.flightNum = flightNum;
    }

    public String getTailNum() {
        return tailNum;
    }

    public void setTailNum(String tailNum) {
        this.tailNum = tailNum;
    }

    public Integer getActualElapsedTime() {
        return actualElapsedTime;
    }

    public void setActualElapsedTime(Integer actualElapsedTime) {
        this.actualElapsedTime = actualElapsedTime;
    }

    public Integer getCrsElapsedTime() {
        return crsElapsedTime;
    }

    public void setCrsElapsedTime(Integer crsElapsedTime) {
        this.crsElapsedTime = crsElapsedTime;
    }

    public Integer getAirTime() {
        return airTime;
    }

    public void setAirTime(Integer airTime) {
        this.airTime = airTime;
    }

    public Integer getArrDelay() {
        return arrDelay;
    }

    public void setArrDelay(Integer arrDelay) {
        this.arrDelay = arrDelay;
    }

    public Integer getDelayed() {
        return delayed;
    }

    public void setDelayed(Integer delayed) {
        this.delayed = delayed;
    }

    public Integer getDepDelay() {
        return depDelay;
    }

    public void setDepDelay(Integer depDelay) {
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

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getTaxiIn() {
        return taxiIn;
    }

    public void setTaxiIn(Integer taxiIn) {
        this.taxiIn = taxiIn;
    }

    public Integer getTaxiOut() {
        return taxiOut;
    }

    public void setTaxiOut(Integer taxiOut) {
        this.taxiOut = taxiOut;
    }

    public Integer getCancelled() {
        return cancelled;
    }

    public void setCancelled(Integer cancelled) {
        this.cancelled = cancelled;
    }

    public Integer getDiverted() {
        return diverted;
    }

    public void setDiverted(Integer diverted) {
        this.diverted = diverted;
    }

    public Integer getCarrierDelay() {
        return carrierDelay;
    }

    public void setCarrierDelay(Integer carrierDelay) {
        this.carrierDelay = carrierDelay;
    }

    public Integer getWeatherDelay() {
        return weatherDelay;
    }

    public void setWeatherDelay(Integer weatherDelay) {
        this.weatherDelay = weatherDelay;
    }

    public Integer getNasDelay() {
        return nasDelay;
    }

    public void setNasDelay(Integer nasDelay) {
        this.nasDelay = nasDelay;
    }

    public Integer getSecurityDelay() {
        return securityDelay;
    }

    public void setSecurityDelay(Integer securityDelay) {
        this.securityDelay = securityDelay;
    }

    public Integer getLateAircraftDelay() {
        return lateAircraftDelay;
    }

    public void setLateAircraftDelay(Integer lateAircraftDelay) {
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
