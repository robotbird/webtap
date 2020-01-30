package com.webtap.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "wt_app_category")
public class AppCategory  implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(name = "org_id")
	private Long orgId;



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


  public Long getOrgId() {
    return orgId;
  }

  public void setOrgId(Long orgId) {
    this.orgId = orgId;
  }

}
