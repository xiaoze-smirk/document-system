package edu.fjnu.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fjnu.entity.Department;
import edu.fjnu.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Controller
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

    @GetMapping("/toInput")
    public String input(Map<String, Object> map) {
        map.put("department", new Department());

        return "department/input_department";
    }

    @PostMapping(value="/create")
    public String create(Department department) {

        departmentService.insert(department);

        return "redirect:/department/list";

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
        List<Department> departmentList = departmentService.findAllDepartment();

        PageInfo<Department> page=new PageInfo<Department>(departmentList);

        map.put("page", page);

        return "department/list_department";
    }

    @DeleteMapping(value="/remove/{deptId}")
    public String remove(@PathVariable("deptId") String deptId) {

        departmentService.deleteByPrimaryKey(deptId);

        return "redirect:/department/list";
    }

    @GetMapping(value="/preUpdate/{deptId}")
    public String preUpdate(@PathVariable("deptId") String deptId, Map<String, Object> map){

        map.put("department", departmentService.selectByPrimaryKey(deptId));

        return "department/update_department";
    }

    @PutMapping(value="/update")
    public String update(Department department) {

        departmentService.updateByPrimaryKey(department);

        return "redirect:/department/list";
    }



}
