package com.blockchains.stream.data.models;

import java.io.Serializable;
import java.util.Date;

public final class CoinUser implements Serializable {
    private static final long serialVersionUID = 8145257839787754634L;
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

    public CoinUser(){}
    public String getUserIdentity() {
        return userIdentity;
    }

    public String getUserName() {
        return userName;
    }

    public Date getUserCreated() {
        return userCreated;
    }

    public long getUserFollowers() {
        return userFollowers;
    }

    public long getUserFriends() {
        return userFriends;
    }

    public String getUserVerified() {
        return userVerified;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public String getUserText() {
        return userText;
    }

    public double getUserCreditRating() {
        return userCreditRating;
    }

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
