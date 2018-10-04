package org.liangxiong.jpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author liangxiong
 * Date:2018-10-01
 * Time:19:32
 * @Description 用户实体类
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "t_user")
public class User implements Serializable {

    private static final long serialVersionUID = -480949435137714008L;

    private static final Logger logger = LoggerFactory.getLogger(User.class);

    /**
     * 主键:用户id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    @Size(min = 1, max = 8)
    private String username;

    /**
     * 年龄
     */
    @NotNull
    private Integer age;

    /**
     * 性别
     */
    @Size(min = 1, max = 2)
    private String sex;

    /**
     * 关系：多个用户对应于一个角色
     */
    @JoinColumn(name = "role_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    private Role role;

    /**
     * 测试方法拆分为属性地算法(SpringDataJPA Documentation chapter 4.4.3)
     * The algorithm would match in the first split round already, choose the wrong property, and fail (as the type of roleRole probably has no name property).
     */
    ///@JSONField(serialize = false)
    ///private String roleRole;

    /**
     * publish event
     * The methods are called every time one of a Spring Data repository’s save(…) methods is called
     */
    @DomainEvents
    private void domainEvents() {
        logger.info("publish event");
    }

    /**
     * clean the list of events to be published
     * The methods are called every time one of a Spring Data repository’s save(…) methods is called
     */
    @AfterDomainEventPublication
    private void callbackMethod() {
        logger.info("clean the list of events to be published");
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", role=" + role +
                '}';
    }
}
