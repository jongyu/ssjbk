package biz.zhongyu.exceptions

import biz.zhongyu.enums.ResultEnum

/**
 * @author yuzhong
 * @date 2020/1/14
 * @desc 业务异常
 */
class BusinessException(result: ResultEnum) : RuntimeException() {

    var code: Int = result.code
    override var message: String = result.message

}