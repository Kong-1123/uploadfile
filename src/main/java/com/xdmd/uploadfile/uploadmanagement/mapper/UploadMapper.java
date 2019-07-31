package com.xdmd.uploadfile.uploadmanagement.mapper;

import com.xdmd.uploadfile.uploadmanagement.pojo.UploadFile;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author: Kong
 * @createDate: 2019/07/31
 * @description: 保存文件到数据库
 */
@Repository
public interface UploadMapper {
    /**
     *
     * @param uploadFile
     * @return
     */
    @Select(value = "INSERT INTO upload_file (upload_file_path,upload_file_name, upload_file_type,upload_suffix_name,file_size,create_time,create_author )\n" +
            "VALUES\n" +
            "#{uploadFilePath}"+
            "#{uploadFileName}"+
            "#{uploadFileType}"+
            "#{uploadSuffixName}"+
            "#{fileSize}"+
            "NOW()"+
            "#{createAuthor})")
    int insertUpload(UploadFile uploadFile);
}
