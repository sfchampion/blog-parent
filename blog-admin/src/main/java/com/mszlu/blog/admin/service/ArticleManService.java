package com.mszlu.blog.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mszlu.blog.admin.mapper.ArticleManMapper;
import com.mszlu.blog.admin.model.params.PageParam;
import com.mszlu.blog.admin.pojo.Article;
import com.mszlu.blog.admin.vo.PageResult;
import com.mszlu.blog.admin.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sfChampion
 * @date 2023/2/23 10:58
 */

@Service
public class ArticleManService {

    @Resource
    private ArticleManMapper articleManMapper;

    public Result articleList(PageParam pageParam) {
        Page<Article> page = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(pageParam.getQueryString())) {
            queryWrapper.like(Article::getTitle,pageParam.getQueryString());
        }
        Page<Article> articlePage = articleManMapper.selectPage(page, queryWrapper);
        PageResult<Article> pageResult = new PageResult<>();
        pageResult.setList(articlePage.getRecords());
        pageResult.setTotal(articlePage.getTotal());
        return Result.success(pageResult);
    }


    public Result deleteArticle(Long id) {
        this.articleManMapper.deleteById(id);
        return Result.success(null);
    }
}
