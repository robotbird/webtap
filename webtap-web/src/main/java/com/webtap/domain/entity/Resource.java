package com.webtap.domain.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author robotbird
 * @version 1.0
 * @website http://webtap.cn
 * @date 2020-04-25 22:32
 **/
@Entity
@Table(name = "wt_resources")
public class Resource implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String type;

	private String url;

	private String permission;

	@Column(name = "parent_id")
	private Long parentId;

	private Long sort;

	private Long external;

	private Long available;

	private String icon;

	@Column(name = "create_time")
	private Long createTime;

	@Column(name = "update_time")
	private Long updateTime;

    @Column(name = "org_id")
    private Long orgId;


  @Transient
  private String checked;
  @Transient
  private Resource parent;
  @Transient
  private List<Resource> nodes;


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


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  public String getPermission() {
    return permission;
  }

  public void setPermission(String permission) {
    this.permission = permission;
  }


  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }


  public Long getSort() {
    return sort;
  }

  public void setSort(Long sort) {
    this.sort = sort;
  }


  public Long getExternal() {
    return external;
  }

  public void setExternal(Long external) {
    this.external = external;
  }


  public Long getAvailable() {
    return available;
  }

  public void setAvailable(Long available) {
    this.available = available;
  }


  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
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

  public String getChecked() {
    return checked;
  }

  public void setChecked(String checked) {
    this.checked = checked;
  }

  public Resource getParent() {
    return parent;
  }

  public void setParent(Resource parent) {
    this.parent = parent;
  }

  public List<Resource> getNodes() {
    return nodes;
  }

  public void setNodes(List<Resource> nodes) {
    this.nodes = nodes;
  }

  public Long getOrgId() {
    return orgId;
  }

  public void setOrgId(Long orgId) {
    this.orgId = orgId;
  }

}
