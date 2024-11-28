package com.blockchains.stream.data.models;

import java.io.Serializable;

public class CryptoCoinUserToken implements Serializable {
    private static final long serialVersionUID = 8145257839787754636L;
    CryptoCurrency cryptoCurrency;
    double assignedId;
    String certifiedAuthority;
    boolean certifiedAuthorityTrusted;
    boolean tokenTrusted;
    String signature;

    public CryptoCoinUserToken(){}

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

    public CryptoCurrency getCryptoCurrency() {
        return cryptoCurrency;
    }

    public double getAssignedId() {
        return assignedId;
    }

    public String getCertifiedAuthority() {
        return certifiedAuthority;
    }

    public boolean isCertifiedAuthorityTrusted() {
        return certifiedAuthorityTrusted;
    }

    public boolean isTokenTrusted() {
        return tokenTrusted;
    }
}
