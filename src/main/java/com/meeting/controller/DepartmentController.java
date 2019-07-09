package com.meeting.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meeting.bean.Department;
import com.meeting.bean.DepartmentExample;
import com.meeting.bean.DepartmentReturn;
import com.meeting.bean.Msg;
import com.meeting.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;


    /**
     * 下拉框
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findAllDept",method = RequestMethod.GET)
    public Msg Department(){
        List<DepartmentReturn> list  = departmentService.selectDeptReturn();
        return Msg.success().add("depts", list);
    }



    /**
     * 分页数据
     * @param pn
     * @return
     */
    @ResponseBody
    @RequestMapping("/findDeptPage")
    public Msg findDeptPage(@RequestParam(value="page",defaultValue="1")Integer pn){
        PageHelper.startPage(pn,15);
        List<DepartmentReturn> list  = departmentService.selectDeptReturn();
        PageInfo page = new PageInfo(list,1);
        return Msg.success().add("depts", page);
    }


    /**
     * 添加数据到部门表
     * @param department
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertDepartment",method = RequestMethod.POST)
    public Msg insertDepartment(Department department, HttpSession session){
        department.setDeleteFlag(false);
        if (departmentService.insertDepartment(department))
            return Msg.success().add("msg","添加成功" );
        else
            return Msg.fail().add("msg", "添加失败");
    }

    /**
     * 检验添加时部门名是否重复
     * @param departName
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkAddDepartmentName")
    public Msg checkAddDepartmentName(@RequestParam("departName")String departName){
        boolean DepartmentName = departmentService.checkAddDepartmentName(departName);
        if (DepartmentName)
            return Msg.success();
        else
            return Msg.fail();
    }


    /**
     * 根据ID删除部门表数据
     * @param department
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteDepartment",method = RequestMethod.POST)
    public Msg deleteDepartment(Department department){
        DepartmentExample departmentExample = new DepartmentExample();
        DepartmentExample.Criteria criteria = departmentExample.createCriteria();
        criteria.andIdEqualTo(department.getId());
        department.setDeleteFlag(true);
        if (departmentService.updateDepartment(department, departmentExample))
            return Msg.success().add("msg", "删除成功");
        else
            return Msg.fail().add("msg", "删除失败");
    }

    /**
     * 校验修改时部门名是否重复
     * @param name
     * @param departname
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkUpdateDepartmentName")
    public Msg checkUpdateDepartmentName(String name,String departname){
        if (departmentService.checkUpdateDepartmentName(name,departname))
            return Msg.success();
        return Msg.fail();
    }

    /**
     * 根据条件修改部门表数据
     * @param department
     * @param departmentName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateDepartment")
    public Msg updateDepartment(Department department,String departmentName){
        DepartmentExample example = new DepartmentExample();
        DepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(department.getId());
        departmentService.updateDepartment(department,example);
        return Msg.success();
    }

}
