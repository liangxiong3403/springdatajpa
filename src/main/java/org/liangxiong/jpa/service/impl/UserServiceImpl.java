package org.liangxiong.jpa.service.impl;

import org.liangxiong.jpa.entity.User;
import org.liangxiong.jpa.repository.UserRepository;
import org.liangxiong.jpa.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liangxiong
 * Date:2018-10-01
 * Time:19:59
 * @Description
 */
@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Integer countByUsername(String username) {
        return userRepository.countByUsername(username);
    }

    @Override
    public Integer deleteByUsername(String username) {
        return userRepository.deleteByUsername(username);
    }

    @Override
    public List<User> removeByUsername(String username) {
        return userRepository.removeByUsername(username);
    }

    @Override
    public List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findOne(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public User save(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public List<User> readBySex(String sex) {
        return userRepository.readBySex(sex);
    }

    @Override
    public List<User> findDistinctByUsernameIgnoreCaseOrAgeOrderByUsernameAsc(String username, Integer age) {
        return userRepository.findDistinctByUsernameIgnoreCaseOrAgeOrderByUsernameAsc(username, age);
    }

    @Override
    public List<User> findByUsernameAndSex(String username, String sex) {
        return userRepository.findByUsernameAndSex(username, sex);
    }

    @Override
    public List<User> findByAgeBetween(Integer start, Integer end) {
        return userRepository.findByAgeBetween(start, end);
    }

    @Override
    public List<User> findByUsernameLike(String username) {
        return userRepository.findByUsernameLike(username);
    }

    @Override
    public List<User> findByAgeLessThan(Integer num) {
        return userRepository.findByAgeLessThan(num);
    }

    @Override
    public List<User> findByRoleRoleNameIgnoreCaseOrderByUsernameDesc(String roleName) {
        return userRepository.findByRoleRoleNameIgnoreCaseOrderByUsernameDesc(roleName);
    }
}
