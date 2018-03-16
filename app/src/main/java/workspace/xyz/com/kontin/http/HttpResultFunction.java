package workspace.xyz.com.kontin.http;

import io.reactivex.functions.Function;

public class HttpResultFunction<T> implements Function<BaseEneity<T>, T> {
    @Override
    public T apply(BaseEneity<T> tBaseEneity) {
        if (!"200".equals(tBaseEneity.getCode())) {
            throw new ApiException(tBaseEneity.getMsg());
        }
        return tBaseEneity.getData();
    }
}
