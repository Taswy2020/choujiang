package com.taswy.choujiang.luckDraw.dao;

import com.taswy.choujiang.luckDraw.entity.LuckyDraw;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (LuckyDraw)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-23 19:57:53
 */
public interface LuckyDrawDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    LuckyDraw queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param luckyDraw 查询条件
     * @param pageable  分页对象
     * @return 对象列表
     */
    List<LuckyDraw> queryAllByLimit(LuckyDraw luckyDraw, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param luckyDraw 查询条件
     * @return 总行数
     */
    long count(LuckyDraw luckyDraw);

    /**
     * 新增数据
     *
     * @param luckyDraw 实例对象
     * @return 影响行数
     */
    int insert(LuckyDraw luckyDraw);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<LuckyDraw> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<LuckyDraw> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<LuckyDraw> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<LuckyDraw> entities);

    /**
     * 修改数据
     *
     * @param luckyDraw 实例对象
     * @return 影响行数
     */
    int update(LuckyDraw luckyDraw);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

