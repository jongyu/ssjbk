package biz.zhongyu.utils

import javax.servlet.http.HttpServletRequest

/**
 * @author yuzhong
 * @date 2020/1/14
 * @desc IP工具类
 */
object IpUtils {

    /**
     * 获取IP地址
     * @param request 请求信息
     * @return IP地址
     */
    @JvmStatic
    fun getIpAddress(request: HttpServletRequest): String? {
        var ip: String?
        ip = if (request.getHeader("X-Real-IP") != null && request.getHeader("X-Real-IP").trim { it <= ' ' }.length >= 7 &&
                !"unknown".equals(request.getHeader("X-Real-IP").trim { it <= ' ' }, ignoreCase = true) &&
                request.getHeader("X-Real-IP").trim { it <= ' ' } != "0:0:0:0:0:0:0:1" &&
                !request.getHeader("X-Real-IP").trim { it <= ' ' }.startsWith("127.")) {
            request.getHeader("X-Real-IP").trim { it <= ' ' }
        } else {
            request.getHeader("x-forwarded-for")
        }
        if (invalidIp(ip)) {
            ip = request.getHeader("Proxy-Client-IP")
        }
        if (invalidIp(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP")
        }
        if (invalidIp(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP")
        }
        if (invalidIp(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR")
        }
        if (invalidIp(ip)) {
            ip = request.remoteAddr
        }
        if (ip == "0:0:0:0:0:0:0:1") {
            ip = "127.0.0.1"
        }
        if (ip!!.split(",".toRegex()).toTypedArray().size > 1) {
            ip = ip.split(",".toRegex()).toTypedArray().first()
        }
        return ip
    }

    /**
     * 校验IP
     */
    private fun invalidIp(ip: String?): Boolean {
        return ip == null || ip.isEmpty() || "unknown".equals(ip, ignoreCase = true)
    }

}