package andy.lee.mdeasynews.net;

/**
 * andy.lee.easynews.net
 * Created by andy on 16-11-7.
 */

public class ApiException extends RuntimeException{
    public static final int RESULT_IS_NULL = 290;
    public static final int KEYWORDS_CON_NOT_BE_NULL = 260;

    public ApiException(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }


    private static String getApiExceptionMessage(int code){
        String message = "";
        switch (code) {
            case RESULT_IS_NULL:
                message = "请求结果为空";
                break;
            case KEYWORDS_CON_NOT_BE_NULL:
                message = "关键字不能为空";
                break;
            default:
                message = "未知错误";

        }
        return message;
    }
}
