package edu.fjnu.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fjnu.entity.Version;
import edu.fjnu.service.ItemService;
import edu.fjnu.service.UserService;
import edu.fjnu.service.VersionService;
import edu.fjnu.utils.StringUtil;
import edu.fjnu.utils.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
@Api(tags ="版本管理")
@RequestMapping("/version")
public class VersionController extends BaseController  {


    @Autowired
    VersionService versionService;

    @Autowired
    ItemService itemService;

    @Autowired
    UserService userService;

    @ModelAttribute
    public void getDepartment(@RequestParam(value="verId",required=false) Integer verId,
                              Map<String, Object> map, Version version){

        if(verId!=null)
            version=versionService.selectByPrimaryKey(verId);
        if(version != null)
            map.put("version", version);
    }

    @ApiOperation(value="进入主页")
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @ApiOperation(value="新增版本界面")
    @GetMapping("/toInput")
    public String input(Map<String, Object> map) {

        map.put("version", new Version());

        map.put("itemList", itemService.findAllItem());

        map.put("userList", userService.findAllUser());

        return "version/input_version";
    }

    @ApiOperation(value="新增操作需要传入后台的值")
    @PostMapping(value="/create")
    public String create(Version version,
                         @RequestParam(value="dateVerAlertTime", required=false) Date dateVerAlertTime) {

        try {
            if(versionService.selectByItemId(version.getItemId()).getItemId()!=null)
                return "redirect:/version/list";
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        if(dateVerAlertTime!=null)
            version.setVerAlertTime(dateVerAlertTime);
        if(version.getJhBiggestNum()==null||version.getJhBiggestNum()==0)
            version.setJhBiggestNum(version.getVerNum());
        if(version.getYlBiggestNum()==null||version.getYlBiggestNum()==0)
            version.setYlBiggestNum(version.getVerNum());
        if(version.getJlBiggestNum()==null||version.getJlBiggestNum()==0)
            version.setJlBiggestNum(version.getVerNum());
        if(version.getQxBiggestNum()==null||version.getQxBiggestNum()==0)
            version.setQxBiggestNum(version.getVerNum());
        if(version.getBgBiggestNum()==null||version.getBgBiggestNum()==0)
            version.setBgBiggestNum(version.getVerNum());
        if(version.getShBiggestNum()==null||version.getShBiggestNum()==0)
            version.setShBiggestNum(version.getVerNum());
        if(version.getQtBiggestNum()==null||version.getQtBiggestNum()==0)
            version.setQtBiggestNum(version.getVerNum());


        versionService.insertSelective(version);

        return "redirect:/version/list";

    }

    @ApiOperation(value="获取分页列表", notes="用来获取分页列表")
    @ApiImplicitParam(name = "pageNoStr", value = "页码:pageNoStr")
    @RequestMapping("/list")
    public String list(Map<String, Object> map,
                       @RequestParam(value="pageNo", required=false, defaultValue="1") String pageNoStr,
                       @RequestParam(value="pageSize", required=false, defaultValue="3") String pageSizeStr) {

        Integer pageNo = 1;
        Integer pageSize = 3;

        //对 pageNo 的校验
        pageNo = Integer.parseInt(pageNoStr);
        if(pageNo < 1){
            pageNo = 1;
        }

        //校验pageSize
        pageSize = Integer.parseInt(pageSizeStr);
        if(pageSize < 1){
            pageSize = 3;
        }

        PageHelper.startPage(pageNo, pageSize);
        List<Version> versionList =versionService.findAllVersion();

        PageInfo<Version> page=new PageInfo<>(versionList);
        List <Version> versionList1 = new ArrayList<>();

        for(Version version:page.getList()){
            version.setItem(itemService.selectByPrimaryKey(version.getItemId()));
            versionList1.add(version);
        }

        page.setList((List<Version>) JSON.toJSON(versionList1));
        map.put("page", page);
        map.put("pageSize", pageSize);

        return "version/list_version";
    }

    @ApiOperation(value="进入文件夹查看(修改)界面")
    @ApiImplicitParam(name = "verId", value = "版本verId", required = true, dataType = "Integer")
    @GetMapping(value="/preWatchFolder/{itemId}")
    public String preWatchFolder(@PathVariable("itemId") Integer itemId, Map<String, Object> map){

        try {
            if(versionService.selectByItemId(itemId).getItemId()!=null)
                map.put("version", versionService.selectByItemId(itemId));
        }catch (Exception e){
            map.put("version", new Version());
        }

        map.put("userList", userService.findAllUser());

        return "version/version_folder";
    }

    @ApiOperation(value="修改文件夹页面值操作需要传入后台的值")
    @PostMapping(value="/updateFolder")
    public String update(Version version, Map<String, Object> map,
                         @RequestParam(value="dateVerAlertTime", required=false) Date dateVerAlertTime) {

        if(dateVerAlertTime!=null)
            version.setVerAlertTime(dateVerAlertTime);
        versionService.updateByPrimaryKeySelective(version);
        map.put("userList", userService.findAllUser());
        map.put("version", version);
        return "version/version_folder";
    }

    @ApiOperation(value="进入文件夹查看文件界面")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileName", value = "文件夹名称fileName", dataType = "String"),
            @ApiImplicitParam(name = "theVerId", value = "文件theVerId", dataType = "Integer")
    })
    @GetMapping(value="/preWatchFile/{fileName}/{theVerId}")
    public String preWatchFile(@PathVariable("fileName") String fileName,
                           @PathVariable("theVerId") Integer theVerId,
                           Map<String, Object> map){
        Version version = versionService.selectByPrimaryKey(theVerId);
        DecimalFormat df = new DecimalFormat("######0.0");
        map.put("version", version);

        String[] stringArray = null;
        List<String> stringList = new ArrayList<>();
        Utils utils = new Utils();

        if(fileName.equals("JH")) {
            if(version.getTestJhPath()!=null) {
                stringArray = version.getTestJhPath().split("!");
                for (String s : stringArray) {
                    stringList.add(utils.lastStr(s));
                }
            }
            map.put("getFileName", "测试计划");
            map.put("getBiggest", df.format(version.getJhBiggestNum()));
        }else if(fileName.equals("YL")){
            if(version.getTestYlPath()!=null) {
                stringArray = version.getTestYlPath().split("!");
                for (String s : stringArray) {
                    stringList.add(utils.lastStr(s));
                }
            }
            map.put("getFileName", "测试用例");
            map.put("getBiggest", df.format(version.getYlBiggestNum()));
        }else if(fileName.equals("JL")){
            if(version.getTestJlPath()!=null) {
                stringArray = version.getTestJlPath().split("!");
                for (String s : stringArray) {
                    stringList.add(utils.lastStr(s));
                }
            }
            map.put("getFileName", "测试记录");
            map.put("getBiggest", df.format(version.getJlBiggestNum()));
        }else if(fileName.equals("QX")){
            if(version.getTestQxPath()!=null) {
                stringArray = version.getTestQxPath().split("!");
                for (String s : stringArray) {
                    stringList.add(utils.lastStr(s));
                }
            }
            map.put("getFileName", "缺陷报告");
            map.put("getBiggest", df.format(version.getQxBiggestNum()));
        }else if(fileName.equals("BG")){
            if(version.getTestBgPath()!=null) {
                stringArray = version.getTestBgPath().split("!");
                for (String s : stringArray) {
                    stringList.add(utils.lastStr(s));
                }
            }
            map.put("getFileName", "测试报告");
            map.put("getBiggest", df.format(version.getBgBiggestNum()));
        }else if(fileName.equals("SH")){
            if(version.getTestShPath()!=null) {
                stringArray = version.getTestShPath().split("!");
                for (String s : stringArray) {
                    stringList.add(utils.lastStr(s));
                }
            }
            map.put("getFileName", "测试输入项");
            map.put("getBiggest", df.format(version.getShBiggestNum()));
        }else if(fileName.equals("QT")){
            if(version.getTestQtPath()!=null) {
                stringArray = version.getTestQtPath().split("!");
                for (String s : stringArray) {
                    stringList.add(utils.lastStr(s));
                }
            }
            map.put("getFileName", "其他");
            map.put("getBiggest", df.format(version.getQtBiggestNum()));
        }

        map.put("stringList", stringList);
        map.put("userList", userService.findAllUser());
        System.out.println("你真的很好"+map.get("getFileName"));
        return "version/version_file";

    }

    @ApiOperation(value="修改文件夹页面值操作需要传入后台的值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "getBiggest", value = "最高版本theVerId"),
            @ApiImplicitParam(name = "getFileName", value = "文件夹名称getFileName", dataType = "String")
    })
    @PostMapping(value="/updateFile/{dateVerAlertTime}/{getBiggest}/{getFileName}")
    public String updateFile(Version version, Map<String, Object> map,
                             @PathVariable("dateVerAlertTime") Date dateVerAlertTime,
                             @PathVariable("getFileName") String getFileName,
                             @PathVariable("getBiggest") Double getBiggest){

        if(dateVerAlertTime!=null)
            version.setVerAlertTime(dateVerAlertTime);
        System.out.println(version.getTestJhPath());
        Utils utils = new Utils();
        if(utils.theMap(version,getFileName).get("stringList")!=null)
            map.put("stringList", utils.theMap(version,getFileName).get("stringList"));
        map.put("getFileName", getFileName);
        map.put("getBiggest", getBiggest);
        map.put("userList", userService.findAllUser());
        System.out.println("你真的很好"+map.get("getFileName"));
        versionService.updateByPrimaryKeySelective(version);
        return "version/version_file";

    }

    @ApiOperation(value="文件上传操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "getBiggest", value = "最高版本theVerId"),
            @ApiImplicitParam(name = "getFileName", value = "文件夹名称getFileName", dataType = "String")
    })
    @PostMapping(value="/upLoad/{verId}/{getBiggest}/{getFileName}")
    public String fileUpLoad(@RequestParam("file") MultipartFile[] files, Map<String, Object> map,
                             @PathVariable("verId") Integer verId,
                             @PathVariable("getFileName") String getFileName,
                             @PathVariable("getBiggest") Double getBiggest) {

        for(MultipartFile f:files){
            System.out.print(f.getOriginalFilename()+"   ");
        }
        System.out.println();

        Utils utils = new Utils();
        Version version = versionService.selectByPrimaryKey(verId);
        version= utils.ver(files, version,getFileName);
        versionService.updateByPrimaryKeySelective(version);

        map.put("version", version);
        if(utils.theMap(version,getFileName).get("stringList")!=null)
            map.put("stringList", utils.theMap(version,getFileName).get("stringList"));

        map.put("getFileName", getFileName);
        map.put("getBiggest", getBiggest);
        map.put("userList", userService.findAllUser());

        return "version/version_file";
    }

    @ApiOperation(value="单文件下载")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileName", value = "文件名fileName", dataType = "String"),
            @ApiImplicitParam(name = "theVerId", value = "文件theVerId"),
            @ApiImplicitParam(name = "getFileName", value = "文件夹名称getFileName", dataType = "String")
    })
    @GetMapping(value="/downloadOne/{fileName}/{verId}/{getFileName}")
    public String downloadOne(@PathVariable("fileName") String fileName ,
                              @PathVariable("verId") Integer verId ,
                              @PathVariable("getFileName") String getFileName,
                              HttpServletResponse response){
        StringUtil stringUtil = new StringUtil();
        Utils utils =new Utils();
        String path="";

        System.out.println(getFileName+"和"+fileName);

        Version version= versionService.selectByPrimaryKey(verId);
        path=utils.getOneFilePath(version,getFileName,fileName);

        File file = new File(path);

        if(file.exists()){ //判断文件父目录是否存在
            try {
                String filename=file.getName();
                System.out.println("----------file download" + filename);
                response.setContentType("application/force-download;charset=UTF-8");
                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(stringUtil.downloadFileName(filename), "UTF-8"));

                byte[] buffer = new byte[1024];
                FileInputStream fis = null; //文件输入流
                BufferedInputStream bis = null;

                OutputStream os = null; //输出流

                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }
                bis.close();
                fis.close();

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    @ApiOperation(value="多文件下载")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileName", value = "文件名fileName", dataType = "String"),
            @ApiImplicitParam(name = "theVerId", value = "文件theVerId"),
            @ApiImplicitParam(name = "getFileName", value = "文件夹名称getFileName", dataType = "String")
    })
    @GetMapping(value="/downloadMany/{manyFileName}/{verId}/{getFileName}")
    public String downloadMany(@PathVariable("manyFileName") String manyFileName ,
                               @PathVariable("verId") Integer verId ,
                               @PathVariable("getFileName") String getFileName,
                               HttpServletResponse response)  throws IOException {
        String[] strArray = manyFileName.split("!");
        Version version= versionService.selectByPrimaryKey(verId);
        List<String> fileNamePaths = new ArrayList<>();
        StringUtil stringUtil = new StringUtil();
        Utils utils =new Utils();
        for(String s:strArray){
            fileNamePaths.add(utils.getOneFilePath(version,getFileName,s));
        }

        response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
        response.setHeader("Content-Disposition","attachment;fileName=" + URLEncoder.encode("批量下载："+stringUtil.downloadFileName(strArray[0])+"等文件.zip", "UTF-8"));

        ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());

        for(String fileNamePath : fileNamePaths) {
            ZipEntry zipEntry = new ZipEntry(utils.lastStr(fileNamePath));
            zipOutputStream.putNextEntry(zipEntry);
            FileInputStream inputStream = new FileInputStream(fileNamePath);
            IOUtils.copy(inputStream,zipOutputStream);
            inputStream.close();
        }

        zipOutputStream.closeEntry();
        zipOutputStream.close();

        return null;

    }

    @ApiOperation(value="文件删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileName", value = "文件名fileName", dataType = "String"),
            @ApiImplicitParam(name = "theVerId", value = "文件theVerId"),
            @ApiImplicitParam(name = "getFileName", value = "文件夹名称getFileName", dataType = "String")
    })
    @GetMapping(value="/removeFile/{manyFileName}/{verId}/{getFileName}")
    public String removeFile(@PathVariable("manyFileName") String manyFileName,Map<String, Object> map,
                             @PathVariable("verId") Integer verId ,
                             @PathVariable("getFileName") String getFileName) throws Exception{

        System.out.println(manyFileName);
        Version version= versionService.selectByPrimaryKey(verId);
        String[] strArray = manyFileName.split("!");

        List<String> fileNamePaths = new ArrayList<>();
        Utils utils =new Utils();
        for(String s:strArray){
            fileNamePaths.add(utils.getOneFilePath(version,getFileName,s));
        }

        String path=null;
        if(getFileName.equals("测试计划")) {
            String[] sArray = version.getTestJhPath().split("!");
            path=utils.getPath(sArray,strArray);
            version.setTestJhPath(path);
            version.setJhBiggestNum(utils.getbiggest(path));
            map.put("getBiggest", version.getJhBiggestNum());
        }else if (getFileName.equals("测试用例")) {
            String[] sArray = version.getTestYlPath().split("!");
            path=utils.getPath(sArray,strArray);
            version.setTestYlPath(path);
            version.setYlBiggestNum(utils.getbiggest(path));
            map.put("getBiggest", version.getYlBiggestNum());
        }else if (getFileName.equals("测试记录")) {
            String[] sArray = version.getTestJlPath().split("!");
            path=utils.getPath(sArray,strArray);
            version.setTestJlPath(path);
            version.setJlBiggestNum(utils.getbiggest(path));
            map.put("getBiggest", version.getJlBiggestNum());
        }else if (getFileName.equals("缺陷报告")) {
            String[] sArray = version.getTestQxPath().split("!");
            path=utils.getPath(sArray,strArray);
            version.setTestQxPath(path);
            version.setQxBiggestNum(utils.getbiggest(path));
            map.put("getBiggest", version.getQxBiggestNum());
        }else if (getFileName.equals("测试报告")) {
            String[] sArray = version.getTestBgPath().split("!");
            path=utils.getPath(sArray,strArray);
            version.setTestBgPath(path);
            version.setBgBiggestNum(utils.getbiggest(path));
            map.put("getBiggest", version.getBgBiggestNum());
        }else if (getFileName.equals("测试输入项")) {
            String[] sArray = version.getTestShPath().split("!");
            path=utils.getPath(sArray,strArray);
            version.setTestShPath(path);
            version.setShBiggestNum(utils.getbiggest(path));
            map.put("getBiggest", version.getShBiggestNum());
        }else if (getFileName.equals("其他")) {
            String[] sArray = version.getTestQtPath().split("!");
            path=utils.getPath(sArray,strArray);
            version.setTestQtPath(path);
            version.setQtBiggestNum(utils.getbiggest(path));
            map.put("getBiggest", version.getQtBiggestNum());
        }
        versionService.updateByPrimaryKeySelective(version);

        map.put("version", version);

        if(utils.theMap(version,getFileName).get("stringList")!=null)
            map.put("stringList", utils.theMap(version,getFileName).get("stringList"));

        map.put("getFileName", getFileName);
        map.put("userList", userService.findAllUser());
        return "version/version_file";

    }


}

