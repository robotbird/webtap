package com.webtap.repository;

import com.webtap.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(" SELECT r.id,  r.name, r.description, " +
            "( CASE WHEN (  SELECT ur.roleId  FROM UserRole ur WHERE  ur.user_id = :userId  AND  ur.roleId = r.id ) THEN 1 ELSE 0 END ) AS selected " +
            "FROM  Role r  WHERE r.available = 1")
    List<Role> queryRoleListWithSelected(@Param("userId") Long userId);


    @Query("SELECT r.id, r.name ,r.description FROM Role r INNER JOIN UserRole ur ON ur.roleId = r.id WHERE ur.userId = userId AND r.available = 1")
    List<Role> listRolesByUserId (Long userId);
}