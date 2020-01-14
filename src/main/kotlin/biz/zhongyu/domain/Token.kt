package biz.zhongyu.domain

/**
 * @author yuzhong
 * @date 2020/1/14
 * @desc JWT返回结果
 */
data class Token(val token: String) {

    var type: String = "Bearer"

}