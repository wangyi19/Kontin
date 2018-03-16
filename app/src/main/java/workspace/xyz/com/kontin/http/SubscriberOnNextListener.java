package workspace.xyz.com.kontin.http;

public  interface SubscriberOnNextListener<T> {
    void onNext(T t);
    void onError();
}
