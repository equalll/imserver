package com.cniao5.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 公共dao层接口
 * Created by sony on 2015/12/14.
 */
public interface IEntityDao<T, PK extends Serializable> {
    /**
     * 获取实体
     *
     * @param id
     * @return
     */
    T get(PK id);

    /**
     * 获取实体列表
     *
     * @return
     */
    List<T> getAll();

    /**
     * 保存实体
     *
     * @param entity
     */
    void save(T entity);

    /**
     * 删除实体
     *
     * @param entity
     */
    void remove(T entity);

    /**
     * 通过id删除实体
     *
     * @param id
     */
    void removeById(PK id);

    /**
     * 更新实体
     *
     * @param entity
     */
    void update(T entity);

    /**
     * 获取Entity对象的主键名.
     *
     * @param clazz
     * @return
     */
    String getIdName(Class<T> clazz);
}