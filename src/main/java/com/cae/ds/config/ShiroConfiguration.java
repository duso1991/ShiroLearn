package com.cae.ds.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cae.ds.shiro.MyRealm;  

/**   
  * @Title: ShiroConfiguration.java
  * @Description:
  * @Company  电子科技大学自动化研究所
  * @author  杜松   
  * @date 2017年12月4日 上午11:03:31
  * @version V1.0   
*/

@Configuration
public class ShiroConfiguration {
	
	/**
	 * 
	  *@Description:定义我们登录、登出、失败、拦截校验的url
	  *@param securityManager
	  *@return 
	  *@author  杜松   
	  *@date 2017年12月4日 上午11:30:05
	 */
	@Bean  
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){  
       
       ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();  
        
       shiroFilterFactoryBean.setSecurityManager(securityManager);  
 
       Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();  
        
     
       filterChainDefinitionMap.put("/logout", "logout");  
        
       //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;  
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->  
       
     //配置记住我或认证通过可以访问的地址
       filterChainDefinitionMap.put("/index", "user");
       filterChainDefinitionMap.put("/", "user");
       
        filterChainDefinitionMap.put("/img/*", "anon");
        filterChainDefinitionMap.put("/**", "authc");  
        shiroFilterFactoryBean.setLoginUrl("/login");  
        // 登录成功后要跳转的链接  
        shiroFilterFactoryBean.setSuccessUrl("/index");  
        //未授权界面;  
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");  
        
       shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);  
       return shiroFilterFactoryBean;  
    }  

	
	@Bean  
    public SecurityManager securityManager(){  
       DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager(); 
       securityManager.setRealm(getMyRealm());
       securityManager.setRememberMeManager(getRememberMeManager());
       return securityManager;  
    }  
	
	
	@Bean
	public MyRealm getMyRealm(){
		MyRealm myRealm=new MyRealm();
		myRealm.setCredentialsMatcher(getHashedCredentialsMatcher());
		myRealm.setCacheManager(getEhCacheManager());
		
		return myRealm;
		
	}
	
	@Bean
	public HashedCredentialsMatcher getHashedCredentialsMatcher(){
		HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("Md5");
		hashedCredentialsMatcher.setHashIterations(2);
		return hashedCredentialsMatcher;
	}
	
	 @Bean  
	    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(SecurityManager securityManager){  
	       AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();  
	       authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);  
	       return authorizationAttributeSourceAdvisor;  
	    }  
	
	 @Bean
	    public EhCacheManager getEhCacheManager(){
	  
	       EhCacheManager cacheManager = new EhCacheManager();
	       cacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
	       return cacheManager;
	    }
	 
	 @Bean
	    public SimpleCookie getRememberMeCookie(){
	      
	       //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
	       SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
	       //<!-- 记住我cookie生效时间30天 ,单位秒;-->
	       simpleCookie.setMaxAge(259200);
	       return simpleCookie;
	    }
	 
	 
	    @Bean
	    public CookieRememberMeManager getRememberMeManager(){
	       System.out.println("ShiroConfiguration.rememberMeManager()");
	       CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
	       cookieRememberMeManager.setCookie(getRememberMeCookie());
	       return cookieRememberMeManager;
	    }
}
