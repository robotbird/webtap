package com.webtap.domain.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "wt_organizations")
public class Organization implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name = "org_name")
	private String orgName;

	@Column(name = "org_logo")
	private String orgLogo;

	@Column(name = "short_url")
	private String shortUrl;



  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }


  public String getOrgLogo() {
    return orgLogo;
  }

  public void setOrgLogo(String orgLogo) {
    this.orgLogo = orgLogo;
  }


  public String getShortUrl() {
    return shortUrl;
  }

  public void setShortUrl(String shortUrl) {
    this.shortUrl = shortUrl;
  }

}
