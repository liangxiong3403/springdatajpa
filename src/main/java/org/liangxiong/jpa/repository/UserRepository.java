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

    /**
     * 通过性别查询员工
     *
     * @param sex 性别
     * @return
     */
    List<User> readBySex(String sex);

    /**
     * 通过用户名或年龄查询用户
     *
     * @param username 用户名
     * @param age      年龄
     * @return
     */
    List<User> findDistinctByUsernameIgnoreCaseOrAgeOrderByUsernameAsc(String username, Integer age);

    /**
     * 通过用户名和性别的组合条件查询用户列表
     *
     * @param username 用户名
     * @param sex      性别
     * @return
     */
    List<User> findByUsernameAndSex(String username, String sex);

    /**
     * 查询指定年龄区间地用户列表
     *
     * @param start 起始年龄
     * @param end   结束年龄
     * @return
     */
    List<User> findByAgeBetween(Integer start, Integer end);

    /**
     * 模糊查询匹配名称地用户列表
     *
     * @param username 用户名
     * @return
     */
    List<User> findByUsernameLike(String username);

    /**
     * 查询小于num地用户列表
     *
     * @param num 大小
     * @return
     */
    List<User> findByAgeLessThan(Integer num);

    /**
     * 通过角色名称查询用户,然后按照用户名倒序排序
     *
     * @param roleName
     * @return
     */
    List<User> findByRoleRoleNameIgnoreCaseOrderByUsernameDesc(String roleName);
}
