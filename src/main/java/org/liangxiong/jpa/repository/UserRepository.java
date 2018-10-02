package org.liangxiong.jpa.repository;

import org.liangxiong.jpa.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liangxiong
 * Date:2018-10-01
 * Time:19:54
 * @Description
 */
@Repository
public interface UserRepository extends BaseRepository<User, Integer> {

    /**
     * 通过用户名查询用户数量
     *
     * @param username
     * @return
     */
    Integer countByUsername(String username);

    /**
     * 通过用户名删除用户
     *
     * @param username 用户名
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
}
