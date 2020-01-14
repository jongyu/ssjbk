package biz.zhongyu.repository

import biz.zhongyu.entity.UserPO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

/**
 * @author yuzhong
 * @date 2020/1/14
 * @desc 用户数据库操作接口
 */
@Repository
interface UserRepository : JpaRepository<UserPO, Long> {

    /**
     * 通过用户名查询用户
     * @param username 用户名
     * @return Optional对象
     */
    fun findByUsername(username: String): Optional<UserPO>

    /**
     * 判断用户名是否在数据库中存在
     * @param username 用户名
     * @return 存在返回true，不存在返回false
     */
    fun existsByUsername(username: String): Boolean

}