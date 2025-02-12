curl --location --request POST 'http://alb-proxy-ingester-aws-1005051216.us-east-1.elb.amazonaws.com/coiningestservices' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
--data-raw '{
  "cryptoCurrency" : {
    "currencyId" : "88b0fba8",
    "code" : "MYR",
    "name" : "Nakfa",
    "openingValue" : 2671403.14761697,
    "closingValue" : 4093785.844037685,
    "highestValue" : 7892925.001469595,
    "lowestValue" : 9300187.70444347,
    "adjClose" : 3377795.537361784,
    "volumeStocks" : 877969,
    "md5" : "71cba56606012913b62d5cc7175335d0",
    "sha512" : "1b1310d0aaeeae655b8ebb493961df2f009ccf7ea202a14951117c39dda0578d184a4459f1f36a24e2b40cb6ce17980110940e91e7aa8edec839616335504678",
    "currentUser" : {
      "userIdentity" : "d101803a-c05c-4400-a92a-d648b940192f",
      "userName" : "Annette Spencer",
      "userCreated" : 1158495898502,
      "userFollowers" : 75845,
      "userFriends" : 2463,
      "userVerified" : "true",
      "userLocation" : "85.644319,-62.351736",
      "userText" : "activity on account, favourite and activity",
      "userCreditRating" : 0.3707067954179859,
      "creditData" : [ {
        "creditCardType" : "discover",
        "creditCardNumber" : "1212-1221-1121-1234",
        "creditCardExpiry" : "2011-10-12",
        "creditCardActivity" : "Lightweight Plastic Keyboard"
      }, {
        "creditCardType" : "american_express",
        "creditCardNumber" : "1211-1221-1234-2201",
        "creditCardExpiry" : "2012-11-12",
        "creditCardActivity" : "Mediocre Concrete Bench"
      }, {
        "creditCardType" : "dankort",
        "creditCardNumber" : "1228-1221-1221-1431",
        "creditCardExpiry" : "2012-11-12",
        "creditCardActivity" : "Mediocre Copper Bench"
      }, {
        "creditCardType" : "mastercard",
        "creditCardNumber" : "1228-1221-1221-1431",
        "creditCardExpiry" : "2012-11-12",
        "creditCardActivity" : "Fantastic Steel Table"
      }, {
        "creditCardType" : "solo",
        "creditCardNumber" : "1211-1221-1234-2201",
        "creditCardExpiry" : "2015-11-11",
        "creditCardActivity" : "Incredible Leather Table"
      }, {
        "creditCardType" : "american_express",
        "creditCardNumber" : "1211-1221-1234-2201",
        "creditCardExpiry" : "2011-10-12",
        "creditCardActivity" : "Durable Rubber Lamp"
      }, {
        "creditCardType" : "jcb",
        "creditCardNumber" : "1211-1221-1234-2201",
        "creditCardExpiry" : "2013-9-12",
        "creditCardActivity" : "Enormous Leather Wallet"
      }, {
        "creditCardType" : "visa",
        "creditCardNumber" : "1228-1221-1221-1431",
        "creditCardExpiry" : "2013-9-12",
        "creditCardActivity" : "Lightweight Marble Shoes"
      }, {
        "creditCardType" : "solo",
        "creditCardNumber" : "1211-1221-1234-2201",
        "creditCardExpiry" : "2015-11-11",
        "creditCardActivity" : "Synergistic Marble Bench"
      }, {
        "creditCardType" : "diners_club",
        "creditCardNumber" : "1212-1221-1121-1234",
        "creditCardExpiry" : "2015-11-11",
        "creditCardActivity" : "Mediocre Granite Bottle"
      } ]
    }
  },
  "assignedId" : 1230000.0,
  "certifiedAuthority" : "Bauch, Bauch and Bauch",
  "certifiedAuthorityTrusted" : false,
  "tokenTrusted" : true,
  "signature" : "Evangeline Schultz"
}'