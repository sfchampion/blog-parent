package com.mszlu.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mszlu.blog.dao.mapper.LoginMapper;
import com.mszlu.blog.dao.pojo.SysUser;
import com.mszlu.blog.service.LoginService;
import com.mszlu.blog.service.SysUserService;
import com.mszlu.blog.utils.BlogException;
import com.mszlu.blog.utils.JWTUtils;
import com.mszlu.blog.vo.ResultCode;
import com.mszlu.blog.vo.LoginVo;
import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.params.LoginParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author sfChampion
 * @date 2023/2/8 14:28
 */

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    private static final String slat = "mszlu!@#";

    @Override
    public Result login(LoginParam loginParam) {
        /*
         * 1.检查参数是否合法
         * 2.根据用户名和密码去user表中查询，是否存在
         * 3.如果不存在，登录失败
         * 4.如果存在，使用jwt生成token，返回给前端
         * 5.token放入redis当中，redis token；user信息 设置过期时间
         * （登录认证时，先认证token字符串是否合法，去redis认证是否存在）
         * */
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            return Result.fail(ResultCode.PARAMS_ERROR.getCode(), ResultCode.PARAMS_ERROR.getMsg());
        }
        password = DigestUtils.md5Hex(password + slat);
        SysUser sysUser = sysUserService.findUser(account, password);
        if (sysUser == null) {
            return Result.fail(ResultCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ResultCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        String token = JWTUtils.createToken(sysUser.getId());
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(sysUser), 1, TimeUnit.DAYS);
        return Result.success(token);
    }

    @Override
    public SysUser checkToken(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        Map<String, Object> stringObjectMap = JWTUtils.checkToken(token);
        if (stringObjectMap == null) {
            return null;
        }
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (StringUtils.isBlank(userJson)) {
            return null;
        }
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        return sysUser;
    }

    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_" + token);
        return Result.success(null);
    }

    @Override
    public Result register(LoginParam loginParam) {
        /*
         * 1.判断参数是否合法
         * 2.判断账户是否存在。存在。返回账户已经被注册
         * 3.不存在，注册账户
         * 4.生成token
         * 5.存入redis，并返回
         * 6.注意 加上事务，一旦中间的任何过程出现问题，注册的用户 需要回滚
         * */
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        String nickname = loginParam.getNickname();
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
        String format = dateFormat.format(date);
        if (StringUtils.isBlank(account)
                || StringUtils.isBlank(password)
                || StringUtils.isBlank(nickname)
        ) {
            return Result.fail(ResultCode.PARAMS_ERROR.getCode(), ResultCode.PARAMS_ERROR.getMsg());
        }
        SysUser sysUser = sysUserService.findUserByAccount(account);
        if (sysUser != null) {
            return Result.fail(ResultCode.ACCOUNT_EXIST.getCode(), ResultCode.ACCOUNT_EXIST.getMsg());
        }
        sysUser = new SysUser();
        sysUser.setNickname(nickname);
        sysUser.setAccount(account);
        sysUser.setPassword(DigestUtils.md5Hex(password + slat));
        sysUser.setCreateDate(format);
        //sysUser.setLastLogin(format);
        //sysUser.setAvatar("/static/img/logo.b3a48c0.png");
        sysUser.setAvatar("/static/img/f.jpg");
        sysUser.setAdmin(1); // 1 为true
        sysUser.setDeleted(0); // 0 为false
        sysUser.setSalt("");
        sysUser.setStatus("");
        sysUser.setEmail("");
        this.sysUserService.save(sysUser);

        //token
        String token = JWTUtils.createToken(sysUser.getId());
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(sysUser), 1, TimeUnit.DAYS);
        return Result.success(token);
    }


    @Resource
    private LoginMapper loginMapper;

    @Override
    public Map<String, Object> phoneLoginUser(LoginVo loginVo) {
        // 从loginVo中获取输入的手机号和验证码
        String mobilePhoneNumber = loginVo.getMobilePhoneNumber();
        String code = loginVo.getCode();

        // 判断手机号和验证码是否为空
        if (StringUtils.isBlank(mobilePhoneNumber) || StringUtils.isBlank(code)) {
            throw new BlogException(ResultCode.PARAMS_ERROR.getMsg(),ResultCode.PARAMS_ERROR.getCode());
        }

        // 判断手机验证码和输入的验证码是否一致
        String redisCode = redisTemplate.opsForValue().get(mobilePhoneNumber);
        if (!code.equals(redisCode)) {
            throw  new BlogException(ResultCode.CODE_ERROR.getMsg(),ResultCode.CODE_ERROR.getCode());
        }
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
        String format = dateFormat.format(date);
        SysUser sysUserPhone = null;
        if (sysUserPhone == null) {
            // 判断是否是第一次登录，根据手机号查询数据库，如果不存在相同手机号，就是第一次登录
            QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("mobile_phone_number", mobilePhoneNumber);
            sysUserPhone = loginMapper.selectOne(queryWrapper);
            // 第一次使用这个手机号登录
            if (sysUserPhone == null) {
                // 将用户信息添加到数据库
                sysUserPhone = new SysUser();
                sysUserPhone.setMobilePhoneNumber(mobilePhoneNumber);
                sysUserPhone.setAccount(sysUserPhone.getMobilePhoneNumber());
                sysUserPhone.setNickname(sysUserPhone.getMobilePhoneNumber().substring(7));
                sysUserPhone.setAvatar("/static/img/s.jpg");
                sysUserPhone.setCreateDate(format);
                //sysUserPhone.setLastLogin(format);
                loginMapper.insert(sysUserPhone);
            }
        }

        /*
        * 不是第一次，直接登录
        * 返回登录信息，返回登录用户名，返回token信息
        * */
        Map<String, Object> map = new HashMap<>();
        String phoneNumber = sysUserPhone.getMobilePhoneNumber();
        //if (StringUtils.isEmpty(phoneNumber)) {
        //    phoneNumber = sysUserPhone.getMobilePhoneNumber().substring(7);
        //}
        //if (StringUtils.isEmpty(phoneNumber)) {
        //    phoneNumber = sysUserPhone.getMobilePhoneNumber();
        //}
        map.put("phoneNumber", phoneNumber);
        map.put("account", sysUserPhone.getMobilePhoneNumber());
        map.put("avatar", sysUserPhone.getAvatar());
        // jwt生成token字符串
        String token = JWTUtils.createToken(sysUserPhone.getMobilePhoneNumber());
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(sysUserPhone), 1, TimeUnit.DAYS);
        map.put("token",token);
        return map;
    }
}
