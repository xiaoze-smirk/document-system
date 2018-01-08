package edu.fjnu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fjnu.entity.Version;
import edu.fjnu.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/version")
public class VersionController extends BaseController  {


    @Autowired
    VersionService versionService;

    @ModelAttribute
    public void getDepartment(@RequestParam(value="verId",required=false) Integer verId,
                              Map<String, Object> map, Version version){

        if(verId!=null)
            version=versionService.selectByPrimaryKey(verId);
        if(version != null)
            map.put("version", version);
    }

    @GetMapping("/toInput")
    public String input(Map<String, Object> map) {
        map.put("version", new Version());

        return "version/input_version";
    }

    @PostMapping(value="/create")
    public String create(Version version) {

        versionService.insert(version);

        return "redirect:/version/list";

    }

    @GetMapping("/list")
    public String list(Map<String, Object> map, @RequestParam(value="pageNo", required=false, defaultValue="1") String pageNoStr) {

        int pageNo = 1;

        //对 pageNo 的校验
        pageNo = Integer.parseInt(pageNoStr);
        if(pageNo < 1){
            pageNo = 1;
        }

        PageHelper.startPage(pageNo, 3);
        List<Version> versionList = versionService.findAllVersion();

        PageInfo<Version> page=new PageInfo<Version>(versionList);

        map.put("page", page);

        return "version/list_version";
    }

    @DeleteMapping(value="/remove/{verId}")
    public String remove(@PathVariable("verId") Integer verId) {

        versionService.deleteByPrimaryKey(verId);

        return "redirect:/version/list";
    }

    @GetMapping(value="/preUpdate/{verId}")
    public String preUpdate(@PathVariable("verId") Integer verId, Map<String, Object> map){

        map.put("version", versionService.selectByPrimaryKey(verId));

        return "version/update_version";
    }

    @PutMapping(value="/update")
    public String update(Version version) {

        versionService.updateByPrimaryKey(version);

        return "redirect:/version/list";
    }



}

