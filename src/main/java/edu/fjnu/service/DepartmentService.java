package edu.fjnu.service;

import edu.fjnu.entity.Department;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    void insert(Department record);

    void insertSelective(Department record);

    void deleteByPrimaryKey(String deptId);

    void updateByPrimaryKeySelective(Department record);

    void updateByPrimaryKey(Department record);

    Department selectByPrimaryKey(String deptId);

    List<Department> findAllDepartment();

    List<Department> findByDeptName(Map map);

}
