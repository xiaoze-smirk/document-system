package edu.fjnu.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fjnu.entity.Department;
import edu.fjnu.entity.Document;
import edu.fjnu.entity.State;
import edu.fjnu.service.DocumentService;
import edu.fjnu.service.StateService;
import edu.fjnu.utils.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Controller
@Api(tags ="文档管理")
@RequestMapping("/document")
public class DocumentController extends BaseController {

    @Autowired
    DocumentService documentService;

    @Autowired
    StateService stateService;

    @Autowired
    State state;

    @ModelAttribute
    public void getDepartment(@RequestParam(value="docNum",required=false) String docNum,
                              Map<String, Object> map, Document document){

        if(isNotEmpty(docNum))
            document=documentService.selectByPrimaryKey(docNum);
        if(document != null)
            map.put("document", document);
    }

    @ApiOperation(value="新增文档界面")
    @GetMapping("/toInput")
    public String input(Map<String, Object> map) {

        map.put("document", new Document());
        map.put("stateList", stateService.findAllState());

        return "document/input_document";
    }

    @ApiOperation(value="新增操作需要传入后台的值")
    @PostMapping(value="/create")
    public String create(Document document,
                         @RequestParam(value="dateDocReleaseDate", required=false) Date dateDocReleaseDate) {

        if(isEmpty(document.getDocState()))
            return "redirect:/document/toInput";

        if(dateDocReleaseDate!=null)
            document.setDocReleaseDate(dateDocReleaseDate);

        documentService.insert(document);

        return "redirect:/document/list";

    }

    @ApiOperation(value="获取分页列表", notes="用来获取分页列表")
    @ApiImplicitParam(name = "pageNoStr", value = "页码:pageNoStr")
    @RequestMapping("/list")
    public String list(Map<String, Object> map,
                       @RequestParam(value="pageNo", required=false, defaultValue="1") String pageNoStr,
                       @RequestParam(value="pageSize", required=false, defaultValue="3") String pageSizeStr,
                       @RequestParam(value="searchDocTitle", required=false) String searchDocTitle) {

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

        Map<String,Object> map1 = new HashMap<>();
        if(isNotEmpty(searchDocTitle))
            map1.put("searchDocTitle", searchDocTitle);

        PageHelper.startPage(pageNo, pageSize);
        List<Document> documentList = documentService.findByDocTitle(map1);

        PageInfo<Document> page=new PageInfo<>(documentList);
        page.setList((List<Document>) JSON.toJSON(page.getList()));

        map.put("page", page);

        map.put("pageSize", pageSize);

        if(isNotEmpty(searchDocTitle))
            map.put("searchDocTitle", searchDocTitle);

        return "document/list_document";
    }

    @ApiOperation(value="删除操作后台所需要的值")
    @ApiImplicitParam(name = "docNum", value = "文档docNum", required = true, dataType = "String")
    @DeleteMapping(value="/remove/{docNum}")
    public String remove(@PathVariable("docNum") String docNum) {

        documentService.deleteByPrimaryKey(docNum);

        return "redirect:/document/list";
    }

    @ApiOperation(value="进入文档修改界面")
    @ApiImplicitParam(name = "docNum", value = "文档docNum", dataType = "String")
    @GetMapping(value="/preUpdate/{docNum}")
    public String preUpdate(@PathVariable("docNum") String docNum, Map<String, Object> map){


        Document document = documentService.selectByPrimaryKey(docNum);
        map.put("stateList", stateService.findAllState());
        map.put("state", stateService.selectByPrimaryKey(document.getDocState()));
        map.put("document",document);

        return "document/update_document";
    }

    @ApiOperation(value="修改操作需要传入后台的值")
    @PutMapping(value="/update")
    public String update(Document document) {

        documentService.updateByPrimaryKey(document);

        return "redirect:/document/list";
    }



}
