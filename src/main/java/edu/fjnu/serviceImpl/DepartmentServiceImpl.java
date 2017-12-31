package edu.fjnu.serviceImpl;

import edu.fjnu.entity.Department;
import edu.fjnu.mapper.DepartmentMapper;
import edu.fjnu.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;


    @Override
    public void insert(Department record) {
        departmentMapper.insert(record);
    }

    @Override
    public void insertSelective(Department record) {
        departmentMapper.insertSelective(record);
    }

    @Override
    public void deleteByPrimaryKey(String deptId) {
        departmentMapper.deleteByPrimaryKey(deptId);
    }

    @Override
    public void updateByPrimaryKeySelective(Department record) {
        departmentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void updateByPrimaryKey(Department record) {
        departmentMapper.updateByPrimaryKey(record);
    }

    @Override
    public Department selectByPrimaryKey(String deptId) {
        return departmentMapper.selectByPrimaryKey(deptId);
    }

    @Override
    public List<Department> findAllDepartment() {
        return  departmentMapper.findAllDepartment();
    }


}
