package com.webtap.repository;

import com.webtap.domain.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

	/**
	 * 跟进短链接获取组织信息
	 * @param shortUrl
	 * @return
	 */
	Organization findByShortUrl(String shortUrl);

	@Transactional
    void deleteById(Long Id);

}