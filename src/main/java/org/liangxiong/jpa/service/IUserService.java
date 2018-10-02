package org.liangxiong.jpa.service;

import org.liangxiong.jpa.entity.User;

import java.util.List;

/**
 * @author liangxiong
 * Date:2018-10-01
 * Time:19:58
 * @Description
 */
public interface IUserService {

    /**
     * 通过用户名查询用户数量
     *
     * @param username 用户名
     * @return 数量
     */
    Integer countByUsername(String username);

    /**
     * 通过用户名删除用户
     *
     * @param username
     * @return
     */
    Integer deleteByUsername(String username);

    /**
     * 通过用户名删除多个用户
     *
     * @param username
     * @return
     */
    List<User> removeByUsername(String username);

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return
     */
    List<User> findByUsername(String username);

    /**
     * 通过用户id查询用户
     *
     * @param id 用户id
     * @return
     */
    User findOne(Integer id);

    /**
     * 新增用户
     *
     * @param entity 用户实体
     * @return
     */
    User save(User entity);
}
