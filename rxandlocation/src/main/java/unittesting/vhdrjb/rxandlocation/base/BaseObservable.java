package unittesting.vhdrjb.rxandlocation.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by vhdrjb on 6/9/17.
 */
public class BaseObservable<T> implements ObservableOnSubscribe<T> {
    public BaseObservable(Context context) {
        this.context = context;
    }

    private final Context context;
    @Override
    public void subscribe(ObservableEmitter<T> e) throws Exception {
        Task<Location> locationTask = createCallBack();
        BaseConnectionCallback<Location> callback=new BaseConnectionCallback(e);
        locationTask.addOnSuccessListener(callback);
        locationTask.addOnFailureListener(callback);
        locationTask.addOnCompleteListener(callback);
    }
    @SuppressLint("MissingPermission")
    private Task<Location> createCallBack() {
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(context);
        return client.getLastLocation();
    }
}
