package com.cae.ds.controller;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cae.ds.service.UserInfoService;

/**
 * @Title: HomeController.java
 * @Description:
 * @Company 电子科技大学自动化研究所
 * @author 杜松
 * @date 2017年12月3日 下午8:19:56
 * @version V1.0
 */
@Controller
public class HomeController {
	@Resource
	private UserInfoService userInfoService;
	
	
	@RequestMapping({ "/", "/index" })
	public String getIndex() {
		return "index";
	}
	
	 @RequestMapping(value="/login",method=RequestMethod.GET)
	    public String getLogin(){
	       return "login";
	    }
	
	 @RequestMapping(value="/userInfo",method=RequestMethod.GET)
	    public String getUserInfo(){
	       return "userInfo";
	    }
	
	
	
    @RequestMapping(value="/login",method=RequestMethod.POST)  
    public String login() throws Exception {  
      
       return "/login";  
    }  
    
   
    @RequestMapping("/userAdd")  
    @RequiresPermissions("userInfo:add")//权限管理;  
    public String userInfoAdd(){  
       return "userInfoAdd";  
    }  
    
    @RequestMapping("/userInfo")  
    @RequiresPermissions("userInfo:view")//权限管理;  
    public String userInfoA(){  
       return "userInfo";  
    }  

}
