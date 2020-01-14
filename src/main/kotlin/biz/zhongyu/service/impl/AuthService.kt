package biz.zhongyu.service.impl

import biz.zhongyu.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * @author yuzhong
 * @date 2020/1/14
 * @desc 只定义UserDetailsService
 */
@Service
class AuthService(private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val optional = userRepository.findByUsername(username)
        if (optional.isPresent) {
            val user = optional.get()
            val roles = user.roles.map { SimpleGrantedAuthority(it.name?.name) }.toMutableList()
            return User.builder().username(user.username).password(user.password).authorities(roles).build()
        } else {
            throw UsernameNotFoundException("$username not found!")
        }
    }

}