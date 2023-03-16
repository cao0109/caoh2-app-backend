package cn.caoh2.app.exception;

import cn.caoh2.app.enums.ResultCode;

/**
 * @Author caoh2
 * @Date 2023/3/16 11:19
 * @Description: 自定义异常
 * @Version 1.0
 */
public class ServiceException extends RuntimeException {

    private final ResultCode resultCode;

    public ServiceException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public ServiceException(ResultCode resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
    }

    public ServiceException(ResultCode resultCode, Throwable cause) {
        super(resultCode.getMessage(), cause);
        this.resultCode = resultCode;
    }

    public ServiceException(ResultCode resultCode, String message, Throwable cause) {
        super(message, cause);
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}

