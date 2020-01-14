package biz.zhongyu.config

import biz.zhongyu.domain.ApiResult
import biz.zhongyu.enums.ResultEnum
import biz.zhongyu.exceptions.BusinessException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * @author yuzhong
 * @date 2020/1/14
 * @desc 全局异常处理
 */
@RestControllerAdvice
class GlobalExceptionHandler {

    /**
     * 业务异常处理
     */
    @ExceptionHandler(BusinessException::class)
    fun businessExceptionHandle(ex: BusinessException): ApiResult {
        ex.printStackTrace()
        return ApiResult.failure(ex.code, ex.message)
    }

    /**
     * 参数校验失败处理
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidExceptionHandle(ex: MethodArgumentNotValidException): ApiResult {
        val errors = mutableMapOf<String, String>()
        ex.bindingResult.allErrors.map {
            errors[(it as FieldError).field] = it.defaultMessage ?: "参数校验失败"
        }
        return ApiResult.failure(ResultEnum.PARAM_CHECK_FAILED, errors)
    }

}