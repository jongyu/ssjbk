package biz.zhongyu.security

import biz.zhongyu.domain.ApiResult
import biz.zhongyu.enums.ResultEnum
import com.alibaba.fastjson.JSON
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author yuzhong
 * @date 2020/1/14
 * @desc 无权限时处理
 */
@Component
class RestfulAccessDeniedHandler : AccessDeniedHandler {

    override fun handle(request: HttpServletRequest, response: HttpServletResponse, accessDeniedException: AccessDeniedException) {
        response.characterEncoding = Charsets.UTF_8.displayName()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        val result = ApiResult.failure(ResultEnum.ACCESS_DENIED)
        response.writer.println(JSON.toJSONString(result))
        response.writer.flush()
    }

}