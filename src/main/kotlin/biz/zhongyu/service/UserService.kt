package biz.zhongyu.service

import biz.zhongyu.domain.ApiResult
import biz.zhongyu.entity.UserDTO

/**
 * @author yuzhong
 * @date 2020/1/14
 * @desc 用户服务接口
 */
interface UserService {

    /**
     * 用户注册
     * @param request 用户请求信息
     * @return ApiResult
     */
    fun request(request: UserDTO): ApiResult

    /**
     * 用户登录
     * @param request 用户请求信息
     * @return ApiResult
     */
    fun login(request: UserDTO): ApiResult

    /**
     * 查询用户
     * @return ApiResult
     */
    fun queryUser(): ApiResult

}