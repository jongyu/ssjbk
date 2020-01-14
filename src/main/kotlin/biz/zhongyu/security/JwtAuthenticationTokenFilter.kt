package biz.zhongyu.security

import biz.zhongyu.service.impl.AuthService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author yuzhong
 * @date 2020/1/14
 * @desc JWT认证过滤器
 */
@Component
class JwtAuthenticationTokenFilter(
        private val jwtProvider: JwtTokenProvider,
        private val authService: AuthService) : OncePerRequestFilter() {

    /**
     * 过滤Token
     */
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val token = resolveToken(request)
        val username = jwtProvider.verify(token)
        if (StringUtils.hasText(token) && username != null) {
            val userDetails = authService.loadUserByUsername(username)
            val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

    /**
     * 获取Token
     * @param request 请求
     * @return Token或null
     */
    private fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7)
        } else null
    }

}