package com.taswy.choujiang.luckDraw.service.impl;

import com.taswy.choujiang.luckDraw.entity.LuckyDraw;
import com.taswy.choujiang.luckDraw.dao.LuckyDrawDao;
import com.taswy.choujiang.luckDraw.service.LuckyDrawService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (LuckyDraw)表服务实现类
 *
 * @author makejava
 * @since 2024-04-23 19:57:53
 */
@Service("luckyDrawService")
public class LuckyDrawServiceImpl implements LuckyDrawService {
    @Resource
    private LuckyDrawDao luckyDrawDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public LuckyDraw queryById(Integer id) {
        return this.luckyDrawDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param luckyDraw   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<LuckyDraw> queryByPage(LuckyDraw luckyDraw, PageRequest pageRequest) {
        long total = this.luckyDrawDao.count(luckyDraw);
        return new PageImpl<>(this.luckyDrawDao.queryAllByLimit(luckyDraw, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param luckyDraw 实例对象
     * @return 实例对象
     */
    @Override
    public LuckyDraw insert(LuckyDraw luckyDraw) {
        this.luckyDrawDao.insert(luckyDraw);
        return luckyDraw;
    }

    /**
     * 修改数据
     *
     * @param luckyDraw 实例对象
     * @return 实例对象
     */
    @Override
    public LuckyDraw update(LuckyDraw luckyDraw) {
        this.luckyDrawDao.update(luckyDraw);
        return this.queryById(luckyDraw.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.luckyDrawDao.deleteById(id) > 0;
    }
}
