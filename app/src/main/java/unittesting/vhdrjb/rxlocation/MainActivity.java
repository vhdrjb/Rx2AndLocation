package unittesting.vhdrjb.rxlocation;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import unittesting.vhdrjb.rxandlocation.base.BaseObservable;
import unittesting.vhdrjb.rxandlocation.base.LocationObservable;

/**
 * Created by vhdrjb on 6/9/17.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Observable<Location> observable = Observable.create(new LocationObservable(this));
        observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(this::print, this::fail, this::complete);
    }
    private void print(Location location) {
        System.out.println("result : " + location.getLatitude() + " - " + location.getLongitude());
    }
    private void fail(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }
    private void complete() {
        System.out.println("Complete");
    }
}
