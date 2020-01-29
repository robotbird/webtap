package com.webtap.service;

import com.webtap.domain.Apps;
import com.webtap.domain.Group;

import java.util.List;

public interface GroupService {

	public Group getGroupByShortUrl(String shortUrl);
	public Group getGroupById(Long Id);
}
