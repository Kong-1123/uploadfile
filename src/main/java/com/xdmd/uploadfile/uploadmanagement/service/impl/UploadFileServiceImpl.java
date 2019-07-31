package com.xdmd.uploadfile.uploadmanagement.service.impl;

import com.xdmd.uploadfile.uploadmanagement.mapper.UploadMapper;
import com.xdmd.uploadfile.uploadmanagement.pojo.UploadFile;
import com.xdmd.uploadfile.uploadmanagement.service.UploadFileService;
import com.xdmd.uploadfile.uploadmanagement.uploadutil.FileSuffixJudge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 * @author: Kong
 * @createDate: 2019/07/31
 * @description: 上传业务实现
 */
public class UploadFileServiceImpl implements UploadFileService {
    @Autowired
    UploadMapper uploadMapper;
    @Override
    public int insertUpload(MultipartFile file,UploadFile uploadFile) throws Exception{
        // 获取文件名拼接当前系统时间作为新文件名
        String nowtime =  new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis());
        StringBuilder pinjiefileName=new StringBuilder(nowtime).append(file.getOriginalFilename());
        String fileName =pinjiefileName.toString();
        //获取文件类型
        String contentType = file.getContentType();
        //获取文件上传路径
        String filePath = "D:\\xdmd\\subject-1" ;
        File dest = new File(filePath + "\\" + fileName);

        // 获取文件大小
        File fileSize = new File(String.valueOf(dest));
        long length = fileSize.length();
        String valueOf = String.valueOf(length);
        uploadFile.setFileSize(valueOf);
        //获取文件后缀名
        String substring = fileName.substring(fileName.lastIndexOf(".") + 1);
        //判断文件的后缀名是否有误
        Boolean flag = FileSuffixJudge.suffixJudge(fileName);
        if(flag == false){
             throw new ExceptionInInitializerError("请上传正确的文件格式");
        }
        
        uploadFile.setUploadFilePath(String.valueOf(dest));

        return 0;
    }
}
