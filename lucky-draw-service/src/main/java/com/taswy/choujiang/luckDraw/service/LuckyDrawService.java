package com.taswy.choujiang.luckDraw.service;

import com.taswy.choujiang.luckDraw.entity.LuckyDraw;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (LuckyDraw)表服务接口
 *
 * @author makejava
 * @since 2024-04-23 19:57:53
 */
public interface LuckyDrawService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    LuckyDraw queryById(Integer id);

    /**
     * 分页查询
     *
     * @param luckyDraw   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<LuckyDraw> queryByPage(LuckyDraw luckyDraw, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param luckyDraw 实例对象
     * @return 实例对象
     */
    LuckyDraw insert(LuckyDraw luckyDraw);

    /**
     * 修改数据
     *
     * @param luckyDraw 实例对象
     * @return 实例对象
     */
    LuckyDraw update(LuckyDraw luckyDraw);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
