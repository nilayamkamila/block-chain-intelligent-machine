package com.blockchains.stream.data.models;

import com.blockchains.stream.data.models.CoinUser;

import java.io.Serializable;

public class CryptoCurrency implements Serializable {
    private String currencyId;
    private String code;
    private String name;
    private double openingValue;
    private double closingValue;
    private double highestValue;
    private double lowestValue;
    private double adjClose;
    private long volumeStocks;
    private String md5;
    private String sha512;
    private CoinUser currentUser;
    public CryptoCurrency(){}
    public CryptoCurrency(String currencyId
            , String code
            , String name
            , double openingValue
            , double closingValue
            , double highestValue
            , double lowestValue
            , double adjClose
            , long volumeStocks
            , String md5
            , String sha512
            , CoinUser currentUser) {
        this.currencyId = currencyId;
        this.code = code;
        this.name = name;
        this.openingValue = openingValue;
        this.closingValue = closingValue;
        this.highestValue = highestValue;
        this.lowestValue = lowestValue;
        this.adjClose = adjClose;
        this.volumeStocks = volumeStocks;
        this.md5 = md5;
        this.sha512 = sha512;
        this.currentUser = currentUser;
    }

    public String toString(){
        return currencyId + ","
                + openingValue + ","
                + closingValue + ","
                + highestValue + ","
                + lowestValue + ","
                + adjClose + ","
                + volumeStocks + ","
                + currentUser;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getOpeningValue() {
        return openingValue;
    }

    public double getClosingValue() {
        return closingValue;
    }

    public double getHighestValue() {
        return highestValue;
    }

    public double getLowestValue() {
        return lowestValue;
    }

    public double getAdjClose() {
        return adjClose;
    }

    public long getVolumeStocks() {
        return volumeStocks;
    }

    public String getMd5() {
        return md5;
    }

    public String getSha512() {
        return sha512;
    }

    public CoinUser getCurrentUser() {
        return currentUser;
    }
}
