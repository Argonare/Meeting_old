package com.meeting.controller;

import com.meeting.bean.DepartmentReturn;
import com.meeting.bean.Msg;
import com.meeting.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    /**
     * 查询部门表全部信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findAllDept",method = RequestMethod.GET)
    public Msg Department(){
        List<DepartmentReturn> list  = departmentService.selectDeptReturn();
        return Msg.success().add("depts", list);
    }
}
