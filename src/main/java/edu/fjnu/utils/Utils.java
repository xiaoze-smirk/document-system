package edu.fjnu.utils;

import edu.fjnu.entity.Version;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * @author xiaoze
 * @date 2018/3/8
 */
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
                String[] strArray=path.split("\\/");
                String fileName = strArray[strArray.length-1];
                String filePath = path;
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

    public Version ver(MultipartFile[] files, Version version,String getFileName){
        DecimalFormat df = new DecimalFormat("######0.0");
        String path ;
        int j=0;
        for(int i=0;i<files.length;i++){
            path="F:/xiaoze/abc/";
            if(getFileName.equals("测试计划")&&!files[i].isEmpty()) {
                if(i==0)
                    version.setJhBiggestNum(version.getJhBiggestNum()+0.1);
                path = path + "测试计划/"+"("+df.format(version.getJhBiggestNum())+")";
                path=path+files[i].getOriginalFilename();
                saveFile(path , files[i]);

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
            }else if(getFileName.equals("测试用例")&&!files[i].isEmpty()) {
                if(i==0)
                    version.setYlBiggestNum(version.getYlBiggestNum()+0.1);
                path = path + "测试用例/"+"("+df.format(version.getYlBiggestNum())+")";
                path=path+files[i].getOriginalFilename();
                saveFile(path , files[i]);
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
            }else if(getFileName.equals("测试记录")&&!files[i].isEmpty()) {
                if(i==0)
                    version.setJlBiggestNum(version.getJlBiggestNum()+0.1);
                path = path + "测试记录/"+"("+df.format(version.getJlBiggestNum())+")";
                path=path+files[i].getOriginalFilename();
                saveFile(path , files[i]);
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
            }else if(getFileName.equals("缺陷报告")&&!files[i].isEmpty()) {
                if(i==0)
                    version.setQxBiggestNum(version.getQxBiggestNum()+0.1);
                path = path + "缺陷报告/"+"("+df.format(version.getQxBiggestNum())+")";
                path=path+files[i].getOriginalFilename();
                saveFile(path , files[i]);
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
            }else if(getFileName.equals("测试报告")&&!files[i].isEmpty()) {
                if(i==0)
                    version.setBgBiggestNum(version.getBgBiggestNum()+0.1);
                path = path + "测试报告/"+"("+df.format(version.getBgBiggestNum())+")";
                path=path+files[i].getOriginalFilename();
                saveFile(path , files[i]);
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
            }else if(getFileName.equals("测试输入项")&&!files[i].isEmpty()) {
                if(i==0)
                    version.setShBiggestNum(version.getShBiggestNum()+0.1);
                path = path + "测试输入项/"+"("+df.format(version.getShBiggestNum())+")";
                path=path+files[i].getOriginalFilename();
                saveFile(path , files[i]);
                if(isNotEmpty(version.getTestShPath())) {
                    String[] strArray=version.getTestShPath().split("!");
                    for(j=0;j<strArray.length;j++){
                        if(strArray[j].equals(path))
                            break;
                    }
                    if(j==strArray.length)
                        version.setTestShPath(version.getTestShPath() + "!" + path);
                }
                else
                    version.setTestShPath(path);
            }else if(getFileName.equals("其他")&&!files[i].isEmpty()) {
                if(i==0)
                    version.setQtBiggestNum(version.getQtBiggestNum()+0.1);
                path = path + "其他/"+"("+df.format(version.getQtBiggestNum())+")";
                path=path+files[i].getOriginalFilename();
                saveFile(path , files[i]);
                if(isNotEmpty(version.getTestQtPath())) {
                    String[] strArray=version.getTestQtPath().split("!");
                    for(j=0;j<strArray.length;j++){
                        if(strArray[j].equals(path))
                            break;
                    }
                    if(j==strArray.length)
                        version.setTestQtPath(version.getTestQtPath() + "!" + path);
                }
                else
                    version.setTestQtPath(path);
            }

        }
        return version;
    }

    public Integer getStateLength(String str){

        Integer length = 0;
        if(str.equals("a"))
            length=0;
        else if(str.equals("b"))
            length=160;
        else if(str.equals("c"))
            length=330;
        else if(str.equals("d"))
            length=500;
        else if(str.equals("e"))
            length=660;
        else if(str.equals("f"))
            length=830;
        else if(str.equals("g"))
            length=1000;

        return length;

    }

    //去字符串最后一个字符串，即（文件名）
    public String lastStr(String str){
        String[] strArray = str.split("\\/");
        return strArray[strArray.length-1];
    }

    public Map<String, Object> theMap(Version version, String fileName){

        Map<String, Object> map = new HashMap<>();

        String[] stringArray = null;
        List<String> stringList = new ArrayList<>();
        if(version.getTestJhPath()!=null&&fileName.equals("测试计划")) {
            if(version.getTestJhPath()!=null) {
                stringArray = version.getTestJhPath().split("!");
                for (String s : stringArray) {
                    stringList.add(lastStr(s));
                }
            }
        }else if(version.getTestYlPath()!=null&&fileName.equals("测试用例")){
            if(version.getTestYlPath()!=null) {
                stringArray = version.getTestYlPath().split("!");
                for (String s : stringArray) {
                    stringList.add(lastStr(s));
                }
            }
        }else if(version.getTestJlPath()!=null&&fileName.equals("测试记录")){
            if(version.getTestJlPath()!=null) {
                stringArray = version.getTestJlPath().split("!");
                for (String s : stringArray) {
                    stringList.add(lastStr(s));
                }
            }
        }
        else if(version.getTestQxPath()!=null&&fileName.equals("缺陷报告")){
            if(version.getTestQxPath()!=null) {
                stringArray = version.getTestQxPath().split("!");
                for (String s : stringArray) {
                    stringList.add(lastStr(s));
                }
            }
        }else if(version.getTestBgPath()!=null&&fileName.equals("测试报告")){
            if(version.getTestBgPath()!=null) {
                stringArray = version.getTestBgPath().split("!");
                for (String s : stringArray) {
                    stringList.add(lastStr(s));
                }
            }
        }else if(version.getTestShPath()!=null&&fileName.equals("测试输入项")){
            if(version.getTestShPath()!=null) {
                stringArray = version.getTestShPath().split("!");
                for (String s : stringArray) {
                    stringList.add(lastStr(s));
                }
            }
        }else if(version.getTestQtPath()!=null&&fileName.equals("其他")){
            if(version.getTestQtPath()!=null) {
                stringArray = version.getTestQtPath().split("!");
                for (String s : stringArray) {
                    stringList.add(lastStr(s));
                }
            }
        }

        map.put("stringList", stringList);

        return map;
    }

    public String getOneFilePath(Version version,String getFileName,String fileName){
        String path=null;
        String[] strArray=null;
        if(getFileName.equals("测试计划")) {
            strArray = version.getTestJhPath().split("!");
            for(String str:strArray) {
                if (lastStr(str).equals(fileName)) {
                    path = str;
                    break;
                }
            }
        }else if (getFileName.equals("测试用例")) {
            strArray = version.getTestYlPath().split("!");
            for(String str:strArray) {
                if (lastStr(str).equals(fileName)) {
                    path = str;
                    break;
                }
            }
        }else if (getFileName.equals("测试记录")) {
            strArray = version.getTestJlPath().split("!");
            for(String str:strArray) {
                if (lastStr(str).equals(fileName)) {
                    path = str;
                    break;
                }
            }
        }else if (getFileName.equals("缺陷报告")) {
            strArray = version.getTestQxPath().split("!");
            for(String str:strArray) {
                if (lastStr(str).equals(fileName)) {
                    path = str;
                    break;
                }
            }
        }else if (getFileName.equals("测试报告")) {
            strArray = version.getTestBgPath().split("!");
            for(String str:strArray) {
                if (lastStr(str).equals(fileName)) {
                    path = str;
                    break;
                }
            }
        }else if (getFileName.equals("测试输入项")) {
            strArray = version.getTestShPath().split("!");
            for(String str:strArray) {
                if (lastStr(str).equals(fileName)) {
                    path = str;
                    break;
                }
            }
        }else if (getFileName.equals("其他")) {
            strArray = version.getTestQtPath().split("!");
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

    public Double getbiggest(String path) throws Exception{
        String[] strArray = path.split("!");
        List<Double> dbList = new ArrayList<>();
        String s;
        for(String str:strArray){
            s=lastStr(str).split("\\)")[0];
            dbList.add(Double.parseDouble(s.substring(1, s.length())));
        }
        Collections.sort(dbList);
        return dbList.get(dbList.size()-1);
    }


}
