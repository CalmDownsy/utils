package exception;

/**
 * @author zhangsy
 * @date 2017/12/28
 */
public class UtilsException extends Exception {

    private Integer errorCode;

    public UtilsException() {
        super();
    }

    public UtilsException(Integer errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
