package org.liangxiong.jpa.controller;

import org.liangxiong.jpa.entity.User;
import org.liangxiong.jpa.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author liangxiong
 * Date:2018-10-01
 * Time:20:00
 * @Description 用户控制器
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

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
    public List<User> findByRole_RoleNameIgnoreCaseOrderByUsernameDesc(String roleName) {
        return userService.findByRole_RoleNameIgnoreCaseOrderByUsernameDesc(roleName);
    }

    /**
     * 查询小于age地指定页码用户列表
     *
     * @param age       年龄
     * @param startPage 开始页码
     * @param pageSize  分页大小
     * @return
     */
    @GetMapping("/ageAndPage")
    public List<User> findByAge(Integer age, Integer startPage, Integer pageSize) {
        Pageable pageable = new PageRequest(startPage, pageSize);
        Page<User> result = userService.findByAgeLessThan(age, pageable);
        return result.getContent();
    }

    /**
     * 查询指定性别地用户列表并分页
     *
     * @param sex       性别
     * @param startPage 开始页码
     * @param pageSize  分页大小
     * @return
     */
    @GetMapping("/sexAndPage")
    public List<User> findBySex(String sex, Integer startPage, Integer pageSize) {
        Pageable pageable = new PageRequest(startPage, pageSize);
        Slice<User> result = userService.findBySex(sex, pageable);
        return result.getContent();
    }

    /**
     * 查询指定性别数据并排序
     *
     * @param sex      性别
     * @param property 排序条件
     * @return
     */
    @GetMapping("sexAndSort")
    public List<User> findBySex(String sex, String property) {
        Sort sort = new Sort(Sort.Direction.DESC, property);
        return userService.findBySex(sex, sort);
    }

    /**
     * 模糊查询指定姓名数据并排序
     *
     * @param username  姓名
     * @param startPage 开始页码
     * @param pageSize  分页大小
     * @param property  排序条件
     * @return
     */
    @GetMapping("/usernameAndPageAndSort")
    public List<User> findByUsernameLike(String username, Integer startPage, Integer pageSize, String property) {
        Sort sort = new Sort(Sort.Direction.DESC, property);
        Pageable pageable = new PageRequest(startPage, pageSize, sort);
        return userService.findByUsernameLike(username, pageable);
    }

    /**
     * 查询结果按照角色名称降序排序,获取第一个用户
     *
     * @return
     */
    @GetMapping("/firstOrderByRoleName")
    public User findFirstByOrderByRoleRoleNameDesc() {
        return userService.findFirstByOrderByRoleRoleNameDesc();
    }

    /**
     * 查询按照姓名升序排列地第一个用户
     *
     * @return
     */
    @GetMapping("/topOrderByUsername")
    public User findTopByOrderByUsernameAsc() {
        return userService.findTopByOrderByUsernameAsc();
    }

    /**
     * 查询满足条件地前10个用户,并按照规则分页
     *
     * @param username  用户名
     * @param startPage 开始页码
     * @param pageSize  分页大小
     * @return
     */
    @GetMapping("/queryFirst10")
    public List<User> queryFirst10ByUsernameLike(String username, Integer startPage, Integer pageSize) {
        Pageable pageable = new PageRequest(startPage, pageSize);
        return userService.queryFirst10ByUsernameLike(username, pageable).getContent();
    }

    /**
     * 查询满足条件地前3个用户,并按照规则分页
     *
     * @param username
     * @param startPage 开始页码
     * @param pageSize  分页大小
     * @return
     */
    @GetMapping("/top3ByUsernameLike")
    public List<User> findTop3ByUsernameLike(String username, Integer startPage, Integer pageSize) {
        Pageable pageable = new PageRequest(startPage, pageSize);
        return userService.findTop3ByUsernameLike(username, pageable).getContent();
    }

    /**
     * 使用自定义查询语句查询数据并流化处理
     *
     * @return
     */
    @GetMapping("/customerQueryAndStream")
    public List<User> findAllByCustomerQueryAndStream() {
        return userService.findAllByCustomerQueryAndStream();
    }

    /**
     * 查询满足条件地数据
     *
     * @param condition 条件值
     * @return
     */
    @GetMapping("notLikeUsernameAndStream")
    public List<User> readAllByUsernameIsNotLike(String condition) {
        return userService.readAllByUsernameIsNotLike(condition);
    }

    /**
     * 使用自定义语句查询数据并分页,查询结果转化为流
     *
     * @param startPage 开始页码
     * @param pageSize  分页大小
     * @return
     */
    @GetMapping("/streamAndPage")
    public List<User> streamAllPaged(Integer startPage, Integer pageSize) {
        Pageable pageable = new PageRequest(startPage, pageSize);
        return userService.streamAllPaged(pageable);
    }

    /**
     * 异步:通过用户名和角色名查询用户
     *
     * @param username 用户名
     * @param roleName 角色名
     * @return
     */
    @GetMapping("/asyncUsernameAndRoleName")
    public User findByUsernameAndRoleRoleName(String username, String roleName) {
        try {
            return userService.findByUsernameAndRoleRoleName(username, roleName);
        } catch (ExecutionException e) {
            logger.error("异步执行出现异常");
        } catch (InterruptedException e) {
            logger.error("异步执行被中断");
        }
        return null;
    }

    /**
     * 异步:通过角色名查询用户
     *
     * @param roleName 角色名
     * @return
     */
    @GetMapping("/asyncRoleName")
    public List<User> findByRoleRoleName(String roleName) {
        try {
            return userService.findByRoleRoleName(roleName);
        } catch (ExecutionException e) {
            logger.error("异步执行出现异常");
        } catch (InterruptedException e) {
            logger.error("异步执行被中断");
        }
        return null;
    }

    /**
     * 异步:通过性别查询用户
     *
     * @param sex 性别
     * @return
     */
    @GetMapping("/asyncSex")
    public List<User> findOneBySex(String sex) {
        try {
            return userService.findBySex(sex);
        } catch (ExecutionException e) {
            logger.error("异步执行出现异常");
        } catch (InterruptedException e) {
            logger.error("异步执行被中断");
        }
        return null;
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
