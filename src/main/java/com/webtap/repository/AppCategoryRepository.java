package com.webtap.repository;

import com.webtap.domain.entity.AppCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface AppCategoryRepository extends JpaRepository<AppCategory, Long> {

    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update AppCategory c set  c.appAmount=(select count (*) from App a where a.categoryId=:id) where c.id=:id")
    void updateAppAmount(@Param("id") Long id);

    List<AppCategory> findAllByOrgId(Long orgid);
}