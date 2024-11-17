package com.blockchains.stream.data.models;

import com.github.javafaker.Faker;

import java.io.Serializable;

public class CreditData implements Serializable {
    private String creditCardType;
    private String creditCardNumber;
    private String creditCardExpiry;
    private String creditCardActivity;

    public CreditData(String creditCardType, String creditCardNumber, String creditCardExpiry, String creditCardActivity) {
        this.creditCardType = creditCardType;
        this.creditCardNumber = creditCardNumber;
        this.creditCardExpiry = creditCardExpiry;
        this.creditCardActivity = creditCardActivity;
    }

    public CreditData(){
    }
    public String getCreditCardType() {
        return creditCardType;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getCreditCardExpiry() {
        return creditCardExpiry;
    }

    public String getCreditCardActivity() {
        return creditCardActivity;
    }
}
