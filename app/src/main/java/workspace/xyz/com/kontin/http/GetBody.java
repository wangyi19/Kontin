package workspace.xyz.com.kontin.http;





import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import workspace.xyz.com.kontin.eneity.MyEneity;
import workspace.xyz.com.kontin.eneity.News;

/**
 * Created by gaodi on 2018/2/10.
 */

public interface GetBody {

    @GET("getSubPages.do")
    Observable<BaseEneity<List<News>>> getNews();




    @POST("update.do")
    Call<MyEneity> update(@Query("id") String id);

    @GET("getNews.do")
    Call<MyEneity> getLists();

}
