package com.blockchains.tokens.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CryptoCurrency {
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
    @JsonProperty
    public String getCurrencyId() {
        return currencyId;
    }

    @JsonProperty
    public String getCode() {
        return code;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public double getOpeningValue() {
        return openingValue;
    }

    @JsonProperty
    public double getClosingValue() {
        return closingValue;
    }

    @JsonProperty
    public double getHighestValue() {
        return highestValue;
    }

    @JsonProperty
    public double getLowestValue() {
        return lowestValue;
    }

    @JsonProperty
    public double getAdjClose() {
        return adjClose;
    }

    @JsonProperty
    public long getVolumeStocks() {
        return volumeStocks;
    }

    @JsonProperty
    public String getMd5() {
        return md5;
    }

    @JsonProperty
    public String getSha512() {
        return sha512;
    }

    @JsonProperty
    public CoinUser getCurrentUser() {
        return currentUser;
    }
}
