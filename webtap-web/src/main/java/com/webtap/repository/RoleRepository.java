package com.webtap.repository;

import com.webtap.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {


    @Query("delete from Role  where id=:id")
    void delete(Long id);
    @Query(" SELECT r.id,  r.name, r.description,  13 AS selected " +
            "FROM  Role r  WHERE r.available = 1")
    List<Role> queryRoleListWithSelected();

    @Query(value = "SELECT r.id,r.name,r.available,r.description,r.create_time,r.update_time,r.org_id " +
            "FROM wt_roles r ,wt_user_role ur  WHERE  ur.role_id = r.id and ur.user_id = :userId",nativeQuery = true)
    List<Role> findRolesByUserId(@Param("userId") Long userId);
}