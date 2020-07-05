package com.webtap.domain.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 用户
 * @author robotbird
 *
 */
@Entity
@Table(name = "wt_users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String passWord;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private Long createTime;
    @Column(name = "org_id")
    private Long orgId;
    @Transient
    private String strRole;

    @Transient
    private Long roleId;

    @Transient
    private List<Role> roleList;

    public User() {
        super();
    }

    public User(String email, String nickName, String passWord, String userName) {
        super();
        this.email = email;
        this.passWord = passWord;
        this.userName = userName;
    }

    public User(Object[] o) {
        super();
        this.id =  new Long(o[0].toString());;
        this.userName = o[1].toString();
        this.email = o[2].toString();
        this.createTime = new Long(o[3].toString());
        this.orgId = new Long(o[4].toString());
        this.strRole = o[5].toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getStrRole() {
        return strRole;
    }

    public void setStrRole(String strRole) {
        this.strRole = strRole;
    }

    public Long getRoleId() {

        if(roleId!=null){
            return roleId;
        }

        List<Role> roles = this.getRoleList();
        if(roles!=null){
            return roles.get(0).getId();
        }
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}