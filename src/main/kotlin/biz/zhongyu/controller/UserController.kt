package biz.zhongyu.controller

import biz.zhongyu.domain.ApiResult
import biz.zhongyu.entity.UserDTO
import biz.zhongyu.service.UserService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 * @author yuzhong
 * @date 2020/1/14
 * @desc 用户请求处理
 */
@RestController
@RequestMapping("user")
class UserController(private val userService: UserService) {

    /**
     * 处理用户注册接口
     * @param request 用户注册信息
     * @return ApiResult
     */
    @PostMapping("register")
    fun userRegister(@Validated @RequestBody request: UserDTO): ApiResult {
        return userService.request(request)
    }

    /**
     * 处理用户登录接口
     * @param request 用户登录信息
     * @return ApiResult
     */
    @PostMapping("login")
    fun userLogin(@Validated @RequestBody request: UserDTO): ApiResult {
        return userService.login(request)
    }

    /**
     * 查询用户
     * @return ApiResult
     */
    @GetMapping
    fun queryQuery(): ApiResult {
        return userService.queryUser()
    }

}