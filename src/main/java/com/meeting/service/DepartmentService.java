package com.meeting.service;



import com.meeting.bean.Department;
import com.meeting.bean.DepartmentExample;
import com.meeting.bean.DepartmentReturn;

import com.meeting.dao.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    public List<DepartmentReturn> selectDeptReturn(){
        return departmentMapper.selectDeptReturn();
    }

    public boolean insertDepartment(Department department) {
        if (departmentMapper.insert(department) == 1)
            return true;
        else
            return false;
    }

    public boolean checkAddDepartmentName(String departName) {
        DepartmentExample departmentExample = new DepartmentExample();
        DepartmentExample.Criteria criteria = departmentExample.createCriteria();
        criteria.andNameEqualTo(departName);
        criteria.andDeleteFlagEqualTo(false);
        long count = departmentMapper.countByExample(departmentExample);
        return count == 0;
    }
    public boolean updateDepartment(Department department, DepartmentExample example) {
        if (departmentMapper.updateByExampleSelective(department, example) == 1)
            return true;
        else
            return false;
    }

    public boolean checkUpdateDepartmentName(String name, String department) {
        Integer id = departmentMapper.checkUpdateDepartmentName(department).get(0).getId();
        List<Department> departments = departmentMapper.checkUpdateDepartmentName(name);
        if (departments.size() == 0)
            return true;
        for (Department lis : departments) {
            if (lis.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public Department selectByPrimaryKey(Integer id) {
        return departmentMapper.selectByPrimaryKey(id);
    }
    public Integer getDepartId(String deptName){
        DepartmentExample departmentExample = new DepartmentExample();
        DepartmentExample.Criteria criteria = departmentExample.createCriteria();
        criteria.andNameEqualTo(deptName);
        return departmentMapper.selectByExample(departmentExample).get(0).getId();
    }
    public Department selectDepartment(Integer id) {
        return departmentMapper.selectByPrimaryKey(id);
    }
}


