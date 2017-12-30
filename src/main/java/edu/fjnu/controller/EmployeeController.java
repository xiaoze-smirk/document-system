package edu.fjnu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fjnu.entity.Employee;
import edu.fjnu.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/employee")
public class EmployeeController extends BaseController {

    @Autowired
    private EmployeeService employeeService;

    @ModelAttribute
    public void getEmployee(@RequestParam(value="updateEmpId",required=false) Integer empId,
                          Map<String, Object> map,Employee employee){

        employee=employeeService.selectByPrimaryKey(empId);
        if(empId != null && employee != null)
            map.put("employee", employee);
    }


    @GetMapping("/enter")
    public String enter() {
        return "main";
    }

    @GetMapping("/toInput")
    public String input(Map<String, Object> map) {
        map.put("employee", new Employee());

        return "employee/input_employee";
    }

    @PostMapping(value="/create")
    public String create(Employee employee) {

        employeeService.insert(employee);

        return "redirect:/employee/list";

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
        List<Employee> employeeList = employeeService.findAllEmployee();

        PageInfo<Employee> page=new PageInfo<Employee>(employeeList);

        map.put("page", page);

        return "employee/list_employee";
    }

    @DeleteMapping(value="/remove/{empId}")
    public String remove(@PathVariable("empId") Integer empId) {

        employeeService.deleteByPrimaryKey(empId);

        return "redirect:/employee/list";
    }

    @GetMapping(value="/preUpdate/{empId}")
    public String preUpdate(@PathVariable("empId") Integer empId, Map<String, Object> map){

        map.put("employee", employeeService.selectByPrimaryKey(empId));

        return "employee/update_employee";
    }

    @PutMapping(value="/update")
    public String update(Employee employee) {

        employeeService.updateByPrimaryKey(employee);

        return "redirect:/employee/list";
    }

}
