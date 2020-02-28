package biz.zhongyu.domain

import biz.zhongyu.enums.ResultEnum
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable

/**
 * @author yuzhong
 * @date 2020/1/14
 * @desc restful返回的基本信息
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
class ApiResult : Serializable {

    var code: Int? = 0
    var message: String? = "successful"
    var data: Any? = null

    constructor()

    constructor(data: Any?) {
        this.data = data
    }

    constructor(code: Int?, message: String?) {
        this.code = code
        this.message = message
    }

    constructor(code: Int?, message: String?, data: Any?) {
        this.code = code
        this.message = message
        this.data = data
    }

    companion object {

        fun success(): ApiResult {
            return ApiResult()
        }

        fun success(data: Any?): ApiResult {
            return ApiResult(data)
        }

        fun failure(result: ResultEnum): ApiResult {
            return ApiResult(result.code, result.message)
        }

        fun failure(code: Int?, message: String?): ApiResult {
            return ApiResult(code, message)
        }

        fun failure(result: ResultEnum, data: Any?): ApiResult {
            return ApiResult(result.code, result.message, data)
        }

        fun failure(code: Int?, message: String?, data: Any?): ApiResult {
            return ApiResult(code, message, data)
        }
    }

}