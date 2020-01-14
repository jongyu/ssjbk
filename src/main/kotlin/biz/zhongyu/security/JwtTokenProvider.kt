package biz.zhongyu.security

import cn.hutool.core.date.DateUtil
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.stereotype.Component
import java.util.*

/**
 * @author yuzhong
 * @date 2020/1/14
 * @desc JWT工具类
 */
@Component
class JwtTokenProvider {

    /**
     * 颁发人
     */
    private val issuer = "ssjbk"

    /**
     * 密钥
     */
    private val algorithm = Algorithm.HMAC256("ssjbk12345")

    /**
     * 根据用户名生成Token
     * @param username 用户名
     * @return Token
     */
    fun generate(username: String): String {
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(username)
                .withIssuedAt(Date())
                .withExpiresAt(DateUtil.nextWeek())
                .sign(algorithm)
    }

    /**
     * 校验Token是否有效
     * @param token Token字符串
     * @return 有效时返回用户名或无效返回null
     */
    fun verify(token: String?): String? {
        return if (token != null) {
            val verifier = JWT.require(algorithm).withIssuer(issuer).build()
            verifier.verify(token).subject
        } else {
            null
        }
    }

}