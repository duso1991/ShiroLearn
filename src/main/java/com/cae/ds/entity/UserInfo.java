package com.cae.ds.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


/**   
  * @Title: SysUserInfo.java
  * @Description:
  * @Company  电子科技大学自动化研究所
  * @author  杜松   
  * @date 2017年12月3日 下午8:29:41
  * @version V1.0   
*/

@Entity
public class UserInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id@GeneratedValue
	private long uid;//用户ID
	@Column(unique=true)
	private String username;//用户名,如手机号，邮箱等
	private String name;//昵称，真实姓名等
	private String salt;//盐
	private String password;//密码
	private byte state;//账户状态，待认证，正常，锁定
	
	//建立中间表
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="SysUserRole",joinColumns={@JoinColumn(name="uid")},inverseJoinColumns={@JoinColumn(name="roleId")})
	private List<SysRole> roles;// 一个用户具有多个角色
	
	
	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public List<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 密码盐.
	 * @return
	 */
	public String getCredentialsSalt(){
		return this.username+this.salt;
	}
	
	@Override
	public String toString() {
		return "UserInfo [uid=" + uid + ", username=" + username + ", name=" + name + ", password=" + password
				+ ", salt=" + salt + ", state=" + state + "]";
	}
	
	

}
