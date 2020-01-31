package com.webtap.repository;

import com.webtap.domain.Apps;
import com.webtap.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    /**
     * 跟进短链接获取组织信息
     *
     * @param shortUrl
     * @return
     */
    Group findByShortUrl(String shortUrl);

}