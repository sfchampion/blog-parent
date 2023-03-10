package com.mszlu.blog.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mszlu.blog.admin.pojo.Admin;
import com.mszlu.blog.admin.pojo.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author sfChampion
 * @date 2023/2/10 15:33
 */
public interface AdminMapper extends BaseMapper<Admin> {
    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select("SELECT * FROM `ms_permission` where id in (select permission_id from ms_admin_permission where admin_id=#{adminId})")
    List<Permission> findPermissionByAdminId(Long id);
}
