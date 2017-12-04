package com.cae.ds.dao;

import org.springframework.data.repository.CrudRepository;

import com.cae.ds.entity.UserInfo;

/**   
  * @Title: UserInfoRepository.java
  * @Description:
  * @Company  电子科技大学自动化研究所
  * @author  杜松   
  * @date 2017年12月4日 下午3:40:26
  * @version V1.0   
*/
public interface UserInfoRepository extends CrudRepository<UserInfo,Long>{
	
	public UserInfo findByUsername(String username);

}
