package com.mszlu.blog.controller;

import com.mszlu.blog.utils.QiniuUtils;
import com.mszlu.blog.vo.ErrorCode;
import com.mszlu.blog.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author sfChampion
 * @date 2023/2/9 18:01
 */

@RestController
@RequestMapping("upload")
public class UploadController {

    @Resource
    private QiniuUtils qiniuUtils;

    @PostMapping
    public Result upload(@RequestParam("image") MultipartFile file) {
        // 原始文件名称
        String originalFilename = file.getOriginalFilename();
        // 唯一的文件名称
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFilename, ".");
        // 上传文件
        /*
         * 上传到七牛云 云服务器 按量计费 把图片发放到离用户最近的服务器上
         * 降低自身应用服务器的带宽消耗
         * */
        boolean upload = qiniuUtils.upload(file, fileName);
        if (upload) {
            return Result.success(QiniuUtils.url + fileName);
        } else {
            return Result.fail(ErrorCode.UPLOAD_FAIL.getCode(), ErrorCode.UPLOAD_FAIL.getMsg());
        }
    }
}
