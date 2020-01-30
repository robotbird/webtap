package com.webtap.repository;

import com.webtap.domain.Apps;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface AppsRepository extends JpaRepository<Apps, Long> {


	Apps findById(Long id);

	List<Apps> findAllByOrgId(Long groupId);

	@Transactional
    void deleteById(Long Id);

}