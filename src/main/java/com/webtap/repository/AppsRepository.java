package com.webtap.repository;

import com.webtap.domain.Apps;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface AppsRepository extends JpaRepository<Apps, Long> {


    List<Apps> findAllByGroupId(Long groupId);

}