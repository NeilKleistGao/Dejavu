package com.buptse.auth;

import com.buptse.auth.util.TokenUtil;
import com.buptse.pojo.User;
import com.buptse.pojo.UserRole;
import com.buptse.pojo.UserToken;
import com.buptse.service.IShiroService;
import com.buptse.service.IUserRoleService;
import com.buptse.service.IUserService;
import com.buptse.service.impl.ShiroServiceImpl;
import com.buptse.service.impl.UserRoleService;
import com.buptse.service.impl.UserServiceImpl;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthRealm extends AuthorizingRealm {

  @Autowired
  IUserRoleService userRoleService;

  @Autowired
  IShiroService shiroService;

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
     User user = (User)principalCollection.getPrimaryPrincipal();
    final SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
    List<UserRole> userRole = userRoleService.findUserRole(user);
    simpleAuthorizationInfo.addRole("user");
    for (UserRole role : userRole) {
      simpleAuthorizationInfo.addRole(role.getRole());
    }
    return simpleAuthorizationInfo;
  }
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
      throws AuthenticationException {
    final String token = (String) authenticationToken.getPrincipal();
    final UserToken userToken = shiroService.findByToken(token);
    if(userToken == null || !token.equals(userToken.getToken())){
      throw new IncorrectCredentialsException("Token 失效，请重新登录");
    }
    final User user = shiroService.findByUserId(userToken.getUserId());
    if(user == null){
      throw new UnknownAccountException("用户已被注销");
    }
    final SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,
        token, this.getName());
    return info;
  }
}
