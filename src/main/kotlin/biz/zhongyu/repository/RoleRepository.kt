package biz.zhongyu.repository

import biz.zhongyu.entity.Role
import biz.zhongyu.enums.RoleName
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author yuzhong
 * @date 2020/1/14
 * @desc
 */
@Repository
interface RoleRepository : JpaRepository<Role, Long> {

    /**
     * 查询角色
     * @param roles 一组角色信息
     * @return 查询到的角色列表
     */
    fun findByNameIn(roles: List<RoleName>): MutableList<Role>

}