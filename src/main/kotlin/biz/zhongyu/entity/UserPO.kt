package biz.zhongyu.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank

/**
 * @author yuzhong
 * @date 2020/1/14
 * @desc 用户数据库对象
 */
@Entity
@Table(name = "user")
class UserPO {

    @Id
    var id: Long? = 0

    var username: String? = null

    var password: String? = null

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = [JoinColumn(name = "user_id")], inverseJoinColumns = [JoinColumn(name = "role_id")])
    var roles: Set<Role> = HashSet()

    @CreatedDate
    var createTime: Date? = null

    @LastModifiedDate
    var updateTime: Date? = null

}

/**
 * @author yuzhong
 * @date 2020/1/14
 * @desc 用户展示到前端时的对象
 */
class UserVO {

    var id: Long? = 0
    var username: String? = null
    var updateTime: String? = null

}

class UserDTO {

    @NotBlank(message = "用户名不能为空")
    var username: String? = null

    @NotBlank(message = "密码不能为空")
    var password: String? = null

}