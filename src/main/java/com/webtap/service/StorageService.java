package com.webtap.service;

import com.webtap.domain.Asset;
import com.webtap.utils.Pager;

import java.io.File;
import java.util.List;

public interface StorageService {


    /**
     * get files by path
     * @author robotbird@qq.com
     * @param path
     * @return files
     */
     public List<File> GetAssets(String path);

    /**
     * get files with pager
     * @author robotbird@qq.com
     * @param pager
     * @param path
     * @param search
     * @return
     */
     public List<Asset> Find(Pager pager,String path,String search);
}
