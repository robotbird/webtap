package com.webtap.service.impl;

import com.webtap.domain.entity.Asset;
import com.webtap.service.StorageService;
import com.webtap.utils.Pager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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
           logger.info("53 classpath================="+classpath);
           int firstIndex = classpath.lastIndexOf(":") + 1;
           classpath = classpath.substring(0,classpath.lastIndexOf(".jar"));
           int lastIndex = classpath.lastIndexOf(File.separator) + 1;
           path = classpath.substring(firstIndex,lastIndex);
           logger.info("58 path====="+path);

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

        logger.info("localpath=============="+localPath);
        List<File> fileList = GetAssets(localPath);
        List<Asset> assetList = new ArrayList<>();

        for (File file:fileList){
            Asset asset = getAsset(file,path);
            assetList.add(asset);
        }
        return assetList;
    }
    private String getLogoPath(String filePath,String path){
        int indexPath = filePath.indexOf(path.replace("/",_separator));
        String logoPath = filePath.substring(indexPath).replace(_separator,"/");
        return logoPath;
    }

    private  Asset getAsset(File file,String path){
        Asset asset = new Asset();
        String logoPath = getLogoPath(file.getPath(),path);
        asset.setTitle(file.getName());
        asset.setPath(logoPath);
        asset.setUrl(logoPath);
        asset.setImage(logoPath);
        return asset;
    }

    public Asset UploadFormFile(MultipartFile file,String path) {
        try {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            logger.info("上传的文件名为：" + fileName);
            // 设置文件存储路径
            String localPath = GetStaticPath() +path;
            String filePath = localPath +_separator+ fileName;
            File uploadFile = new File(filePath);
            // 检测是否存在目录
            if (!uploadFile.getParentFile().exists()) {
                uploadFile.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(uploadFile);// 文件写入
            logger.info("upload file path ======"+uploadFile);

            Asset asset = getAsset(uploadFile,path);
            return asset;

        } catch (IllegalStateException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public void DeleteFile(String url){
           String filePath = GetStaticPath()+url;
           filePath = filePath.replace("/",_separator);
           File file = new File(filePath);
           try{
               file.delete();
           } catch (IllegalStateException e){
               logger.error(e.getMessage());
           }
    }
}
