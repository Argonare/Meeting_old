package com.meeting.service;

import com.meeting.bean.Department;
import com.meeting.bean.DepartmentReturn;
import com.meeting.dao.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    public Department selectByPrimaryKey(Integer id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    public List<DepartmentReturn> selectDeptReturn() {
        return departmentMapper.selectDeptReturn();
    }
}
