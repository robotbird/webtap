package com.webtap.service.impl;

import com.webtap.domain.Apps;
import com.webtap.domain.Group;
import com.webtap.repository.AppsRepository;
import com.webtap.repository.GroupRepository;
import com.webtap.service.AppsService;
import com.webtap.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("groupService")
public class GroupServiceImpl implements GroupService {
	protected Logger logger =  LoggerFactory.getLogger(this.getClass());

	@Autowired
   private GroupRepository groupRepository;

    public Group getGroupByShortUrl(String shortUrl) {
        Group group = groupRepository.findByShortUrl(shortUrl);
        return group;
    }

    public Group getGroupById(Long Id){
        return groupRepository.findById(Id);
    }


}