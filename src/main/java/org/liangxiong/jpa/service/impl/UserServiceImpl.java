package org.liangxiong.jpa.service.impl;

import org.liangxiong.jpa.entity.User;
import org.liangxiong.jpa.repository.UserRepository;
import org.liangxiong.jpa.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public List<User> findByRole_RoleNameIgnoreCaseOrderByUsernameDesc(String roleName) {
        return userRepository.findByRole_RoleNameIgnoreCaseOrderByUsernameDesc(roleName);
    }

    @Override
    public Page<User> findByAgeLessThan(Integer age, Pageable pageable) {
        return userRepository.findByAgeLessThan(age, pageable);
    }

    @Override
    public Slice<User> findBySex(String sex, Pageable pageable) {
        return userRepository.findBySex(sex, pageable);
    }

    @Override
    public List<User> findBySex(String sex, Sort sort) {
        return userRepository.findBySex(sex, sort);
    }

    @Override
    public List<User> findByUsernameLike(String username, Pageable pageable) {
        return userRepository.findByUsernameLike(username, pageable);
    }

    @Override
    public User findFirstByOrderByRoleRoleNameDesc() {
        return userRepository.findFirstByOrderByRoleRoleNameDesc();
    }

    @Override
    public User findTopByOrderByUsernameAsc() {
        return userRepository.findTopByOrderByUsernameAsc();
    }

    @Override
    public Page<User> queryFirst10ByUsernameLike(String username, Pageable pageable) {
        return userRepository.queryFirst10ByUsernameLike(username, pageable);
    }

    @Override
    public Slice<User> findTop3ByUsernameLike(String username, Pageable pageable) {
        return userRepository.findTop3ByUsernameLike(username, pageable);
    }

    /**
     * Stream处理需要事务支持,不能在controller处理流,因为流已经关闭了
     *
     * @return
     */
    @Override
    public List<User> findAllByCustomerQueryAndStream() {
        try (Stream<User> stream = userRepository.findAllByCustomerQueryAndStream()) {
            return stream.collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> readAllByUsernameIsNotLike(String condition) {
        try (Stream<User> stream = userRepository.readAllByUsernameIsNotLike(condition)) {
            return stream.collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> streamAllPaged(Pageable pageable) {
        try (Stream<User> stream = userRepository.streamAllPaged(pageable)) {
            return stream.collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findByUsernameAndRoleRoleName(String username, String roleName) throws ExecutionException, InterruptedException {
        Future<User> future = userRepository.findByUsernameAndRoleRoleName(username, roleName);
        if (future.isCancelled()) {
            System.out.println("cancelled");
        } else if (future.isDone()) {
            return future.get();
        }
        return null;
    }

    @Override
    public List<User> findByRoleRoleName(String roleName) throws ExecutionException, InterruptedException {
        CompletableFuture<List<User>> future = userRepository.findByRoleRoleName(roleName);
        if (future.isCompletedExceptionally()) {
            System.out.println("error");
        } else if (future.isCancelled()) {
            System.out.println("cancelled");
        } else if (future.isDone()) {
            return future.get();
        }
        return null;
    }

    @Override
    public List<User> findBySex(String sex) throws ExecutionException, InterruptedException {
        ListenableFuture<List<User>> future = userRepository.findBySex(sex);
        future.addCallback((Object o) -> System.out.println("success"), (Throwable throwable) -> System.out.println("failed"));
        return future.get();
    }
}
