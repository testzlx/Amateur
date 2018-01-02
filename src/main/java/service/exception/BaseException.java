package service.exception;

/**
 * Created by zhanglinxing on 2017/8/14.
 */
public class BaseException extends  RuntimeException {
    protected  String classPath;
    protected  String method;


    public BaseException(String msg){
        super(msg);
    }

    public BaseException(String msg,Throwable e){
        super(msg,e);
    }
    public BaseException(Throwable e){
        super(e);
    }

    public BaseException(){
        super();
    }


}
