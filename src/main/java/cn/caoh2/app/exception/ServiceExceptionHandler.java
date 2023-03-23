package cn.caoh2.app.exception;

import cn.caoh2.app.enums.ResultCode;
import cn.caoh2.app.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author caoh2
 * @Date 2023/3/16 11:16
 * @Description: 全局异常处理
 * @Version 1.0
 */
@Slf4j
@ControllerAdvice
public class ServiceExceptionHandler {

    /**
     * 处理 ServiceException
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result<Object> handleServiceException(ServiceException e) {
        log.error("ServiceException: {}", e.getMessage(), e);
        return Result.failure(e.getResultCode());
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<Object> handleException(Exception e) {
        log.error("Exception: {}", e.getMessage(), e);
        if (e instanceof AccessDeniedException) {
            return Result.failure(ResultCode.PERMISSION_NO_ACCESS);
        }
        if (e instanceof AuthenticationException) {
            return Result.failure(ResultCode.USER_LOGIN_ERROR);
        }
        return Result.failure(ResultCode.SYSTEM_INNER_ERROR, e.getMessage());
    }
}

