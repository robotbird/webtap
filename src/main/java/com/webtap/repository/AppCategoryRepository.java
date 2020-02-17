package com.webtap.repository;

import com.webtap.domain.AppCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface AppCategoryRepository extends JpaRepository<AppCategory, Long> {
}