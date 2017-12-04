package com.cae.ds.shiro;


import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.cae.ds.entity.SysPermission;
import com.cae.ds.entity.SysRole;
import com.cae.ds.entity.UserInfo;
import com.cae.ds.service.UserInfoService;  
   

/**   
 * @Title: MyRealm.java
 * @Description:身份验证核心类
 * @Company  电子科技大学自动化研究所
 * @author  杜松   
 * @date 2017年12月4日 下午4:54:50
 * @version V1.0   
*/  
public class MyRealm extends AuthorizingRealm{  
   
         
    @Resource  
    private UserInfoService userInfoService;  
     
    /**
     * 
      *@Description:身份认证
      *@param token
      *@return
      *@throws AuthenticationException
      *@author  杜松   
      *@date 2017年12月4日 下午4:58:21
     */
    @Override  
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {  
         

       String username = (String)token.getPrincipal();  
       
       UserInfo userInfo = userInfoService.findByUsername(username);  
      System.out.println(userInfo);
       if(userInfo == null){  
           return null;  
       }  
       SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(  
    		   userInfo, userInfo.getPassword(),  
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),
                getName()
        );  
        
       
       return authenticationInfo;  
    }  
     
     
     
   /**
    * 
     *@Description:授权
     *@param principals
     *@return
     *@author  杜松   
     *@date 2017年12月4日 下午6:58:05
    */
    @Override  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {  
      
    	
       SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();  
       UserInfo userInfo  = (UserInfo)principals.getPrimaryPrincipal();  

       for(SysRole role:userInfo.getRoles()){  
           authorizationInfo.addRole(role.getRole());  
           
           for(SysPermission p:role.getPermissions()){  
              authorizationInfo.addStringPermission(p.getPermission());  
              
           }  
       }       
       return authorizationInfo;  
    }  
   
}  