package com.blockchains.tokens.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public final class CoinUser {
    private String userIdentity;
    private String userName;
    private Date userCreated;
    private long userFollowers;
    private long userFriends;
    private String userVerified;
    private String userLocation;
    private String userText;
    private double userCreditRating;
    private CreditData[] creditData;

    @JsonProperty
    public String getUserIdentity() {
        return userIdentity;
    }

    @JsonProperty
    public String getUserName() {
        return userName;
    }

    @JsonProperty
    public Date getUserCreated() {
        return userCreated;
    }

    @JsonProperty
    public long getUserFollowers() {
        return userFollowers;
    }

    @JsonProperty
    public long getUserFriends() {
        return userFriends;
    }

    @JsonProperty
    public String getUserVerified() {
        return userVerified;
    }

    @JsonProperty
    public String getUserLocation() {
        return userLocation;
    }

    @JsonProperty
    public String getUserText() {
        return userText;
    }

    @JsonProperty
    public double getUserCreditRating() {
        return userCreditRating;
    }

    @JsonProperty
    public CreditData[] getCreditData() {
        return creditData;
    }

    public CoinUser(String userIdentity
            , String userName
            , Date userCreated
            , long userFollowers
            , long userFriends
            , String userVerified
            , String userLocation
            , String userText
            , double userCreditRating
            , CreditData[] creditData
    ) {
        this.userIdentity = userIdentity;
        this.userName = userName;
        this.userCreated = userCreated;
        this.userFollowers = userFollowers;
        this.userFriends = userFriends;
        this.userVerified = userVerified;
        this.userLocation = userLocation;
        this.userText = userText;
        this.userCreditRating = userCreditRating;
        this.creditData = creditData;
    }
    public String toString(){
        return userIdentity + ","
                + userFollowers + ","
                + userFriends + ","
                + userFollowers + ","
                + userVerified + ","
                + userLocation + ","
                + userCreditRating;
    }
}
