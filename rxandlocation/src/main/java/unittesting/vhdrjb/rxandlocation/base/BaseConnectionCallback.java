package unittesting.vhdrjb.rxandlocation.base;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import io.reactivex.ObservableEmitter;

/**
 * Created by vhdrjb on 6/9/17.
 */

public class BaseConnectionCallback<T> implements OnCompleteListener<T>,OnSuccessListener<T>,OnFailureListener {
    public BaseConnectionCallback(ObservableEmitter<T> emitter) {
        this.emitter = emitter;
    }

    private final ObservableEmitter<T> emitter;
    @Override
    public void onComplete(@NonNull Task<T> task) {
            emitter.onComplete();
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        emitter.onError(new RuntimeException("Connection Failed", e.getCause()));
    }

    @Override
    public void onSuccess(T t) {
        if (t == null) {
            emitter.onError(new RuntimeException("Null Value"));
        }else
        emitter.onNext(t);
    }
}
