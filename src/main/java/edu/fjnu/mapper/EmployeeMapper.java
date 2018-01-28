package edu.fjnu.mapper;

import edu.fjnu.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    void insert(Employee record);

    void insertSelective(Employee record);

    void deleteByPrimaryKey(Integer empId);

    void updateByPrimaryKeySelective(Employee record);

    void updateByPrimaryKey(Employee record);

    Employee selectByPrimaryKey(Integer empId);

    List<Employee> findAllEmployee();

    List<Employee> findByEmpName(Map map);
}