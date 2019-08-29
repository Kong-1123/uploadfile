package com.xdmd.uploadfile.uploadmanagement.service.impl;

import com.xdmd.uploadfile.uploadmanagement.pojo.UploadFile;
import com.xdmd.uploadfile.uploadmanagement.uploadutil.FileSuffixJudge;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 * @author: Kong
 * @createDate: 2019/07/31
 * @description: 上传业务实现
 */
@Service
public class UploadFileServiceImpl {
   // @Autowired
   // UploadMapper uploadMapper;
    public String insertUpload(MultipartFile file) throws Exception{
        UploadFile uploadFile=new UploadFile();
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
        File StrValueOf = new File(String.valueOf(dest));
        String fileSize = String.valueOf(StrValueOf.length());

        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1);
        //判断文件的后缀名是否有误
        Boolean flag = FileSuffixJudge.suffixJudge(fileName);
        if(flag == false){
             throw new ExceptionInInitializerError("请上传正确的文件格式");
        }

        //将获取到的上传文件属性保封装到uploadFile
       // UploadFile uploadFile = new UploadFile(0, filePath, fileName, "合同附件", contentType, fileSize, null, "提交者");

        // return uploadMapper.insertUpload(uploadFile);
        return null;
    }
}
