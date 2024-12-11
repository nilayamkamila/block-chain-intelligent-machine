package com.blockchains.ingest.proxy.utils;

import com.amazonaws.services.kinesis.model.ResourceNotFoundException;
import com.blockchains.ingest.proxy.exceptions.ProxyException;
import com.blockchains.stream.data.models.CryptoCoinUserToken;
import com.blockchains.stream.data.models.CryptoCurrency;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IngresProxyUtility {
    public void validateRequestPayload(CryptoCoinUserToken cryptoCoinUserToken) {
        Optional.ofNullable(cryptoCoinUserToken).orElseThrow(RuntimeException::new);
        validateCurrencyMinimumRequiredFieldValues(cryptoCoinUserToken.getCryptoCurrency());
    }
    private void validateCurrencyMinimumRequiredFieldValues(CryptoCurrency cryptoCurrency){
        Optional.ofNullable(cryptoCurrency).orElseThrow(RuntimeException::new);
        Optional.ofNullable(cryptoCurrency.getCurrencyId()).orElseThrow(() -> new ProxyException(ProxyConstants.CURRENCY_ID_NOT_FOUND));
        Optional.ofNullable(cryptoCurrency.getOpeningValue()).orElseThrow(() -> new ProxyException(ProxyConstants.CURRENCY_OPENING_VALUE_NOT_FOUND));
        Optional.ofNullable(cryptoCurrency.getClosingValue()).orElseThrow(() -> new ProxyException(ProxyConstants.CURRENCY_CLOSING_VALUE_NOT_FOUND));
        Optional.ofNullable(cryptoCurrency.getHighestValue()).orElseThrow(() -> new ProxyException(ProxyConstants.CURRENCY_HIGHEST_VALUE_NOT_FOUND));
        Optional.ofNullable(cryptoCurrency.getLowestValue()).orElseThrow(() -> new ProxyException(ProxyConstants.CURRENCY_LOWEST_VALUE_NOT_FOUND));
        Optional.ofNullable(cryptoCurrency.getVolumeStocks()).orElseThrow(() -> new ProxyException(ProxyConstants.CURRENCY_STOCK_VOLUME_VALUE_NOT_FOUND));
    }
}
