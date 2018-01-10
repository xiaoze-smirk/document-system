package edu.fjnu.utils;

import edu.fjnu.entity.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public String dateToStr(Date date){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(date);
    }

    public Date strToDate(String str){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sf.parse(str);
            return date;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String timeToStr(Date date){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(date);
    }

    public Date strToTime(String str){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sf.parse(str);
            return date;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String longTimeToStr(Date date){
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sf.format(date);
    }

    /***
     * 保存文件
     * @param file
     * @return
     */
    private void saveFile(String path , MultipartFile file) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 文件保存路径
                String fileName = file.getOriginalFilename();
                String filePath = path + fileName;
                System.out.println(file.getOriginalFilename());

                File file1 = new File(filePath);
                // 检测是否存在目录
                if (!file1.getParentFile().exists()) {
                    file1.getParentFile().mkdirs();// 新建文件夹
                }

                if (file1.exists()) {
                    String suffixName = fileName.substring(fileName.lastIndexOf("."));
                    String filePath1 = path + longTimeToStr(new Date()) + suffixName;
                    file1.renameTo(new File(filePath1));
                }

                file.transferTo(new File(filePath));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public Document docm(MultipartFile[] files, Document document){
        String path ;
        for(int i=0;i<files.length;i++){
            path="F:/xiaoze/abc/";
            if(i==0&&!files[i].isEmpty()) {
                path = path + "测试计划/" + document.getTestJh() + "/";
                saveFile(path , files[i]);
                path=path+files[i].getOriginalFilename();
                document.setTestJhPath(path);
            }else if(i==1&&!files[i].isEmpty()) {
                path = path + "测试用例/" + document.getTestYl() + "/";
                saveFile(path , files[i]);
                path=path+files[i].getOriginalFilename();
                document.setTestYlPath(path);
            }else if(i==2&&!files[i].isEmpty()) {
                path = path + "测试记录/" + document.getTestJl() + "/";
                saveFile(path , files[i]);
                path=path+files[i].getOriginalFilename();
                document.setTestJlPath(path);
            }else if(i==3&&!files[i].isEmpty()) {
                path = path + "缺陷报告/" + document.getTestQx() + "/";
                saveFile(path , files[i]);
                path=path+files[i].getOriginalFilename();
                document.setTestQxPath(path);
            }else if(i==4&&!files[i].isEmpty()) {
                path = path + "测试报告/" + document.getTestBg() + "/";
                saveFile(path , files[i]);
                path=path+files[i].getOriginalFilename();
                document.setTestBgPath(path);
            }

        }
        return document;
    }

}
