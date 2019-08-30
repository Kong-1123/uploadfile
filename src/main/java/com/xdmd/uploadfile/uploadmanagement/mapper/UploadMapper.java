package com.xdmd.uploadfile.uploadmanagement.mapper;

import com.xdmd.uploadfile.uploadmanagement.pojo.UploadFile;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

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
            "#{uploadFileAddress},"+
            "#{uploadFileName},"+
            "#{uploadFileType},"+
            "#{uploadSuffixName},"+
            "#{fileSize},"+
            "NOW(),"+
            "#{createAuthor})")
    int insertUpload(UploadFile uploadFile);

    /**
     * 获取文件路径和文件名
     * @param id
     * @return
     */
    @Select("SELECT\n" +
            "uf.id,\n" +
            "uf.upload_file_name,\n" +
            "uf.upload_file_address\n" +
            "FROM\n" +
            "upload_file uf,\n" +
            "open_tender ot\n" +
            "WHERE\n" +
            "uf.id in(\n" +
            "(select uf.id from open_tender ot,upload_file uf where uf.id=ot.winning_file_attachment_id and ot.id=#{id}),\n" +
            "(select uf.id from open_tender ot,upload_file uf where uf.id=ot.announcement_transaction_announcement_id and ot.id=#{id),\n" +
            "(select uf.id from open_tender ot,upload_file uf where uf.id=ot.deal_notification_attachment_id and ot.id=#{id),\n" +
            "(select uf.id from open_tender ot,upload_file uf where uf.id=ot.response_file_attachment_id and ot.id=#{id)\n" +
            ")")
    HashMap getfileInfo(@Param("id") int id);
}
