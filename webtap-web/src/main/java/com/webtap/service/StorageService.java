package com.webtap.service;

import com.webtap.domain.entity.Asset;
import com.webtap.utils.Pager;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface StorageService {


    /**
     * get files by path
     * @author robotbird@qq.com
     * @param path
     * @return files
     */
    List<File> GetAssets(String path);

    /**
     * get files with pager
     * @author robotbird@qq.com
     * @param pager
     * @param path
     * @param search
     * @return
     */
    List<Asset> Find(Pager pager, String path, String search);

    /**
     * upload file
     * @author robotbird@qq.com
     * @param file
     * @param path
     * @return
     */
    Asset UploadFormFile(MultipartFile file, String path);

    /**
     * delete file by file url
     * @author robotbird@qq.com
     * @param url
     */
    void DeleteFile(String url);
}
