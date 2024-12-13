package com.blockchains.tokens.data.utility;

import com.blockchains.stream.data.models.CoinUser;
import com.blockchains.stream.data.models.CreditData;
import com.blockchains.stream.data.models.CryptoCoinUserToken;
import com.blockchains.stream.data.models.CryptoCurrency;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BlockTokensGenerator {

    @Autowired
    Faker faker;

    private CreditData[] getUsersCreditData(int numberOfCreditInfo){
        CreditData[] creditData = new CreditData[numberOfCreditInfo];

        for (int i=0; i<numberOfCreditInfo; i++){
            creditData[i] = new CreditData(faker.business().creditCardType()
                    , faker.business().creditCardNumber()
                    ,faker.business().creditCardExpiry()
                    ,faker.commerce().productName());
        }
        return creditData;
    }

    private CoinUser getCoinUser(){
        return new CoinUser(UUID.randomUUID().toString()
                , faker.name().name()
                , faker.date().birthday()
                ,Math.round(faker.random().nextDouble()* Math.ceil(100000))
                ,Math.round(faker.random().nextDouble()* Math.ceil(10000))
                , String.valueOf(faker.bool().bool())
                , faker.address().latitude() + ","+ faker.address().longitude()
                , faker.letterify("activity on account, favourite and activity")
                , Math.random()
                , getUsersCreditData(Math.max(0,5))
        );
    }

    private CryptoCurrency getCryptoCurrency(){
        String randomId = UUID.randomUUID().toString();
        return new CryptoCurrency(randomId.substring(0, randomId.indexOf("-"))
                , faker.currency().code()
                , faker.currency().name()
                , faker.random().nextDouble()*Math.ceil(10000000)
                , faker.random().nextDouble()*Math.ceil(10000000)
                , faker.random().nextDouble()*Math.ceil(10000000)
                , faker.random().nextDouble()*Math.ceil(10000000)
                , faker.random().nextDouble()*Math.ceil(10000000)
                , Math.round(faker.random().nextDouble()* Math.ceil(1000000))
                , faker.crypto().md5()
                , faker.crypto().sha512()
                , getCoinUser());
    }

    public CryptoCoinUserToken getCryptoCoinUserToken(){
        return new CryptoCoinUserToken(getCryptoCurrency()
                , Math.abs(1230000.0)
                , faker.company().name()
                , faker.bool().bool()
                , faker.bool().bool()
                , faker.app().author());
    }
}
