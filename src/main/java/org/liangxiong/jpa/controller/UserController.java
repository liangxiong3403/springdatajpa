package org.liangxiong.jpa.controller;

import org.liangxiong.jpa.entity.User;
import org.liangxiong.jpa.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liangxiong
 * Date:2018-10-01
 * Time:20:00
 * @Description 用户控制器
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 查询指定名称用户人数
     *
     * @param username 用户名
     * @return
     */
    @GetMapping
    public Integer countByUsername(String username) {
        return userService.countByUsername(username);
    }

    /**
     * 查询指定用户名用户列表
     *
     * @param username 用户名
     * @return
     */
    @GetMapping("/username")
    public List<User> findByUsername(String username) {
        return userService.findByUsername(username);
    }

    /**
     * 查询指定用户id的用户列表
     *
     * @param id 用户id
     * @return
     */
    @GetMapping("/id")
    public User findOne(Integer id) {
        return userService.findOne(id);
    }

    /**
     * 通过性别查询用户列表
     *
     * @param sex 性别
     * @return
     */
    @GetMapping("/sex")
    public List<User> readBySex(String sex) {
        return userService.readBySex(sex);
    }

    /**
     * 查询用户名或年龄匹配条件地用户列表,用户名不区分大小写,按照姓名升序排序
     *
     * @param username 用户名
     * @param age      年龄
     * @return
     */
    @GetMapping("/usernameOrAge")
    public List<User> findDistinctByUsernameIgnoreCaseOrAgeOrderByUsernameAsc(String username, Integer age) {
        /*
         * 作用类似于findUserDistinctByUsernameIgnoreCaseOrAgeOrderByUsernameAsc
         * 作用类似于findDistinctUserByUsernameIgnoreCaseOrAgeOrderByUsernameAsc
         */
        return userService.findDistinctByUsernameIgnoreCaseOrAgeOrderByUsernameAsc(username, age);
    }

    /**
     * 通过用户名和性别的组合条件查询用户列表
     *
     * @param username 用户名
     * @param sex      性别
     * @return
     */
    @GetMapping("/usernameAndSex")
    public List<User> findByUsernameAndSex(String username, String sex) {
        return userService.findByUsernameAndSex(username, sex);
    }

    /**
     * 查询指定年龄区间地用户列表
     *
     * @return
     */
    @GetMapping("/ageBetween")
    public List<User> findByAgeBetween(Integer start, Integer end) {
        return userService.findByAgeBetween(start, end);
    }

    /**
     * 模糊查询匹配名称地用户列表
     *
     * @param username 用户名
     * @return
     */
    @GetMapping("/usernameLike")
    public List<User> findByUsernameLike(String username) {
        return userService.findByUsernameLike(username);
    }

    /**
     * 查询小于num地用户列表
     *
     * @param num 大小
     * @return
     */
    @GetMapping("/ageLessThan")
    public List<User> findByAgeLessThan(Integer num) {
        return userService.findByAgeLessThan(num);
    }

    /**
     * 通过角色名称查询用户列表,忽略角色名称大小写
     *
     * @param roleName 角色名称
     * @return
     */
    @GetMapping("/roleName")
    public List<User> findByRoleRoleNameIgnoreCaseOrderByUsernameDesc(String roleName) {
        return userService.findByRoleRoleNameIgnoreCaseOrderByUsernameDesc(roleName);
    }

    /**
     * 通过用户名删除用户,并返回删除数据条数
     *
     * @param username 用户名
     * @return
     */
    @DeleteMapping("/one")
    public Integer deleteByUsername(String username) {
        return userService.deleteByUsername(username);
    }

    /**
     * 通过用户名删除匹配地用户,并返回已经删除地用户列表
     *
     * @param username 用戶名
     * @return
     */
    @DeleteMapping("/many")
    public List<User> removeByUsername(String username) {
        return userService.removeByUsername(username);
    }

    /**
     * 添加用户
     *
     * @param user JSON格式地用户
     * @return
     */
    @PostMapping
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

}
