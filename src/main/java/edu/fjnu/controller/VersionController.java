package edu.fjnu.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fjnu.entity.Version;
import edu.fjnu.service.DocumentService;
import edu.fjnu.service.ItemService;
import edu.fjnu.service.VersionService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Controller
@Api(tags ="版本管理")
@RequestMapping("/version")
public class VersionController extends BaseController  {


    @Autowired
    VersionService versionService;

    @Autowired
    DocumentService documentService;

    @Autowired
    ItemService itemService;

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

        map.put("documentList", documentService.findAllDocument());

        map.put("itemList", itemService.findAllItem());

        return "version/input_version";
    }

    @ApiOperation(value="新增操作需要传入后台的值")
    @PostMapping(value="/create")
    public String create(Version version,
                         @RequestParam(value="dateVerAlertTime", required=false) Date dateVerAlertTime) {

        if(dateVerAlertTime!=null)
            version.setVerAlertTime(dateVerAlertTime);
        version.setTestJh("JH"+version.getForItem()+version.getDocNum());
        version.setTestYl("YL"+version.getForItem()+version.getDocNum());
        version.setTestJl("JL"+version.getForItem()+version.getDocNum());
        version.setTestQx("QX"+version.getForItem()+version.getDocNum());
        version.setTestBg("BG"+version.getForItem()+version.getDocNum());
        versionService.insertSelective(version);

        return "redirect:/version/list";

    }

    @ApiOperation(value="获取分页列表", notes="用来获取分页列表")
    @ApiImplicitParam(name = "pageNoStr", value = "页码:pageNoStr")
    @RequestMapping("/list")
    public String list(Map<String, Object> map,
                       @RequestParam(value="pageNo", required=false, defaultValue="1") String pageNoStr,
                       @RequestParam(value="pageSize", required=false, defaultValue="3") String pageSizeStr,
                       @RequestParam(value="searchDocNum", required=false) String searchDocNum) {

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
        List<Version> versionList ;
        if(isNotEmpty(searchDocNum))
            versionList = versionService.selectByDocNum(searchDocNum);
        else
            versionList = versionService.findAllVersion();

        PageInfo<Version> page=new PageInfo<Version>(versionList);
        page.setList((List<Version>) JSON.toJSON(page.getList()));
        map.put("page", page);
        map.put("pageSize", pageSize);

        if(isNotEmpty(searchDocNum))
            map.put("searchDocNum", searchDocNum);

        return "version/list_version";
    }

    @ApiOperation(value="进入文件夹查看(修改)界面")
    @ApiImplicitParam(name = "verId", value = "版本verId", required = true, dataType = "Integer")
    @GetMapping(value="/preWatch/{verId}")
    public String preWatch(@PathVariable("verId") Integer verId, Map<String, Object> map){

        map.put("version", versionService.selectByPrimaryKey(verId));

        return "version/version_folder";
    }

    @ApiOperation(value="修改文件夹页面值操作需要传入后台的值")
    @PostMapping(value="/updateFolder")
    public String update(Version version, Map<String, Object> map,
                         @RequestParam(value="dateVerAlertTime", required=false) Date dateVerAlertTime) {

        if(dateVerAlertTime!=null)
            version.setVerAlertTime(dateVerAlertTime);
        versionService.updateByPrimaryKey(version);
        map.put("version", version);
        return "version/version_folder";
    }

    @ApiOperation(value="进入文件夹查看(修改)界面")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileName", value = "文件夹名称fileName", dataType = "String"),
            @ApiImplicitParam(name = "theVerId", value = "文件theVerId", dataType = "Integer")
    })
    @GetMapping(value="/preWatchFile/{fileName}/{theVerId}")
    public String preWatch(@PathVariable("fileName") String fileName,
                           @PathVariable("theVerId") Integer theVerId,
                           Map<String, Object> map){
        Version version = versionService.selectByPrimaryKey(theVerId);

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
            map.put("theDocNum", version.getTestJh());
        }
        else if(fileName.equals("YL")){
            if(version.getTestYlPath()!=null) {
                stringArray = version.getTestYlPath().split("!");
                for (String s : stringArray) {
                    stringList.add(utils.lastStr(s));
                }
            }
            map.put("getFileName", "测试用例");
            map.put("theDocNum", version.getTestYl());
        }
        else if(fileName.equals("JL")){
            if(version.getTestJlPath()!=null) {
                stringArray = version.getTestJlPath().split("!");
                for (String s : stringArray) {
                    stringList.add(utils.lastStr(s));
                }
            }
            map.put("getFileName", "测试记录");
            map.put("theDocNum", version.getTestJl());
        }
        else if(fileName.equals("QX")){
            if(version.getTestQxPath()!=null) {
                stringArray = version.getTestQxPath().split("!");
                for (String s : stringArray) {
                    stringList.add(utils.lastStr(s));
                }
            }
            map.put("getFileName", "缺陷报告");
            map.put("theDocNum", version.getTestQx());
        }
        else if(fileName.equals("BG")){
            if(version.getTestBgPath()!=null) {
                stringArray = version.getTestBgPath().split("!");
                for (String s : stringArray) {
                    stringList.add(utils.lastStr(s));
                }
            }
            map.put("getFileName", "测试报告");
            map.put("theDocNum", version.getTestBg());
        }

        if(stringList!=null)
            map.put("stringList", stringList);
        System.out.println(map.get("theDocNum")+"你真的很好"+map.get("getFileName"));
        return "version/version_file";
    }

    @ApiOperation(value="修改文件夹页面值操作需要传入后台的值")
    @PostMapping(value="/updateFile")
    public String updateFile(Version version, Map<String, Object> map,
                         @RequestParam(value="dateVerAlertTime", required=false) Date dateVerAlertTime,
                         @RequestParam(value="theDocNum", required=false) String theDocNum) {

        System.out.println(theDocNum+"你好啊啊啊啊啊 啊");
        if(dateVerAlertTime!=null)
            version.setVerAlertTime(dateVerAlertTime);
        versionService.updateByPrimaryKeySelective(version);

        Utils utils = new Utils();
        map.put("version", version);
        if(isNotEmpty(theDocNum))
            map.put("theDocNum", theDocNum);

        if(utils.map(version,theDocNum).get("getFileName")!=null)
            map.put("getFileName", utils.map(version,theDocNum).get("getFileName"));
        if(utils.map(version,theDocNum).get("stringList")!=null)
            map.put("stringList", utils.map(version,theDocNum).get("stringList"));

        return "version/version_file";
    }

    @ApiOperation(value="文件上传操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "theDocNum", value = "文件夹编号theDocNum", dataType = "String"),
            @ApiImplicitParam(name = "theVerId", value = "文件theVerId", dataType = "Integer")
    })
    @PostMapping(value="/upLoad/{verId}/{theDocNum}")
    public String fileUpLoad(@RequestParam("file") MultipartFile[] files, Map<String, Object> map,
                             @PathVariable("verId") Integer verId,
                             @PathVariable("theDocNum") String theDocNum) {

        for(MultipartFile f:files){
            System.out.print(f.getOriginalFilename()+"   ");
        }
        System.out.println();

        Utils utils = new Utils();
        Version version = versionService.selectByPrimaryKey(verId);
        version= utils.ver(files, version,theDocNum);
        versionService.updateByPrimaryKeySelective(version);

        map.put("version", version);
        if(isNotEmpty(theDocNum))
            map.put("theDocNum", theDocNum);

        if(utils.map(version,theDocNum).get("getFileName")!=null)
            map.put("getFileName", utils.map(version,theDocNum).get("getFileName"));
        if(utils.map(version,theDocNum).get("stringList")!=null)
            map.put("stringList", utils.map(version,theDocNum).get("stringList"));

        return "version/version_file";
    }

    @ApiOperation(value="单文件下载")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "theDocNum", value = "文件夹编号theDocNum", dataType = "String"),
            @ApiImplicitParam(name = "theVerId", value = "文件theVerId", dataType = "Integer"),
            @ApiImplicitParam(name = "fileName", value = "文件名fileName", dataType = "String")
    })
    @GetMapping(value="/downloadOne/{fileName}/{verId}/{theDocNum}")
    public String downloadOne(@PathVariable("verId") Integer verId ,
                              @PathVariable("theDocNum") String theDocNum ,
                              @PathVariable("fileName") String fileName ,
                              HttpServletResponse response){

        Utils utils =new Utils();
        String path="";

        System.out.println(theDocNum+"和"+fileName);

        Version version= versionService.selectByPrimaryKey(verId);
        path=utils.getOneFilePath(version,theDocNum,fileName);

        File file = new File(path);

        if(file.exists()){ //判断文件父目录是否存在
            try {
                String filename=file.getName();
                System.out.println("----------file download" + filename);
                response.setContentType("application/force-download;charset=UTF-8");
                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(filename, "UTF-8"));

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
            @ApiImplicitParam(name = "theDocNum", value = "文件夹编号theDocNum", dataType = "String"),
            @ApiImplicitParam(name = "theVerId", value = "文件theVerId", dataType = "Integer"),
            @ApiImplicitParam(name = "manyFileName", value = "文件名集合字符串manyFileName", dataType = "String")
    })
    @GetMapping(value="/downloadMany/{manyFileName}/{verId}/{theDocNum}")
    public String downloadMany(@PathVariable("verId") Integer verId ,
                              @PathVariable("theDocNum") String theDocNum ,
                              @PathVariable("manyFileName") String manyFileName ,
                              HttpServletResponse response)  throws IOException {
        String[] strArray = manyFileName.split("!");
        Version version= versionService.selectByPrimaryKey(verId);
        List<String> fileNamePaths = new ArrayList<>();
        Utils utils =new Utils();
        for(String s:strArray){
            fileNamePaths.add(utils.getOneFilePath(version,theDocNum,s));
        }

        response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
        response.setHeader("Content-Disposition","attachment;fileName=" + URLEncoder.encode("批量下载："+strArray[0]+"等文件.zip", "UTF-8"));

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
            @ApiImplicitParam(name = "theDocNum", value = "文件夹编号theDocNum", dataType = "String"),
            @ApiImplicitParam(name = "theVerId", value = "文件theVerId", dataType = "Integer"),
            @ApiImplicitParam(name = "manyFileName", value = "文件名集合字符串manyFileName", dataType = "String")
    })
    @GetMapping(value="/removeFile/{manyFileName}/{verId}/{theDocNum}")
    public String removeFile(@PathVariable("verId") Integer verId ,Map<String, Object> map,
                               @PathVariable("theDocNum") String theDocNum ,
                               @PathVariable("manyFileName") String manyFileName ) {

        System.out.println(manyFileName);
        Version version= versionService.selectByPrimaryKey(verId);
        String[] strArray = manyFileName.split("!");

        List<String> fileNamePaths = new ArrayList<>();
        Utils utils =new Utils();
        for(String s:strArray){
            fileNamePaths.add(utils.getOneFilePath(version,theDocNum,s));
        }

        String path=null;
        if(version.getTestJh().equals(theDocNum)) {
            String[] sArray = version.getTestJhPath().split("!");

            path=utils.getPath(sArray,strArray);
            version.setTestJhPath(path);
        }
        else if (version.getTestYl().equals(theDocNum)) {
            String[] sArray = version.getTestYlPath().split("!");
            path=utils.getPath(sArray,strArray);
            version.setTestYlPath(path);
        }
        else if (version.getTestJl().equals(theDocNum)) {
            String[] sArray = version.getTestJlPath().split("!");
            path=utils.getPath(sArray,strArray);
            version.setTestJlPath(path);
        }
        else if (version.getTestQx().equals(theDocNum)) {
            String[] sArray = version.getTestQxPath().split("!");
            path=utils.getPath(sArray,strArray);
            version.setTestQxPath(path);
        }
        else if (version.getTestBg().equals(theDocNum)) {
            String[] sArray = version.getTestBgPath().split("!");
            path=utils.getPath(sArray,strArray);
            version.setTestBgPath(path);
        }
        versionService.updateByPrimaryKeySelective(version);

        map.put("version", version);
        if(isNotEmpty(theDocNum))
            map.put("theDocNum", theDocNum);

        if(utils.map(version,theDocNum).get("stringList")!=null)
            map.put("stringList", utils.map(version,theDocNum).get("stringList"));


        if(utils.map(version,theDocNum).get("getFileName")!=null)
            map.put("getFileName", utils.map(version,theDocNum).get("getFileName"));

        return "version/version_file";

    }

}

