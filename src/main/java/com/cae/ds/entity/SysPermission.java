package com.cae.ds.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class SysPermission implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id@GeneratedValue
	private long id;//ID
	private String name;//权限名称
	@Column(columnDefinition="enum('menu','button')")
	private String resourceType;//资源类型
	private String url;//资源路径
	private String parentIds;//父编号列表
	private Long parentId;//父编号
	private String permission;//权限
	
	private Boolean available=Boolean.FALSE;//可用性
	
	@ManyToMany
	@JoinTable(name="SysRolePermission",joinColumns={@JoinColumn(name="permissionId")},inverseJoinColumns={@JoinColumn(name="roleId")})
	private List<SysRole> roles;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getParentIds() {
		return parentIds;
	}
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	public List<SysRole> getRoles() {
		return roles;
	}
	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "SysPermission [id=" + id + ", name=" + name + ", resourceType=" + resourceType + ", url=" + url
				+ ", parentIds=" + parentIds + ", parentId=" + parentId + ", permission=" + permission + ", available="
				+ available + ", roles=" + roles + "]";
	}
	
	
	
}
