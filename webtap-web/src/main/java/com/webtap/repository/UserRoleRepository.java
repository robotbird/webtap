package com.webtap.repository;

import com.webtap.domain.entity.Role;
import com.webtap.domain.entity.User;
import com.webtap.domain.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    List<UserRole> findAllByUserId(Long userId);



    @Modifying
    @Transactional
    @Query("delete from UserRole where id=:id")
    void delete(@Param("id") Long id);
}