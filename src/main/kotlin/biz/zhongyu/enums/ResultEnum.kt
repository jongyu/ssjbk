package biz.zhongyu.enums

/**
 * @author yuzhong
 * @date 2020/1/14
 * @desc 返回结果定义
 */
enum class ResultEnum(val code: Int, val message: String) {

    AUTHENTICATION_FAILED(401,"认证失败，请登录"),
    ACCESS_DENIED(403, "您没有权限访问此资源"),
    PARAM_CHECK_FAILED(10001, "参数校验失败"),
    DATA_PROCESSING_FAILED(10002, "数据没有经过安全处理，请联系管理员"),
    USERNAME_IS_TAKEN(10010, "用户名已被占用"),
    EMAIL_IS_TAKEN(10011, "邮箱已被占用"),
    USER_DOES_NOT_EXIST(10012, "用户不存在"),
    WRONG_PASSWORD(10013, "密码错误"),
    ;

}