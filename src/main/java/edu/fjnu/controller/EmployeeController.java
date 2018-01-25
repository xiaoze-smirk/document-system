package edu.fjnu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fjnu.entity.Department;
import edu.fjnu.entity.Employee;
import edu.fjnu.service.DepartmentService;
import edu.fjnu.service.EmployeeService;
import edu.fjnu.utils.EntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;


@Controller
@Api(tags ="员工管理")
@RequestMapping("/employee")
public class EmployeeController extends BaseController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @ModelAttribute
    public void getEmployee(@RequestParam(value="empId",required=false) Integer empId,
                          Map<String, Object> map,Employee employee){


        if(empId != null)
            employee=employeeService.selectByPrimaryKey(empId);
        if(employee != null)
            map.put("employee", employee);
    }

    @ApiOperation(value="进入主界面")
    @GetMapping("/enter")
    public String enter() {

        return "main";
    }

    @ApiOperation(value="新增员工界面")
    @GetMapping("/toInput")
    public String input(Map<String, Object> map) {
        map.put("employee", new Employee());

        map.put("deptList", departmentService.findAllDepartment());

        return "employee/input_employee";
    }

    @ApiOperation(value="新增操作需要传入后台的值")
    @PostMapping(value="/create")
    public String create(Employee employee) {

        employeeService.insert(employee);

        return "redirect:/employee/list";

    }

    @ApiOperation(value="获取分页列表", notes="用来获取分页列表")
    @ApiImplicitParam(name = "pageNoStr", value = "页码:pageNoStr")
    @GetMapping("/list")
    public String list(Map<String, Object> map, @RequestParam(value="pageNo", required=false, defaultValue="1") String pageNoStr) {

        int pageNo = 1;

        //对 pageNo 的校验
        pageNo = Integer.parseInt(pageNoStr);
        if(pageNo < 1){
            pageNo = 1;
        }

        PageHelper.startPage(pageNo, 3);
        List<Employee> employeeList = employeeService.findAllEmployee();
        List<Department> departmentList = departmentService.findAllDepartment();

        PageInfo<Employee> page=new PageInfo<Employee>(employeeList);

        EntityUtil entityUtil = new EntityUtil();
        String str;
        for(Employee emp:page.getList()) {
            str = entityUtil.str(emp.getEmpDept(),departmentList);
            emp.setEmpDeptName(str);
        }

        map.put("page", page);

        map.put("deptList", departmentService.findAllDepartment());

        return "employee/list_employee";
    }

    @ApiOperation(value="删除操作后台所需要的值")
    @ApiImplicitParam(name = "empId", value = "员工empId", required = true, dataType = "Integer")
    @DeleteMapping(value="/remove/{empId}")
    public String remove(@PathVariable("empId") Integer empId) {

        employeeService.deleteByPrimaryKey(empId);

        return "redirect:/employee/list";
    }

    @ApiOperation(value="进入员工修改界面")
    @ApiImplicitParam(name = "empId", value = "员工empId", required = true, dataType = "Integer")
    @GetMapping(value="/preUpdate/{empId}")
    public String preUpdate(@PathVariable("empId") Integer empId, Map<String, Object> map){

        map.put("employee", employeeService.selectByPrimaryKey(empId));

        map.put("deptList", departmentService.findAllDepartment());

        return "employee/update_employee";
    }

    @ApiOperation(value="修改操作需要传入后台的值")
    @PutMapping(value="/update")
    public String update(Employee employee) {

        employeeService.updateByPrimaryKey(employee);

        return "redirect:/employee/list";
    }

}
