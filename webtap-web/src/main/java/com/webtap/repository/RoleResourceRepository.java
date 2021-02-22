package com.webtap.repository;

import com.webtap.domain.entity.Role;
import com.webtap.domain.entity.RoleResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface RoleResourceRepository extends JpaRepository<RoleResource, Long> {


    @Modifying
    @Transactional
    @Query("delete from RoleResource where id=:id")
    void delete(@Param("id") Long id);
}