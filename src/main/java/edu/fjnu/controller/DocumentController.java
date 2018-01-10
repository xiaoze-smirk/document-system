package edu.fjnu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fjnu.entity.Document;
import edu.fjnu.entity.State;
import edu.fjnu.service.DocumentService;
import edu.fjnu.service.StateService;
import edu.fjnu.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Controller
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

    @GetMapping("/toInput")
    public String input(Map<String, Object> map) {

        map.put("document", new Document());
        map.put("stateList", stateService.findAllState());

        return "document/input_document";
    }

    @PostMapping(value="/create")
    public String create(@RequestParam("files") MultipartFile[] files, Document document) {

        if(isEmpty(document.getDocState()))
            return "redirect:/document/toInput";
        document.setDocReleaseDate(new Date());
        Utils utils = new Utils();
        document= utils.docm(files, document);

        documentService.insert(document);

        return "redirect:/document/list";

    }

    @GetMapping("/list")
    public String list(Map<String, Object> map, @RequestParam(value="pageNo", required=false, defaultValue="1") String pageNoStr) {

        int pageNo = 1;

        //对 pageNo 的校验
        pageNo = Integer.parseInt(pageNoStr);
        if(pageNo < 1){
            pageNo = 1;
        }

        /*
         * 第一个参数：第几页;
         * 第二个参数：每页获取的条数.
         */
        PageHelper.startPage(pageNo, 3);
        List<Document> documentList = documentService.findAllDocument();

        PageInfo<Document> page=new PageInfo<>(documentList);

        map.put("page", page);

        return "document/list_document";
    }

    @DeleteMapping(value="/remove/{docNum}")
    public String remove(@PathVariable("docNum") String docNum) {

        documentService.deleteByPrimaryKey(docNum);

        return "redirect:/document/list";
    }

    @GetMapping(value="/preUpdate/{docNum}")
    public String preUpdate(@PathVariable("docNum") String docNum, Map<String, Object> map){


        Document document = documentService.selectByPrimaryKey(docNum);
        map.put("stateList", stateService.findAllState());
        map.put("state", stateService.selectByPrimaryKey(document.getDocState()));
        map.put("document",document);

        return "document/update_document";
    }

    @PutMapping(value="/update")
    public String update(@RequestParam("files") MultipartFile[] files,Document document) {

        Utils utils = new Utils();
        document= utils.docm(files, document);
        documentService.updateByPrimaryKey(document);

        return "redirect:/document/list";
    }

    @GetMapping(value="/download/{docNum}/{testNum}")
    public String downLoad(@PathVariable("docNum") String docNum , @PathVariable("testNum") String testNum , HttpServletResponse response){

        String path=null;
        Document document= documentService.selectByPrimaryKey(docNum);
        if(document.getTestJh().equals(testNum))
            path=document.getTestJhPath();
        else if (document.getTestYl().equals(testNum))
            path=document.getTestYlPath();
        else if (document.getTestJl().equals(testNum))
            path=document.getTestJlPath();
        else if (document.getTestQx().equals(testNum))
            path=document.getTestQxPath();
        else if (document.getTestBg().equals(testNum))
            path=document.getTestBgPath();

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


}
