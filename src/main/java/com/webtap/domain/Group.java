package com.webtap.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "groups")
public class Group  implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name = "group_name")
	private String groupName;

	@Column(name = "group_logo")
	private String groupLogo;

	@Column(name = "short_url")
	private String shortUrl;



  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }


  public String getGroupLogo() {
    return groupLogo;
  }

  public void setGroupLogo(String groupLogo) {
    this.groupLogo = groupLogo;
  }


  public String getShortUrl() {
    return shortUrl;
  }

  public void setShortUrl(String shortUrl) {
    this.shortUrl = shortUrl;
  }

}
