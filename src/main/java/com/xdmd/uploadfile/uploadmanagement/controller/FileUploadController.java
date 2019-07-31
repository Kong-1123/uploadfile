package com.xdmd.uploadfile.uploadmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author: Kong
 * @createDate: 2019/07/31
 * @description: 文件上传
 */
@Controller
public class FileUploadController {
    /**
     * 获取file.html页面
     * @return
     */
    @RequestMapping("file")
    public String file(){
        return "/file";
    }

    /**
     * 实现单文件上传
     * */
    @RequestMapping("fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("fileName") MultipartFile file){
        if(file.isEmpty()){
            return "false";
        }

        // 获取文件名拼接当前系统时间作为新文件名
        String nowtime =  new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis());
        StringBuilder pinjiefileName=new StringBuilder(nowtime).append(file.getOriginalFilename());
        String fileName =pinjiefileName.toString();

        //获取文件上传路径
        String filePath = "D:\\xdmd\\subject-1" ;
        File dest = new File(filePath + "\\" + fileName);
        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            //保存文件
            file.transferTo(dest);
            return "上传成功";
        } catch (Exception e) {
             e.printStackTrace();
            return "上传失败";
        }
    }

    /**
     * 获取multifile.html页面
     * @return
     */
    @RequestMapping("multifile")
    public String multifiles(){
        return "/multifile";
    }

    /**
     * 实现多文件上传
     * @param request
     * @return
     */
    @PostMapping(value="multifileUpload")
    @ResponseBody
    public String multifileUpload(HttpServletRequest request){

        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("fileName");

        if(files.isEmpty()){
            return "false";
        }
        String filePath = "D:\\xdmd\\subject-1" ;

        for(MultipartFile file:files){
            // 获取文件名拼接当前系统时间作为新文件名
            String nowtime =  new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis());
            StringBuilder pinjiefileName=new StringBuilder(nowtime).append(file.getOriginalFilename());
            String fileName =pinjiefileName.toString();

            //判断是否为空
            if(file.isEmpty()){
                return "没有上传任何文件";
            }else{
                File dest = new File(filePath + "\\" + fileName);
                //判断文件父目录是否存在,不存在则创建
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                try {
                    file.transferTo(dest);
                }catch (Exception e) {
                    e.printStackTrace();
                    return "上传失败";
                }
            }
        }
        return "上传成功";
    }

    /**
     * 暂时用不到
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/download")
    public String downLoad(HttpServletResponse response) throws UnsupportedEncodingException {
        String filename="20190731160550liuyifei.jpg";
        String filePath = "D:\\xdmd\\subject-1";
        File file = new File(filePath + "\\" + filename);
        //判断文件父目录是否存在
         if(file.exists()){
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;fileName=" +   java.net.URLEncoder.encode(filename,"UTF-8"));
            byte[] buffer = new byte[1024];
             //文件输入流
            FileInputStream fis = null;
            BufferedInputStream bis = null;
             //输出流
            OutputStream os = null;
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("----------file download---" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }
}
