package com.mszlu.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.mszlu.blog.dao.mapper.ArticleMapper;
import com.mszlu.blog.dao.pojo.Article;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author sfChampion
 * @date 2023/2/9 12:08
 */

@Component
public class ThreadService {

    @Async("taskExecutor")
    public void updateArticleViewCount(ArticleMapper articleMapper, Article article) {
        int viewCounts = article.getViewCounts();
        Article articleUpdate = new Article();
        articleUpdate.setViewCounts(viewCounts + 1);
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId,article.getId());
        // 设置一个 为了在多线程的环境下，保证线程安全
        updateWrapper.eq(Article::getViewCounts,viewCounts);
        // update article set view_count=100 where view_count=99 and id=11
        articleMapper.update(articleUpdate,updateWrapper);
        try {
            //睡眠5秒 证明不会影响主线程的使用
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
