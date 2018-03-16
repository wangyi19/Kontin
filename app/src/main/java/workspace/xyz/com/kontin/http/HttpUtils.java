package workspace.xyz.com.kontin.http;


import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Callback;
import workspace.xyz.com.kontin.eneity.News;

/**
 * Created by gaodi on 2018/2/11.
 */

public class HttpUtils {
    public GetBody api;
    private static volatile HttpUtils httpUtils;

    private <T> void toSubscribe(Observable<T> observable, Observer<T> subscriber) {
        observable.subscribeOn(Schedulers.io()).
                unsubscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(subscriber);

    }

    public static HttpUtils getInstant() {
        if(httpUtils == null){
            synchronized (HttpUtils.class){
                if (httpUtils == null) {
                    httpUtils = new HttpUtils();
                }
            }
        }

        return httpUtils;
    }


    private HttpUtils() {
        api = RetrofitServiceManager.getInstance().create(GetBody.class);
    }

    public void getNews(Observer<List<News>> subscriber) {

        Observable news = api.getNews().map(new HttpResultFunction<List<News>>());

        toSubscribe(news, subscriber);

    }

    public void request(Callback callback) {
        //api.getNews().enqueue(callback);
    }

    public void update(Callback callback, String news) {
        api.update(news).enqueue(callback);
    }

}
