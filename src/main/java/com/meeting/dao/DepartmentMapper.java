package com.meeting.dao;

import com.meeting.bean.Department;
import com.meeting.bean.DepartmentExample;
import java.util.List;

import com.meeting.bean.DepartmentReturn;
import org.apache.ibatis.annotations.Param;

public interface DepartmentMapper {
    List<DepartmentReturn> selectDeptReturn();

    long countByExample(DepartmentExample example);

    int deleteByExample(DepartmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    List<Department> selectByExample(DepartmentExample example);

    Department selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByExample(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> checkUpdateDepartmentName(String name);
}