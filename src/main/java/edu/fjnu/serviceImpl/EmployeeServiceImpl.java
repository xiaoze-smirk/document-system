package edu.fjnu.serviceImpl;

import edu.fjnu.entity.Employee;
import edu.fjnu.mapper.EmployeeMapper;
import edu.fjnu.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public void insert(Employee record) {
        employeeMapper.insert(record);
    }

    @Override
    public void insertSelective(Employee record) {
        employeeMapper.insertSelective(record);
    }

    @Override
    public void deleteByPrimaryKey(Integer empId) {
        employeeMapper.deleteByPrimaryKey(empId);
    }

    @Override
    public void updateByPrimaryKeySelective(Employee record) {
        employeeMapper.updateByPrimaryKeySelective(record);

    }

    @Override
    public void updateByPrimaryKey(Employee record) {
        employeeMapper.updateByPrimaryKey(record);
    }

    @Override
    public Employee selectByPrimaryKey(Integer empId) {
        return employeeMapper.selectByPrimaryKey(empId);
    }

    @Override
    public List<Employee> findAllEmployee() {
        return employeeMapper.findAllEmployee();
    }

    @Override
    public List<Employee> findByEmpName(Map map) {
        return employeeMapper.findByEmpName(map);
    }
}
