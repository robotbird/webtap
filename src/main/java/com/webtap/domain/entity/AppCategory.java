package com.webtap.domain.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "wt_app_category")
public class AppCategory  implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;

	private String name;

	@Column(name = "org_id")
	private Long orgId;

    @Column(name = "user_id")
    private Long userId;


    @Column(name = "app_amount")
	private Long appAmount;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAppAmount() {
        return appAmount;
    }

    public void setAppAmount(Long appAmount) {
        this.appAmount = appAmount;
    }

}
