package edu.fjnu.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fjnu.entity.Department;
import edu.fjnu.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Controller
@Api(tags ="部门管理")
@RequestMapping("/department")
public class DepartmentController extends BaseController {

    @Autowired
    private DepartmentService departmentService;

    @ModelAttribute
    public void getDepartment(@RequestParam(value="deptId",required=false) String deptId,
                            Map<String, Object> map, Department department){

        if(isNotEmpty(deptId))
            department=departmentService.selectByPrimaryKey(deptId);
        if(department != null)
            map.put("department", department);
    }

    @ApiOperation(value="新增部门界面")
    @GetMapping("/toInput")
    public String input(Map<String, Object> map) {
        map.put("department", new Department());

        return "department/input_department";
    }

    @ApiOperation(value="新增操作需要传入后台的值")
    @PostMapping(value="/create")
    public String create(Department department) {

        departmentService.insert(department);

        return "redirect:/department/list";

    }

    @ApiOperation(value="获取分页列表", notes="用来获取分页列表")
    @ApiImplicitParam(name = "pageNoStr", value = "页码:pageNoStr")
    @RequestMapping("/list")
    public String list(Map<String, Object> map,
                       @RequestParam(value="pageNo", required=false, defaultValue="1") String pageNoStr,
                       @RequestParam(value="pageSize", required=false, defaultValue="3") String pageSizeStr,
                       @RequestParam(value="searchDeptname", required=false) String searchDeptname) {
        System.out.println(searchDeptname+"啊啊");

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

        Map<String,Object> map1 = new HashMap<String,Object>();
        if(isNotEmpty(searchDeptname))
            map1.put("searchDeptname", searchDeptname);

        PageHelper.startPage(pageNo, pageSize);
        List<Department> departmentList = departmentService.findByDeptName(map1);

        PageInfo<Department> page=new PageInfo<Department>(departmentList);

        map.put("page", page);

        map.put("pageSize", pageSize);

        if(isNotEmpty(searchDeptname))
            map.put("searchDeptname", searchDeptname);

        return "department/list_department";
    }

    @ApiOperation(value="删除操作后台所需要的值")
    @ApiImplicitParam(name = "deptId", value = "部门deptId")
    @DeleteMapping(value="/remove/{deptId}")
    public String remove(@PathVariable("deptId") String deptId) {

        departmentService.deleteByPrimaryKey(deptId);

        return "redirect:/department/list";
    }

    @ApiOperation(value="进入部门修改界面")
    @ApiImplicitParam(name = "deptId", value = "部门deptId", required = true, dataType = "String")
    @GetMapping(value="/preUpdate/{deptId}")
    public String preUpdate(@PathVariable("deptId") String deptId, Map<String, Object> map){

        map.put("department", departmentService.selectByPrimaryKey(deptId));

        return "department/update_department";
    }

    @ApiOperation(value="修改操作需要传入后台的值")
    @PutMapping(value="/update")
    public String update(Department department) {

        departmentService.updateByPrimaryKey(department);

        return "redirect:/department/list";
    }



}
