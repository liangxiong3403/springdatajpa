package org.liangxiong.jpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
