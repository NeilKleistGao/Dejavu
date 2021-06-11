package com.buptse.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.buptse.common.util.*;
import com.buptse.dto.LoginDto;
import com.buptse.dto.CommonResult;
import com.buptse.mapper.UserMapper;
import com.buptse.pojo.Car;
import com.buptse.pojo.User;
import com.buptse.pojo.UserToken;
import com.buptse.service.ICarService;
import com.buptse.service.IShiroService;
import com.buptse.service.IUserService;
import java.nio.charset.MalformedInputException;
import java.util.HashMap;
import java.util.Map;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    IUserService service;
    @Autowired
    ICarService carService;
    @Autowired
    IShiroService shiroService;
    @Autowired
    IUserService userService;
    @GetMapping("/user/get/{uid}")
    public User getUSerById(@PathVariable("uid") Integer uid) {
        User user = service.getById(uid);
        return user;
    }
    @GetMapping("/user/info/query_all")
    public CommonResult queryAllUser(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "phone",required = false) String phone,
            @RequestParam(value = "email",required = false) String email){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if(name!=null){userQueryWrapper.eq("name",name);}
        if(phone!=null){userQueryWrapper.eq("phone_number",phone);}
        if(email!=null){userQueryWrapper.eq("mail",email);}
        List<User> userList = service.list(userQueryWrapper);
        return CommonResult.success(userList);
    }

    @PostMapping("/user/login")
    public Map<String,Object> loginUser(
            @RequestBody LoginDto loginDto){
        String phoneNumber = loginDto.getPhoneNumber();
        String password = loginDto.getPassword();
        Map<String,Object> result = new HashMap<>();
        User user = shiroService.findByUserPhone(phoneNumber);
        if(user == null || !user.getPassword().equals(password)){
            result.put("status",400);
            result.put("info","密码错误");
        }else{
            result = shiroService.createToken(user.getUid());
            result.put("uid",user.getUid());
            result.put("status",200);
            result.put("info","登录成功");
        }
        return result;
    }

    @GetMapping("/user/info/query")
    /**
    * @Description: 获取用户信息
    * @Param: [userId]
    * @return: com.buptse.pojo.User
    * @Author: gerayking
    * @Date: 2021/6/5-14:40
    */
    public User getUsersInfo(
        @RequestParam("uid")Integer userId
    ){
        User user = userService.getById(userId);
        return user;
    }
    @RequiresRoles({"manager,user"})
    @PostMapping("/user/info/modify")
    /**
    * @Description: 修改用户信息
    * @Param: [token, userId, mail, avatar]
    * @return: java.util.Map
    * @Author: gerayking
    * @Date: 2021/6/5-14:40
    */
    public Map modifyUserInfo(
        @RequestParam(value = "token")String token,
        @RequestParam(value = "uid")Integer userId,
        @RequestParam(value = "main",required = false)String mail,
        @RequestParam(value = "avatar",required = false)String avatar
    ){
        Map<String, Object> result = new HashMap<>();
        UserToken userToken = shiroService.findByToken(token);
        if(userToken.getUserId() != userId){
            result.put("result","false");
            result.put("info","token认证失败");
            return result;
        }
        User user = new User();
        user.setUid(userId);
        user.setAvatar(avatar);
        user.setMail(mail);
        boolean flag = userService.updateById(user);
        result.put("result",flag);
        return result;
    }

    @RequiresRoles({"manage"})
    @PostMapping("/user/info/password")
    /**
    * @Description: 修改用户密码
    * @Param: [uid, oldPassword, newPassword]
    * @return: java.util.Map
    * @Author: gerayking
    * @Date: 2021/6/5-14:40
    */
    public Map modifyPassword(
        @RequestParam(value = "uid")Integer uid,
        @RequestParam(value = "old")String oldPassword,
        @RequestParam(value = "new")String newPassword
    ){
        Map<String, Object> result = new HashMap<>();
        User user = userService.getById(uid);
        if(user.getPassword() != oldPassword){
            result.put("result",-1);
            result.put("info","密码错误");
        }else{
            user.setPassword(newPassword);
            userService.updateById(user);
            result.put("result",uid);
            result.put("info","密码修改成功");
        }
        return result;
    }

    @RequiresRoles({"user"})
    @PostMapping("/user/logout")
    public Map logout(
        @RequestParam("token")String token
    ){
        Map<String, Object> result = new HashMap<>();
        boolean flag = shiroService.logout(token);
        result.put("result",flag);
        return result;
    }
    @PostMapping("/user/register")
    public Map userRegister(
        @RequestParam("phone")String phoneNumber,
        @RequestParam("mail")String mail,
        @RequestParam("password")String password
    ){
        Map<String, Object> result = new HashMap<>();
        User user = userService.findByPhoneNumber(phoneNumber);
        if(user != null){
            result.put("result",-1);
            result.put("info","用户已存在");
            return result;
        }
        user = new User();
        user.setPassword(password);
        user.setMail(mail);
        user.setPhone_number(phoneNumber);
        Integer uid = userService.insertUser(user);
        result.put("result",uid);
        result.put("info","注册成功");
        return result;
    }




}
