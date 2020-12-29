package com.webtap.domain.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "wt_role_resource")
public class RoleResource implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name = "role_id")
	private Long roleId;

	@Column(name = "resources_id")
	private Long resourcesId;

	@Column(name = "create_time")
	private Long createTime;

	@Column(name = "update_time")
	private Long updateTime;



  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }


  public Long getResourcesId() {
    return resourcesId;
  }

  public void setResourcesId(Long resourcesId) {
    this.resourcesId = resourcesId;
  }


  public Long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }


  public Long getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Long updateTime) {
    this.updateTime = updateTime;
  }

}
