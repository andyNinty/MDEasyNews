package andy.lee.mdeasynews.bean;

import java.util.List;

/**
 * andy.lee.easynews.bean
 * Created by andy on 16-11-7.
 */

public class HttpResult<T> {

    private int code;
    private String msg;
    private List<T> newslist;

    public List<T> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<T> newslist) {
        this.newslist = newslist;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }




}
