# pay-me

[![Build Status](https://secure.travis-ci.org/jberkel/pay-me.png?branch=master)](http://travis-ci.org/jberkel/pay-me)

An Android library for handling In-App-Billing V3 ([IABv3][]), based on Google's [marketbilling][] sample code.
The goal of this project is to build a reliable and tested library which can easily be included as an [apklib][]
in your (Maven based) projects.

Google's sample code has been refactored and made testable - at the moment there are over 100 unit tests covering
most of the code base. However it has not been used in a published application yet.

## usage

Install the apklib to your local maven repository (it has not been published yet).

```
$ git clone https://github.com/jberkel/pay-me.git
$ mvn install
```

Add a maven dependency in your main project:

```xml
<dependency>
    <groupId>com.github.jberkel.payme</groupId>
    <artifactId>library</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <type>apklib</type>
</dependency>
```

Instantiate and use the [IabHelper][] in your activity:

```java
@Override public void onCreate(Bundle bundle) {
    mIabHelper = new IabHelper(this, "Base64EncodedPublicKey");
    mIabHelper.startSetup(new OnIabSetupFinishedListener() {
        public void onIabSetupFinished(IabResult result) {
          if (result.isSuccess()) {
              // helper is ready to use
              mIabHelper.launchPurchaseFlow(this,
                 "android.test.purchased",
                  ItemType.IN_APP,
                  0,
                  new OnIabPurchaseFinishedListener() {
                     public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
                        // handle purchase result
                     }
                  }, null);
          }
        }
    });
}

@Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    mIabHelper.handleActivityResult(requestCode, resultCode, data);
}
```

##<a name="license">License</a>

This application is released under the terms of the [Apache License, Version 2.0][].

[Apache License, Version 2.0]: http://www.apache.org/licenses/LICENSE-2.0.html

[IABv3]: http://developer.android.com/google/play/billing/api.html
[marketbilling]: https://code.google.com/p/marketbilling/
[apklib]: https://code.google.com/p/maven-android-plugin/wiki/ApkLib
[IabHelper]: https://github.com/jberkel/pay-me/blob/master/library/src/main/java/com/github/jberkel/payme/IabHelper.java

