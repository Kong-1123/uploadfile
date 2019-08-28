package com.xdmd.uploadfile.uploadmanagement.mapper;

import com.xdmd.uploadfile.uploadmanagement.pojo.UploadFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

/**
 * @author: Kong
 * @createDate: 2019/07/31
 * @description: 保存文件到数据库
 */
@Mapper
@Repository
public interface UploadMapper {
    /**
     * 上传文件sql
     * @param uploadFile
     * @return
     */
    @Options(useGeneratedKeys=true,keyProperty = "id",keyColumn = "id")
    @Insert(value = "INSERT INTO upload_file (upload_file_address,upload_file_name, upload_file_type,upload_suffix_name,file_size,create_time,create_author )\n" +
            "VALUES(" +
            "#{uploadFilePath},"+
            "#{uploadFileName},"+
            "#{uploadFileType},"+
            "#{uploadSuffixName},"+
            "#{fileSize},"+
            "NOW(),"+
            "#{createAuthor})")
    int insertUpload(UploadFile uploadFile);
}
