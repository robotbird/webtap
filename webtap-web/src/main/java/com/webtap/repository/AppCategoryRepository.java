package com.webtap.repository;

import com.webtap.domain.entity.AppCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface AppCategoryRepository extends JpaRepository<AppCategory, Long> {


    @Query(value = "SELECT a.id,a.name,a.org_id,a.user_id,(SELECT count(*) FROM wt_apps b " +
            "WHERE b.category_id = a.id) as app_amount " +
            "FROM wt_app_category a WHERE a.org_id=?1 ",nativeQuery = true)
    List<AppCategory> findAllByOrgId(Long orgId);

    void deleteAllByOrgId(Long orgId);

    @Query("delete from AppCategory  where id=:id")
    void delete(Long id);
}