package com.webtap.repository;

import com.webtap.domain.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    @Query("         SELECT" +
            "            re.id," +
            "            re.name," +
            "            re.parent_id," +
            "            re.url," +
            "            re.permission," +
            "            re.icon," +
            "            node.id AS node_id," +
            "            node.name AS node_name," +
            "            node.type AS node_type," +
            "            node.url AS node_url," +
            "            node.parent_id AS node_parent_id," +
            "            node.permission AS node_permission," +
            "            node.available AS node_available," +
            "            node.icon AS node_icon" +
            "        FROM" +
            "            Resource re" +
            "        LEFT JOIN RoleResource rr ON re.id = rr.resourcesId" +
            "        LEFT JOIN UserRole ur ON rr.roleId = ur.roleId" +
            "        LEFT JOIN Resource node ON node.parentId = re.id AND node.available = 1" +
            "        WHERE" +
            "            (re.parentId = 0 OR re.parentId IS NULL )" +
            "        AND re.available = 1" +
            "       AND ur.userId = :userId" +
            "        ORDER BY" +
            "            re.sort ASC," +
            "            node.sort ASC")
    List<Resource> findResourceByUserId (Long userId);

}