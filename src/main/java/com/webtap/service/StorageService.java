package com.webtap.service;

import com.webtap.domain.Asset;
import com.webtap.utils.Pager;

import java.util.List;

public interface StorageService {
         public List<Asset> Find(Pager pager,String path,String search);
}
