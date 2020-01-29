package com.webtap.service.impl;

import com.webtap.domain.Asset;
import com.webtap.service.StorageService;
import com.webtap.utils.Pager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("storageService")
public class StorageServiceImpl implements StorageService {


    public List<Asset> Find(Pager pager, String path, String search) {

        List<Asset> assetList = new ArrayList<>();
        Asset asset = new Asset();
        asset.setTitle("Title");
        asset.setPath("Path");
        asset.setUrl("Url");
        asset.setImage("upload/logo/openticket.png");
        assetList.add(asset);
        return assetList;
    }
}
