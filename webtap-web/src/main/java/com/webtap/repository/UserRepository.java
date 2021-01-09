package com.webtap.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.webtap.domain.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    User findByUserNameOrEmail(String username, String email);
    
    User findByEmail(String email);


    @Query("delete from User  where id=:id")
    void delete(@Param("id") Long id);

    @Query(value = "SELECT u.id,u.user_name,u.email,u.create_time,u.org_id,r.description as strRole \n" +//group_concat(r.description)
            "FROM wt_users u \n" +
            "LEFT JOIN wt_user_role ur on u.id = ur.user_id \n" +
            "LEFT JOIN wt_roles r on r.id = ur.role_id \n" +
            "WHERE u.org_id =:orgId " ,nativeQuery = true)
    List<Object []> findObjectsByOrgId(@Param("orgId") Long orgId);


    List<User> findAllByOrgId(Long orgId);


//    @Query(value = "SELECT u.id,u.user_name,u.email,u.introduction,u.background_picture,u.create_time,u.last_modify_time,u.org_id,u.out_date,u.pass_word,u.profile_picture,u.user_type,u.validata_code,group_concat(r.description) as role \n" +
//            "FROM wt_users u \n" +
//            "LEFT JOIN wt_user_role ur on u.id = ur.user_id \n" +
//            "LEFT JOIN wt_roles r on r.id = ur.role_id \n" +
//            "WHERE u.org_id =:orgId " +
//            "GROUP BY u.id",nativeQuery = true)
//    List<UserVO> findAllByOrgId2(@Param("orgId") Long orgId);


    @Query("SELECT u.id,u.userName,u.email,u.createTime,u.passWord,u.orgId,'admin' as strRole " +
            "FROM User u  " +
            "WHERE u.orgId =:orgId ")
    List<User> findAllByOrgId3(@Param("orgId") Long orgId);


//                "LEFT JOIN UserRole ur on u.id = ur.userId " +
//                        "LEFT JOIN Role r on r.id = ur.roleId " +

    @Query("SELECT s.id,s.userName " +
            " FROM User s, UserRole ur " +
            " WHERE ur.userId = s.id and ur.roleId = :roleId")
    List<User> findAllByRoleId(@Param("roleId") Long roleId);

    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update User set outDate=:outDate, validataCode=:validataCode where email=:email")
    int setOutDateAndValidataCode(@Param("outDate") String outDate, @Param("validataCode") String validataCode, @Param("email") String email);
    
    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update User set passWord=:passWord where email=:email")
    int setNewPassword(@Param("passWord") String passWord, @Param("email") String email);

    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update User set passWord=:passWord where userName=:userName")
    int updatePassword(@Param("passWord") String passWord, @Param("userName") String userName);
    
    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update User set introduction=:introduction where email=:email")
    int setIntroduction(@Param("introduction") String introduction, @Param("email") String email);
    
    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update User set userName=:userName where email=:email")
    int setUserName(@Param("userName") String userName, @Param("email") String email);
    
    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update User set profilePicture=:profilePicture where id=:id")
    int setProfilePicture(@Param("profilePicture") String profilePicture, @Param("id") Long id);

//    @Query("from User u where u.name=:name")
//    User findUser(@Param("name") String name);

    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update User set backgroundPicture=:backgroundPicture where id=:id")
    int setBackgroundPicture(@Param("backgroundPicture") String backgroundPicture, @Param("id") Long id);

    void deleteAllByOrOrgId(Long orgId);
}