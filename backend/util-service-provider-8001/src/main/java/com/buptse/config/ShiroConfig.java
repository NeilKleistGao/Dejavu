package com.buptse.config;

import com.buptse.auth.AuthFilter;
import com.buptse.auth.AuthRealm;
import com.buptse.security.UserRealm;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.Filter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class ShiroConfig {

  @Bean
  public SecurityManager securityManager(AuthRealm authRealm) {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    securityManager.setRealm(authRealm);
    securityManager.setRememberMeManager(null);
    return securityManager;
  }
  @Bean
  public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
    ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
    shiroFilter.setSecurityManager(securityManager);
    //auth过滤
    Map<String, Filter> filters = new HashMap<>();
    filters.put("auth", new AuthFilter());
    shiroFilter.setFilters(filters);
    Map<String, String> filterMap = new LinkedHashMap<>();
    // anno匿名访问  auth验证
    filterMap.put("/webjars/**", "anon");
    filterMap.put("/druid/**", "anon");
    filterMap.put("/user/login", "anon");
    filterMap.put("/swagger/**", "anon");
    filterMap.put("/v2/api-docs", "anon");
    filterMap.put("/user/register","anon");
    filterMap.put("/swagger-ui.html", "anon");
    filterMap.put("/swagger-resources/**", "anon");
    filterMap.put("/doc.html", "anon");
    // 除了以上路径，其他都需要权限验证
    filterMap.put("/**", "auth");
    shiroFilter.setLoginUrl("/user/login");
    shiroFilter.setFilterChainDefinitionMap(filterMap);
    return shiroFilter;
  }
  @Bean
  public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    return new LifecycleBeanPostProcessor();
  }
  @Bean
  public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
    return new DefaultAdvisorAutoProxyCreator();
  }

  @Bean
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
    advisor.setSecurityManager(securityManager);
    return advisor;
  }
}
