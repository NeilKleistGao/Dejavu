package com.buptse.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.buptse.common.RESULT;
import com.buptse.common.util.*;
import com.buptse.dto.LoginDto;
import com.buptse.dto.CommonResult;
import com.buptse.dto.ModifyPasswordDto;
import com.buptse.dto.ModifyUserInfoDto;
import com.buptse.dto.UserRegisterDto;
import com.buptse.mapper.UserMapper;
import com.buptse.pojo.Car;
import com.buptse.pojo.User;
import com.buptse.pojo.UserToken;
import com.buptse.service.ICarService;
import com.buptse.service.IShiroService;
import com.buptse.service.IUserService;
import java.nio.charset.MalformedInputException;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    @RequiresRoles({"manager"})
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
    @RequiresRoles(value = {"manager","user"},logical = Logical.OR)
    @PostMapping("/user/info/modify")
    /**
    * @Description: 修改用户信息
    * @Param: [token, userId, mail, avatar]
    * @return: java.util.Map
    * @Author: gerayking
    * @Date: 2021/6/5-14:40
    */
    public CommonResult modifyUserInfo(
        @RequestBody ModifyUserInfoDto modifyUserInfoDto
    ){
        final Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            return CommonResult.failFast(RESULT.USER_NOT_LOGIN);
        }
        User user = (User)subject.getPrincipal();
        Map<String, Object> result = new HashMap<>();
        if(!user.getUid().equals(modifyUserInfoDto.getUserId())){
            return CommonResult.failFast(RESULT.NO_PERMISSION,"无法修改其他用户信息");
        }
        String avatar = modifyUserInfoDto.getAvatar();
        if(avatar !=null) user.setAvatar(avatar);
        String mail = modifyUserInfoDto.getMail();
        if(mail != null) user.setMail(mail);
        boolean flag = userService.updateById(user);
        return CommonResult.success(flag);
    }
    @PostMapping("/user/info/password")
    /**
    * @Description: 修改用户密码
    * @Param: [uid, oldPassword, newPassword]
    * @return: java.util.Map
    * @Author: gerayking
    * @Date: 2021/6/5-14:40
    */
    public Map modifyPassword(
        @RequestBody ModifyPasswordDto modifyPasswordDto
    ){
        final Subject subject = SecurityUtils.getSubject();
        final User user = (User) subject.getPrincipal();
        Integer uid = modifyPasswordDto.getUid();
        String oldPassword = modifyPasswordDto.getOldPassword();
        String newPassword = modifyPasswordDto.getNewPassword();
        Map<String, Object> result = new HashMap<>();
        if(!user.getPassword().equals(oldPassword)){
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
    public Map logout(){
        Map result = new HashMap();
        final Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            result.put("result",false);
            result.put("info","未登录");
        }
        final User user = (User)subject.getPrincipal();
        boolean flag = shiroService.logout(user.getUid());
        result.put("result",flag);
        return result;
    }
    @PostMapping("/user/register")
    public Map userRegister(
        @RequestBody UserRegisterDto userRegisterDto
    ){
        Map<String, Object> result = new HashMap<>();
        final String phoneNumber = userRegisterDto.getPhoneNumber();
        final String password = userRegisterDto.getPassword();
        final String mail = userRegisterDto.getMail();
        final String name = userRegisterDto.getName();
        if(null == phoneNumber || null == password || null == mail){
            result.put("result",-1);
            result.put("info","参数错误");
            return result;
        }
        User user = userService.findByPhoneNumber(phoneNumber);
        if(user != null){
            result.put("result",-1);
            result.put("info","用户已存在");
            return result;
        }
        user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setMail(mail);
        user.setPhone_number(phoneNumber);
        Integer uid = userService.insertUser(user);
        result.put("result",uid);
        result.put("info","注册成功");
        return result;
    }
}
