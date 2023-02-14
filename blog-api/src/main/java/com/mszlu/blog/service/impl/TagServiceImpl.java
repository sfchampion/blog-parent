package com.mszlu.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mszlu.blog.dao.mapper.TagMapper;
import com.mszlu.blog.dao.pojo.Tag;
import com.mszlu.blog.service.TagService;
import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.TagVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author sfChampion
 * @date 2023/1/31 14:04
 */

@Service
public class TagServiceImpl implements TagService {

    @Resource
    private TagMapper tagMapper;

    public TagVo copy(Tag tag){
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag,tagVo);
        tagVo.setId(String.valueOf(tag.getId()));
        return tagVo;
    }
    public List<TagVo> copyList(List<Tag> tagList){
        List<TagVo> tagVoList = new ArrayList<>();
        for (Tag tag : tagList) {
            tagVoList.add(copy(tag));
        }
        return tagVoList;
    }

    @Override
    public List<TagVo> findTagsByArticleId(Long articleId) {
        List<Tag> tags = tagMapper.findTagsByArticleId(articleId);
        return copyList(tags);
    }

    @Override
    public Result hots(int limit) {
        /*
        * 1.标签所拥有的文章数量最多 最热标签
        * 2.查询 根据tag_id 分组 计数，从大到小 排列 取前limit个
        * */
        List<Long> tagIds = tagMapper.findHotsTagIds(limit);
        if (CollectionUtils.isEmpty(tagIds)) {
            return Result.success(Collections.emptyList());
        }
        // 需要的是 tagId 和 tagName Tag对象
        List<Tag> tagList = tagMapper.findTagsByTagIds(tagIds);
        return Result.success(tagList);
    }

    @Override
    public Result findAll() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Tag::getId,Tag::getTagName);
        List<Tag> tags = this.tagMapper.selectList(queryWrapper);
        return Result.success(copyList(tags));
    }

    @Override
    public Result findAllDetail() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        List<Tag> tags = this.tagMapper.selectList(queryWrapper);
        return Result.success(copyList(tags));
    }

    @Override
    public Result findDetailById(Long id) {
        Tag tag = this.tagMapper.selectById(id);
        return Result.success(copy(tag));
    }
}
