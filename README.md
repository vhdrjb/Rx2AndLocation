# Android Location with RxAndroid 2

There is an [Android Reactive Location](https://github.com/mcharmas/Android-ReactiveLocation) with Rx 1

this library use android [FusedLocationProviderClient](https://developer.android.com/training/location/retrieve-current.html) and [RxAndroid 2](https://github.com/ReactiveX/RxAndroid)

## How to use

specify ``` compile sdk ``` , ``` build tools ``` , ``` min sdk ``` and ``` target sdk ``` in build.gradle (Project) :

```
ext{

    compileSdk=25
    buildTools="25.0.2"
    minSdk=14
    targetSdk=25
}

```

and now you can use location : 

``` java

        Observable<Location> observable = Observable.create(new LocationObservable(this));
        observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(this::locationUsage, this::falied, this::complete);

```


