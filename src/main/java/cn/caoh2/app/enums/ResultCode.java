package cn.caoh2.app.enums;


/**
 * @Author caoh2
 * @Date 2023/3/16 11:21
 * @Description: 返回结果状态码枚举类
 * @Version 1.0
 */
public enum ResultCode {
    /* 成功状态码 */
    SUCCESS(2000, "操作成功"),

    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(10001, "参数无效"),
    PARAM_IS_BLANK(10002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(10003, "参数类型错误"),
    PARAM_NOT_COMPLETE(10004, "参数缺失"),

    /* 用户错误：20001-29999*/
    USER_NOT_LOGGED_IN(20001, "用户未登录"),
    USER_LOGIN_ERROR(20002, "账号不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN(20003, "账号已被禁用"),
    USER_NOT_EXIST(20004, "用户不存在"),
    USER_HAS_EXISTED(20005, "用户已存在"),
    USER_PASSWORD_ERROR(20006, "密码错误"),
    USER_REGISTER_ERROR(20007, "注册失败"),

    /* 业务错误：30001-39999 */
    SPECIFIED_QUESTIONED_USER_NOT_EXIST(30001, "某业务出现问题"),
    DATA_INSERT_ERROR(30002, "数据插入失败"),
    DATA_UPDATE_ERROR(30003, "数据更新失败"),
    DATA_DELETE_ERROR(30004, "数据删除失败"),
    DATA_SELECT_ERROR(30005, "数据查询失败"),

    /* 系统错误：40001-49999 */
    SYSTEM_INNER_ERROR(40001, "系统繁忙，请稍后重试"),
    SYSTEM_ERROR(40002, "系统错误,请联系管理员"),

    /* 数据错误：50001-599999 */
    RESULT_DATA_NONE(50001, "数据未找到"),
    DATA_IS_WRONG(50002, "数据有误"),
    DATA_ALREADY_EXISTED(50003, "数据已存在"),

    /* 接口错误：60001-69999 */
    INTERFACE_INNER_INVOKE_ERROR(60001, "内部系统接口调用异常"),
    INTERFACE_OUTER_INVOKE_ERROR(60002, "外部系统接口调用异常"),
    INTERFACE_FORBID_VISIT(60003, "该接口禁止访问"),
    INTERFACE_ADDRESS_INVALID(60004, "接口地址无效"),
    INTERFACE_REQUEST_TIMEOUT(60005, "接口请求超时"),
    INTERFACE_EXCEED_LOAD(60006, "接口负载过高"),

    /* 权限错误：70001-79999 */
    PERMISSION_NO_ACCESS(70001, "无访问权限"),

    /* 上传文件错误：80001-89999 */
    FILE_UPLOAD_ERROR(80001, "文件上传失败"),
    FILE_DOWNLOAD_ERROR(80002, "文件下载失败"),
    FILE_NOT_EXIST(80003, "文件不存在"),
    FILE_TYPE_ERROR(80004, "文件类型错误"),
    FILE_SIZE_ERROR(80005, "文件大小错误"),
    FILE_NAME_ERROR(80006, "文件名错误"),

    /* 任务错误：90001-99999 */
    TASK_NOT_EXIST(90001, "任务不存在"),
    TASK_ALREADY_EXISTED(90002, "任务已存在"),
    TASK_ALREADY_STARTED(90003, "任务已启动"),
    TASK_ALREADY_STOPPED(90004, "任务已停止"),
    TASK_ALREADY_FINISHED(90005, "任务已完成"),
    TASK_ALREADY_FAILED(90006, "任务已失败"),
    TASK_ALREADY_CANCELED(90007, "任务已取消"),
    TASK_ALREADY_DELETED(90008, "任务已删除"),
    TASK_ALREADY_PAUSED(90009, "任务已暂停"),
    TASK_ALREADY_RESUMED(90010, "任务已恢复"),
    TASK_ALREADY_RESTARTED(90011, "任务已重启"),
    TASK_ALREADY_REMOVED(90012, "任务已移除");
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


