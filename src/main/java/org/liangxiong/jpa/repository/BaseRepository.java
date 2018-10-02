package org.liangxiong.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author liangxiong
 * Date:2018-10-01
 * Time:19:55
 * @Description 基础地Repository
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {

    /**
     * 查询数据并排序
     *
     * @param sort 排序规则
     * @return
     */
    Iterable<T> findAll(Sort sort);

    /**
     * 查询数据并分页
     *
     * @param pageable 分页规则
     * @return
     */
    Page<T> findAll(Pageable pageable);

}
