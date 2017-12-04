package com.cae.ds.service;

import com.cae.ds.entity.UserInfo;

/**   
  * @Title: UserInfoService.java
  * @Description:
  * @Company  电子科技大学自动化研究所
  * @author  杜松   
  * @date 2017年12月4日 下午3:42:20
  * @version V1.0   
*/
public interface UserInfoService {
	
	public UserInfo findByUsername(String username); 

}
