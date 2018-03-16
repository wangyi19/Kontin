package workspace.xyz.com.kontin.eneity;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by gaodi on 2018/2/24.
 */

public class MyObject {
    private final String data;
    private final Context mContext;

    public MyObject(Context mainActivity, String dd) {
        this.data = dd;
        mContext = mainActivity;
    }

    @JavascriptInterface
    public String getData() {

        List<Person> mlist = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mlist.add(new Person("姓名" + i, i + "", "工作单位" + i));
        }
        Gson gson = new Gson();
        String d = gson.toJson(mlist);
        Log.d("TAG", "getData: dddd" + d);
        return d;
    }

}
