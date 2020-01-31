package com.webtap.service.impl;

import com.webtap.domain.Asset;
import com.webtap.service.StorageService;
import com.webtap.utils.Pager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static com.webtap.utils.HtmlUtil.logger;

@Service("storageService")
public class StorageServiceImpl implements StorageService {

    String _separator = File.separator;

    @Value("${web.upload}")
    private String webUpload;


    public List<File> GetAssets(String path){
         path = path.replace("/",_separator);
         List<File> fileList = new ArrayList<>();
         try{
            File dir = new File(path);
            File[] files = dir.listFiles();
            if(files !=null){
                for(File file:files){
                    fileList.add(file);
                }
            }
         }catch (Exception ex){
             logger.error(ex.getMessage());
         }
         return fileList;
    }

    /**
     * get static path
     * @author robotbird@qq.com
     * @return
     */
    private String GetStaticPath(){
       String path = webUpload;
       String classpath = StorageServiceImpl.class.getClassLoader().getResource("").getPath();
       if(classpath.indexOf(".jar")>0){
           return path;
       }else {
           try {
               File file = new File(ResourceUtils.getURL("classpath:static").getPath());
               path = file.getAbsolutePath()+"/";
           } catch (FileNotFoundException e) {
               logger.error(e.getMessage());
           }
       }
       return path;
    }

    public List<Asset> Find(Pager pager, String path, String search) {
        //int skip = pager.CurrentPage * pager.ItemsPerPage - pager.ItemsPerPage;

        String localPath = "";
        if(path ==""){
            localPath = GetStaticPath();
        }else {
            localPath = GetStaticPath() +path;
        }
        List<File> fileList = GetAssets(localPath);
        List<Asset> assetList = new ArrayList<>();

        for (File file:fileList){
            Asset asset = new Asset();
            String filePath = file.getPath();
            int indexPath = filePath.indexOf(path.replace("/",_separator));
            String logoPath = filePath.substring(indexPath).replace(_separator,"/");
            asset.setTitle(file.getName());
            asset.setPath(logoPath);
            asset.setUrl(logoPath);
            asset.setImage(logoPath);
            assetList.add(asset);
        }
        return assetList;
    }
}
