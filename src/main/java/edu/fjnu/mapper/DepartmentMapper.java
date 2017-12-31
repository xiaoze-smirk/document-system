package edu.fjnu.mapper;

import edu.fjnu.entity.Department;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(String deptId);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(String deptId);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> findAllDepartment();
}