package com.webtap.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "wt_roles")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;

	private Long available;

	@Column(name = "create_time")
	private Long createTime;

	@Column(name = "update_time")
	private Long updateTime;

    @Column(name = "org_id")
    private Long orgId;

    public Role(){

    }
    public Role (Long id, String name, String description, Long available, Long createTime,Long updateTime,Long orgId){
      this.id = id;
      this.name = name;
      this.description = description;
      this.available = available;
      this.createTime = createTime;
      this.updateTime = updateTime;
      this.orgId = orgId;
    }

    @Transient
    private Integer selected;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public Long getAvailable() {
    return available;
  }

  public void setAvailable(Long available) {
    this.available = available;
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

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}
