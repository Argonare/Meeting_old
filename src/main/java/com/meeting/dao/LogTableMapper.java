package com.meeting.dao;

import com.meeting.bean.LogTable;
import com.meeting.bean.LogTableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogTableMapper {
    long countByExample(LogTableExample example);

    int deleteByExample(LogTableExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LogTable record);

    int insertSelective(LogTable record);

    List<LogTable> selectByExample(LogTableExample example);

    LogTable selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LogTable record, @Param("example") LogTableExample example);

    int updateByExample(@Param("record") LogTable record, @Param("example") LogTableExample example);

    int updateByPrimaryKeySelective(LogTable record);

    int updateByPrimaryKey(LogTable record);
}