package com.webtap.repository;

import com.webtap.domain.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface AppRepository extends JpaRepository<App, Long> {

	List<App> findAllByOrgId(Long groupId);

	List<App> findAllByCategoryId(Long categoryId);

	List<App> findAllByTitleContains(String title);

	List<App> findAllByTitleContainingAndCategoryId(String title,Long id);

	App findAppByShortUrl(String shortUrl);

	@Query("select max(id) from App ")
	Long findMaxId();

	@Query("select url from App where id=:id and viewPassword=:password")
	String findAppUrlByIdAndPassword(@Param("id") Long id,@Param("password") String password);


    @Modifying(clearAutomatically=true)
    @Transactional
	@Query("update App set viewPassword=:password where id=:id")
	void updateViewPassword(@Param("id") Long id,@Param("password") String password);

}