package edu.fjnu.utils;

import edu.fjnu.entity.Document;
import edu.fjnu.entity.Version;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

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

    public Version ver(MultipartFile[] files, Version version,String theDocNum){
        String path ;
        int j=0;
        for(int i=0;i<files.length;i++){
            path="F:/xiaoze/abc/";
            if(theDocNum.equals(version.getTestJh())&&!files[i].isEmpty()) {
                path = path + "测试计划/"+theDocNum+"/";
                saveFile(path , files[i]);
                path=path+files[i].getOriginalFilename();

                if(isNotEmpty(version.getTestJhPath())) {
                    String[] strArray=version.getTestJhPath().split("!");
                    for(j=0;j<strArray.length;j++){
                        if(strArray[j].equals(path))
                            break;
                    }
                    if(j==strArray.length)
                        version.setTestJhPath(version.getTestJhPath() + "!" + path);
                }
                else
                    version.setTestJhPath(path);
            }else if(theDocNum.equals(version.getTestYl())&&!files[i].isEmpty()) {
                path = path + "测试用例/"+theDocNum+"/" ;
                saveFile(path , files[i]);
                path=path+files[i].getOriginalFilename();
                if(isNotEmpty(version.getTestYlPath())) {
                    String[] strArray=version.getTestYlPath().split("!");
                    for(j=0;j<strArray.length;j++){
                        if(strArray[j].equals(path))
                            break;
                    }
                    if(j==strArray.length)
                        version.setTestYlPath(version.getTestYlPath() + "!" + path);
                }
                else
                    version.setTestYlPath(path);
            }else if(theDocNum.equals(version.getTestJl())&&!files[i].isEmpty()) {
                path = path + "测试记录/"+theDocNum+"/";
                saveFile(path , files[i]);
                path=path+files[i].getOriginalFilename();
                if(isNotEmpty(version.getTestJlPath())) {
                    String[] strArray=version.getTestJlPath().split("!");
                    for(j=0;j<strArray.length;j++){
                        if(strArray[j].equals(path))
                            break;
                    }
                    if(j==strArray.length)
                        version.setTestJlPath(version.getTestJlPath() + "!" + path);
                }
                else
                    version.setTestJlPath(path);
            }else if(theDocNum.equals(version.getTestQx())&&!files[i].isEmpty()) {
                path = path + "缺陷报告/"+theDocNum+"/";
                saveFile(path , files[i]);
                path=path+files[i].getOriginalFilename();
                if(isNotEmpty(version.getTestQxPath())) {
                    String[] strArray=version.getTestQxPath().split("!");
                    for(j=0;j<strArray.length;j++){
                        if(strArray[j].equals(path))
                            break;
                    }
                    if(j==strArray.length)
                        version.setTestQxPath(version.getTestQxPath() + "!" + path);
                }
                else
                    version.setTestQxPath(path);
            }else if(theDocNum.equals(version.getTestBg())&&!files[i].isEmpty()) {
                path = path + "测试报告/"+theDocNum+"/";
                saveFile(path , files[i]);
                path=path+files[i].getOriginalFilename();
                if(isNotEmpty(version.getTestBgPath())) {
                    String[] strArray=version.getTestBgPath().split("!");
                    for(j=0;j<strArray.length;j++){
                        if(strArray[j].equals(path))
                            break;
                    }
                    if(j==strArray.length)
                        version.setTestBgPath(version.getTestBgPath() + "!" + path);
                }
                else
                    version.setTestBgPath(path);
            }

        }
        return version;
    }

    public Integer getStateLength(String str){

        Integer length = 0;
        if(str.equals("a"))
            length=0;
        else if(str.equals("b"))
            length=16;
        else if(str.equals("c"))
            length=33;
        else if(str.equals("d"))
            length=50;
        else if(str.equals("e"))
            length=66;
        else if(str.equals("f"))
            length=83;
        else if(str.equals("g"))
            length=100;

        return length;

    }

    //去字符串最后一个字符串，即（文件名）
    public String lastStr(String str){
        String[] strArray = str.split("\\/");
        return strArray[strArray.length-1];
    }

    public Map<String, Object> map(Version version,String theDocNum){

        Map<String, Object> map = new HashMap<>();

        String[] stringArray = null;
        List<String> stringList = new ArrayList<>();
        if(version.getTestJh()!=null&&theDocNum.equals(version.getTestJh())) {
            if(version.getTestJhPath()!=null) {
                stringArray = version.getTestJhPath().split("!");
                for (String s : stringArray) {
                    stringList.add(lastStr(s));
                }
            }
            map.put("getFileName", "测试计划");
        }
        else if(version.getTestYl()!=null&&theDocNum.equals(version.getTestYl())){
            if(version.getTestYlPath()!=null) {
                stringArray = version.getTestYlPath().split("!");
                for (String s : stringArray) {
                    stringList.add(lastStr(s));
                }
            }
            map.put("getFileName", "测试用例");
        }
        else if(version.getTestJl()!=null&&theDocNum.equals(version.getTestJl())){
            if(version.getTestJlPath()!=null) {
                stringArray = version.getTestJlPath().split("!");
                for (String s : stringArray) {
                    stringList.add(lastStr(s));
                }
            }
            map.put("getFileName", "测试记录");
        }
        else if(version.getTestQx()!=null&&theDocNum.equals(version.getTestQx())){
            if(version.getTestQxPath()!=null) {
                stringArray = version.getTestQxPath().split("!");
                for (String s : stringArray) {
                    stringList.add(lastStr(s));
                }
            }
            map.put("getFileName", "缺陷报告");
        }
        else if(version.getTestBg()!=null&&theDocNum.equals(version.getTestBg())){
            if(version.getTestBgPath()!=null) {
                stringArray = version.getTestBgPath().split("!");
                for (String s : stringArray) {
                    stringList.add(lastStr(s));
                }
            }
            map.put("getFileName", "测试报告");
        }

        if(stringList!=null)
            map.put("stringList", stringList);
        return map;
    }

    public String getOneFilePath(Version version,String theDocNum,String fileName){
        String path=null;
        String[] strArray=null;
        if(version.getTestJh().equals(theDocNum)) {
            strArray = version.getTestJhPath().split("!");
            for(String str:strArray) {
                if (lastStr(str).equals(fileName)) {
                    path = str;
                    break;
                }
            }
        }
        else if (version.getTestYl().equals(theDocNum)) {
            strArray = version.getTestYlPath().split("!");
            for(String str:strArray) {
                if (lastStr(str).equals(fileName)) {
                    path = str;
                    break;
                }
            }
        }
        else if (version.getTestJl().equals(theDocNum)) {
            strArray = version.getTestJlPath().split("!");
            for(String str:strArray) {
                if (lastStr(str).equals(fileName)) {
                    path = str;
                    break;
                }
            }
        }
        else if (version.getTestQx().equals(theDocNum)) {
            strArray = version.getTestQxPath().split("!");
            for(String str:strArray) {
                if (lastStr(str).equals(fileName)) {
                    path = str;
                    break;
                }
            }
        }
        else if (version.getTestBg().equals(theDocNum)) {
            strArray = version.getTestBgPath().split("!");
            for(String str:strArray) {
                if (lastStr(str).equals(fileName)) {
                    path = str;
                    break;
                }
            }
        }
        return path;
    }

    public String getPath (String[] sArray , String[] strArray){
        String path="";
        for(int i=0;i<sArray.length;i++) {
            for(int j =0;j<strArray.length;j++){
                if(strArray[j].equals(lastStr(sArray[i]))){
                    sArray[i]="#";
                }
            }
        }
        for(int i=0;i<sArray.length;i++) {
            if(!sArray[i].equals("#"))
                path=path+"!"+sArray[i];
        }

        if(path.length()>0)
            path=path.substring(1, path.length());
        return path;
    }
}
