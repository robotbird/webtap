package com.webtap.repository;

import com.webtap.domain.App;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface AppRepository extends JpaRepository<App, Long> {

	List<App> findAllByOrgId(Long groupId);

}