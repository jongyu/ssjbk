package biz.zhongyu.entity

import biz.zhongyu.enums.RoleName
import com.alibaba.fastjson.JSON
import org.hibernate.annotations.NaturalId
import javax.persistence.*

/**
 * @author yuzhong
 * @date 2020/1/14
 * @desc 角色对象
 */
@Entity
@Table(name = "role")
class Role {

    @Id
    var id: Long? = 0

    @NaturalId
    @Enumerated(EnumType.STRING)
    var name: RoleName? = null

    override fun toString(): String {
        return JSON.toJSONString(this)
    }

}