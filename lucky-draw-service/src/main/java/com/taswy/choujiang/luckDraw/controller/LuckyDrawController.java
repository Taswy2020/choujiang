package com.taswy.choujiang.luckDraw.controller;

import com.taswy.choujiang.luckDraw.entity.LuckyDraw;
import com.taswy.choujiang.luckDraw.service.LuckyDrawService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (LuckyDraw)表控制层
 *
 * @author makejava
 * @since 2024-04-23 19:57:52
 */
@RestController
@RequestMapping("luckyDraw")
public class LuckyDrawController {
    /**
     * 服务对象
     */
    @Resource
    private LuckyDrawService luckyDrawService;

    /**
     * 分页查询
     *
     * @param luckyDraw   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<LuckyDraw>> queryByPage(LuckyDraw luckyDraw, PageRequest pageRequest) {
        return ResponseEntity.ok(this.luckyDrawService.queryByPage(luckyDraw, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<LuckyDraw> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.luckyDrawService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param luckyDraw 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<LuckyDraw> add(LuckyDraw luckyDraw) {
        return ResponseEntity.ok(this.luckyDrawService.insert(luckyDraw));
    }

    /**
     * 编辑数据
     *
     * @param luckyDraw 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<LuckyDraw> edit(LuckyDraw luckyDraw) {
        return ResponseEntity.ok(this.luckyDrawService.update(luckyDraw));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.luckyDrawService.deleteById(id));
    }

}

