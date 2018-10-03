package org.liangxiong.jpa.repository;

import org.liangxiong.jpa.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Stream;

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
     * Because we treat the underscore character as a reserved character, we strongly advise following standard Java naming conventions (that is, not using underscores in property names but using camel case instead).
     *
     * @param roleName
     * @return
     */
    List<User> findByRole_RoleNameIgnoreCaseOrderByUsernameDesc(String roleName);

    /**
     * 查询指定页码数据
     *
     * @param age      年龄
     * @param pageable 分页条件
     * @return
     */
    Page<User> findByAgeLessThan(Integer age, Pageable pageable);

    /**
     * 查询指定页码数据(Slice不会进行count查询)
     *
     * @param sex      性别
     * @param pageable 分页条件
     * @return
     */
    Slice<User> findBySex(String sex, Pageable pageable);

    /**
     * 查询指定性别数据并排序
     *
     * @param sex  性别
     * @param sort 排序条件
     * @return
     */
    List<User> findBySex(String sex, Sort sort);

    /**
     * 模糊查询指定姓名数据并排序
     *
     * @param username 姓名
     * @param pageable 分页条件(包含排序条件)
     * @return
     */
    List<User> findByUsernameLike(String username, Pageable pageable);

    /**
     * 查询结果按照角色名称降序排序,获取第一个用户
     *
     * @return
     */
    User findFirstByOrderByRoleRoleNameDesc();

    /**
     * 查询按照姓名升序排列地第一个用户
     *
     * @return
     */
    User findTopByOrderByUsernameAsc();

    /**
     * 查询满足条件地前10个用户,并按照规则分页
     *
     * @param username 用户名
     * @param pageable 分页规则
     * @return
     */
    Page<User> queryFirst10ByUsernameLike(String username, Pageable pageable);

    /**
     * 查询满足条件地前3个用户,并按照规则分页(Slice不会进行count查询)
     *
     * @param username
     * @param pageable
     * @return
     */
    Slice<User> findTop3ByUsernameLike(String username, Pageable pageable);

    /**
     * 使用自定义查询语句查询数据并流化处理
     *
     * @return
     */
    @Query("select u from User u")
    Stream<User> findAllByCustomerQueryAndStream();

    /**
     * 查询满足条件地数据
     *
     * @param condition 条件值
     * @return
     */
    Stream<User> readAllByUsernameIsNotLike(String condition);

    /**
     * 使用自定义语句查询数据并分页,查询结果转化为流(不会进行count查询)
     *
     * @param pageable
     * @return
     */
    @Query("select u from User u")
    Stream<User> streamAllPaged(Pageable pageable);

    /**
     * 异步:通过用户名和角色名查询用户
     *
     * @param username 用户名
     * @param roleName 角色名
     * @return
     */
    @Async
    Future<User> findByUsernameAndRoleRoleName(String username, String roleName);

    /**
     * 异步:通过角色名查询用户
     *
     * @param roleName 角色名
     * @return
     */
    @Async
    CompletableFuture<List<User>> findByRoleRoleName(String roleName);

    /**
     * 异步:通过性别查询用户
     *
     * @param sex 性别
     * @return
     */
    @Async
    ListenableFuture<List<User>> findBySex(String sex);
}



