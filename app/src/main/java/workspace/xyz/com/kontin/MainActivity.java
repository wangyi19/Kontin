package workspace.xyz.com.kontin;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TimingLogger;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.List;

import io.reactivex.Flowable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import workspace.xyz.com.kontin.eneity.MyEneity;
import workspace.xyz.com.kontin.eneity.MyObject;
import workspace.xyz.com.kontin.eneity.News;
import workspace.xyz.com.kontin.http.BaseEneity;
import workspace.xyz.com.kontin.http.GetBody;
import workspace.xyz.com.kontin.http.HttpResultFunction;
import workspace.xyz.com.kontin.http.HttpUtils;
import workspace.xyz.com.kontin.http.MyObserver;
import workspace.xyz.com.kontin.http.SubscriberOnNextListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bbt;
    private Button bbt1;
    private WebView wv;
    private SubscriberOnNextListener<List<News>> onNextListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bbt = (Button) findViewById(R.id.bt_);
        bbt1 = (Button) findViewById(R.id.bt_web);
        bbt.setOnClickListener(this);
        TextView textView = new TextView(this);
        textView.setText("");


        wv = (WebView) findViewById(R.id.wv);
        WebSettings settings = wv.getSettings();
        //调用WebView关联的WebSettings中setJavaScriptEnable(true)方法。
        settings.setJavaScriptEnabled(true);
        wv.loadUrl("file:///android_asset/index.html");
        //调用WebView关联的WebSettings中addJavaScriptInterface

        wv.addJavascriptInterface(new MyObject(this, "dd"), "my");
        bbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("<<<<", "hello");
                String msg = "hello";
                wv.loadUrl("javascript:alertMessage(\"" + msg + "\")");

            }
        });

        onNextListener = new SubscriberOnNextListener<List<News>>() {
            @Override
            public void onNext(List<News> o) {
                Log.e(">>data", o.toString());
            }

            @Override
            public void onError() {

            }
        };
    }

    @Override
    public void onClick(View v) {

      /*  Intent intent = new Intent(MainActivity.this, MyIntentService.class);
        intent.putExtra("params", "MainActivity");
        startActivityForResult(intent, 0);
        startService(intent);*/
        initData();


    }

    private void initData() {
        initRxjava();
       /*Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://10.36.124.134:8080/sinhand/").build();
        GetBody body = retrofit.create(GetBody.class);
        Call<MyEneity> call = body.getNews();
        call.enqueue(new Callback<MyEneity>() {
            @Override
            public void onResponse(Call<MyEneity> call, Response<MyEneity> response) {
                Log.d(">>>>>",call.request().url().toString());

                bbt.setText("111");
            }

            @Override
            public void onFailure(Call<MyEneity> call, Throwable t) {
                Log.d(">>>>>","失败");
            }
        });

        HttpUtils httpUtils = new HttpUtils();
        httpUtils.request(new Callback<MyEneity>() {
            @Override
            public void onResponse(Call<MyEneity> call, Response<MyEneity> response) {
                Log.d(">>>>>", call.request().url().toString());



                bbt.setText("111");
            }

            @Override
            public void onFailure(Call<MyEneity> call, Throwable t) {
                Log.d(">>>>>", "失败");
            }
        });*/
    }

    private void initRxjava() {


        HttpUtils.getInstant().getNews(new MyObserver<List<News>>(onNextListener, MainActivity.this));
        HttpUtils.getInstant().api.getNews().
                map(new HttpResultFunction<List<News>>()).
                doOnNext(new Consumer<List<News>>() {
                    @Override
                    public void accept(List<News> news) throws Exception {
                        Log.e(">>>>>doOnNext", news.toString());
                    }
                }).
                flatMap(new Function<List<News>, ObservableSource<News>>() {
                    @Override
                    public ObservableSource<News> apply(List<News> news) throws Exception {
                        Log.e(">>>>>>>>", news.size() + "");
                        return Observable.fromIterable(news);
                    }
                }).
                subscribeOn(Schedulers.io()).
                unsubscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<News>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(News value) {
                        if (value != null) {
                            Log.e(">>>>>>>>" + value.getName(), value.toString());
                        } else {
                            Log.e(">>>>>>>>", "null");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                Intent intent = new Intent();
                startActivity(intent);
                this.finish();
                ActivityManager.getMyMemoryState(new ActivityManager.RunningAppProcessInfo());

                break;
            case 2:
                final boolean b = TextUtils.isEmpty("");
                aa("", b);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String host = "127.0.0.1";
                        int port = 55533;
                 /*     try {
                            Socket socket = new Socket(host, port);
                            OutputStream outputStream = socket.getOutputStream();
                            String msg = "hello_go";
                            outputStream.write(msg.getBytes());
                            socket.shutdownOutput();
                            InputStream inputStream = socket.getInputStream();
                            byte[] bytes = new byte[1024];
                            int len;
                            StringBuilder sb = new StringBuilder();
                            while ((len = inputStream.read(bytes)) != -1) {
                                sb.append(new String(bytes, 0, len, "UTF_8"));
                            }
                            inputStream.close();
                            outputStream.close();
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*/

                    }
                }).start();


                break;


        }
    }

    /**
     * @param a
     * @param aa
     */
    private void aa(String a, boolean aa) {

    }


}
