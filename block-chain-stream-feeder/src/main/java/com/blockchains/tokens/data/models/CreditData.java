package com.blockchains.tokens.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;

public class CreditData {
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

    @JsonProperty
    public String getCreditCardType() {
        return creditCardType;
    }

    @JsonProperty
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    @JsonProperty
    public String getCreditCardExpiry() {
        return creditCardExpiry;
    }

    @JsonProperty
    public String getCreditCardActivity() {
        return creditCardActivity;
    }
}
