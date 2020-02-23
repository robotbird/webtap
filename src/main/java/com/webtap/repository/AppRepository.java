package com.webtap.repository;

import com.webtap.domain.App;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AppRepository extends JpaRepository<App, Long> {

	List<App> findAllByOrgId(Long groupId);

	List<App> findAllByCategoryId(Long categoryId);

	List<App> findAllByTitleContains(String title);

	List<App> findAllByTitleContainingAndCategoryId(String title,Long id);

}