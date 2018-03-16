package workspace.xyz.com.kontin.http;

/**
 * Created by gaodi on 2018/2/11.
 */

public class BaseEneity<T> {
    @Override
    public String toString() {
        return "MyEneity{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", status=" + status +
                ", time='" + time + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * code : 200
     * data : [{"create_time":"2017-08-16 20:10:40","id":2,"image_url":"http://localhost:8080/SinghandCMS/img/1502885442000481814.jpg","name":"产品案例"},{"create_time":"","id":3,"image_url":"","name":"核心技术"},{"create_time":"","id":4,"image_url":"","name":"公司动态"},{"create_time":"","id":5,"image_url":"","name":"关于星汉"},{"create_time":"","id":6,"image_url":"","name":"加入星汉"},{"create_time":"","id":7,"image_url":"","name":"动态详情"}]
     * msg : SUCCESS
     * status : 0
     * time : 2018-02-10 11:01:15
     */

    private String code;
    private String msg;
    private int status;
    private String time;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public T getData() {
        return data;
    }

    public void setData(T time) {
        this.data = time;
    }
}


