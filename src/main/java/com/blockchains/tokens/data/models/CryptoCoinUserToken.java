package com.blockchains.tokens.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CryptoCoinUserToken {
    CryptoCurrency cryptoCurrency;
    double assignedId;
    String certifiedAuthority;
    boolean certifiedAuthorityTrusted;
    boolean tokenTrusted;
    String signature;

    public CryptoCoinUserToken(
            CryptoCurrency cryptoCurrency
            , double assignedId
            , String certifiedAuthority
            , boolean certifiedAuthorityTrusted
            , boolean tokenTrusted
            , String signature) {
        this.cryptoCurrency = cryptoCurrency;
        this.assignedId = assignedId;
        this.certifiedAuthority = certifiedAuthority;
        this.certifiedAuthorityTrusted = certifiedAuthorityTrusted;
        this.tokenTrusted = tokenTrusted;
        this.signature = signature;
    }

    public String toString(){
        return cryptoCurrency + ","
                + certifiedAuthorityTrusted + ","
                + tokenTrusted;
    }




    public String getSignature() {
        return signature;
    }

    @JsonProperty
    public CryptoCurrency getCryptoCurrency() {
        return cryptoCurrency;
    }

    @JsonProperty
    public double getAssignedId() {
        return assignedId;
    }

    @JsonProperty
    public String getCertifiedAuthority() {
        return certifiedAuthority;
    }

    @JsonProperty
    public boolean isCertifiedAuthorityTrusted() {
        return certifiedAuthorityTrusted;
    }

    @JsonProperty
    public boolean isTokenTrusted() {
        return tokenTrusted;
    }
}
