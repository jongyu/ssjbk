package biz.zhongyu.service.impl

import biz.zhongyu.domain.ApiResult
import biz.zhongyu.domain.Token
import biz.zhongyu.entity.UserDTO
import biz.zhongyu.entity.UserPO
import biz.zhongyu.entity.UserVO
import biz.zhongyu.enums.ResultEnum.*
import biz.zhongyu.enums.RoleName
import biz.zhongyu.exceptions.BusinessException
import biz.zhongyu.repository.RoleRepository
import biz.zhongyu.repository.UserRepository
import biz.zhongyu.security.JwtTokenProvider
import biz.zhongyu.service.UserService
import biz.zhongyu.utils.DozerUtils
import cn.hutool.core.lang.Snowflake
import com.github.dozermapper.core.Mapper
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

/**
 * @author yuzhong
 * @date 2020/1/14
 * @desc 用户服务实现类
 */
@Service
class UserServiceImpl(private val mapper: Mapper,
                      private val snowflake: Snowflake,
                      private val authService: AuthService,
                      private val jwtProvider: JwtTokenProvider,
                      private val roleRepository: RoleRepository,
                      private val userRepository: UserRepository,
                      private val passwordEncoder: BCryptPasswordEncoder) : UserService {

    override fun request(request: UserDTO): ApiResult {
        if (userRepository.existsByUsername(request.username
                        ?: throw BusinessException(DATA_PROCESSING_FAILED))) {
            return ApiResult.failure(USERNAME_IS_TAKEN)
        }
        var user = mapper.map(request, UserPO::class.java)
        user.id = snowflake.nextId()
        user.password = passwordEncoder.encode(request.password)
        user.roles = roleRepository.findByNameIn(mutableListOf(
                RoleName.ROLE_USER,
                RoleName.ROLE_PM)).toHashSet()
        user = userRepository.save(user)
        return ApiResult.success(mapper.map(user, UserVO::class.java))
    }

    override fun login(request: UserDTO): ApiResult {
        try {
            val userDetails = authService.loadUserByUsername(request.username
                    ?: throw BusinessException(DATA_PROCESSING_FAILED))
            if (!passwordEncoder.matches(request.password, userDetails.password)) {
                return ApiResult.failure(WRONG_PASSWORD)
            }
            val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
            SecurityContextHolder.getContext().authentication = authentication
            val token = jwtProvider.generate(userDetails.username)
            return ApiResult.success(Token(token))
        } catch (ex: AuthenticationException) {
            if (ex is UsernameNotFoundException) {
                return ApiResult.failure(USER_DOES_NOT_EXIST)
            }
            return ApiResult.failure(AUTHENTICATION_FAILED)
        }
    }

    override fun queryUser(): ApiResult {
        val list = userRepository.findAll()
        return ApiResult.success(DozerUtils.mapList(mapper, list, UserVO::class.java))
    }

}