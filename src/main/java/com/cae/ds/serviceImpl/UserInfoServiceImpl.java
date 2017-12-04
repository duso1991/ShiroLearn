package com.cae.ds.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cae.ds.dao.UserInfoRepository;
import com.cae.ds.entity.UserInfo;
import com.cae.ds.service.UserInfoService;

/**   
  * @Title: UserInfoService.java
  * @Description:
  * @Company  电子科技大学自动化研究所
  * @author  杜松   
  * @date 2017年12月4日 下午3:42:20
  * @version V1.0   
*/
@Service
public class UserInfoServiceImpl implements UserInfoService{
	@Resource
	private UserInfoRepository userInfoRepository;
	
	@Override
	public UserInfo findByUsername(String username){
		return userInfoRepository.findByUsername(username);
	}

}
