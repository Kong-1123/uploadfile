package com.xdmd.uploadfile.uploadmanagement.service;

import com.xdmd.uploadfile.uploadmanagement.pojo.UploadFile;
import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {
    /**
     * 上传
     * @param file
     * @param uploadFile
     * @return
     */
    int insertUpload(MultipartFile file, UploadFile uploadFile) throws Exception;
}
