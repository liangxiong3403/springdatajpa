package org.liangxiong.jpa.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author liangxiong
 * Date:2018-10-02
 * Time:19:41
 * @Description 角色实体
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "t_role")
public class Role implements Serializable {

    private static final long serialVersionUID = -6050150705416727830L;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色名称
     */
    @Size(min = 1, max = 16)
    private String roleName;

    /**
     * 角色描述
     */
    @Size(min = 1, max = 16)
    private String description;

    /**
     * 一个角色可以对应多个用户
     */
    @JSONField(serialize = false)
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "role")
    private Set<User> users = new HashSet<>(8);

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
