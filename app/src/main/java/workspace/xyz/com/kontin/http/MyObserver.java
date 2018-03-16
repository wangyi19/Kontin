package workspace.xyz.com.kontin.http;

import android.content.Context;
import android.widget.Toast;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MyObserver<T> implements Observer<T> {

    private SubscriberOnNextListener<T> mSubscriberOnNextListener;

    private Context context;

    public MyObserver(SubscriberOnNextListener mSubscriberOnNextListener, Context context) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;

    }


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T data) {
        mSubscriberOnNextListener.onNext(data);
    }

    @Override
    public void onError(Throwable e) {

        if (e instanceof SocketTimeoutException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onError();
        }
    }

    @Override
    public void onComplete() {

    }
}
