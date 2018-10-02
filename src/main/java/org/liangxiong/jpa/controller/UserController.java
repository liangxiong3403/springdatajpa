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
 * @Description
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public Integer countByUsername(String username) {
        return userService.countByUsername(username);
    }

    @GetMapping("/username")
    public List<User> findByUsername(String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/id")
    public User findOne(Integer id) {
        return userService.findOne(id);
    }

    @DeleteMapping("/one")
    public Integer deleteByUsername(String username) {
        return userService.deleteByUsername(username);
    }

    @DeleteMapping("/many")
    public List<User> removeByUsername(String username) {
        return userService.removeByUsername(username);
    }

    @PostMapping
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

}
