package com.meeting.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meeting.bean.Msg;
import com.meeting.bean.UserInfo;
import com.meeting.bean.UserInfoReturn;
import com.meeting.service.UserInfoService;
import com.meeting.utils.getGuestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/userInfo")
public class UserController {
    @Autowired
    UserInfoService userInfoService;

    @ResponseBody
    @RequestMapping("/findUserInfoReturnByUsernames")
    public Msg findUserInfoReturnByUsernames(HttpServletRequest request){
        String[] usernames = request.getParameterValues("usernames[]");
        List<UserInfoReturn> userInfoReturns = userInfoService.findUserInfoReturnByUsernames(usernames);
        return Msg.success().add("userInfoReturns",userInfoReturns);
    }

    /**
     * 根据条件修改用户数据
     * @param userInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateUser")
    public Msg updateUser(UserInfo userInfo){
        if (userInfoService.updateByExampleSelectiveUser(userInfo))
            return Msg.success().add("msg", "修改成功");
        else
            return Msg.fail().add("msg", "修改失败");
    }

    /**
     * 根据条件删除用户表数据
     * @param userInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public Msg deleteUser(UserInfo userInfo){
        userInfo.setDeleteFlag(true);
        if (userInfoService.updateByExampleSelectiveUser(userInfo)){
            return Msg.success().add("msg", "删除成功");
        }
        else
            return Msg.fail().add("msg", "删除失败");
    }

    /**
     * 删除多个
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteByExampleSelectiveUser/{ids}",method = RequestMethod.POST)
    public Msg updateByExampleSelectiveUser(@PathVariable("ids")String ids){
        List<Integer> del_ids = new ArrayList<Integer>();
        String str_ids[] = ids.split(",");
        for (String string: str_ids){
            del_ids.add(Integer.parseInt(string));
        }
        userInfoService.deleteByExampleUser(del_ids);
        return Msg.success();
    }

    /**
     * 登录
     * @param userInfo
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkUser",method = RequestMethod.POST)
    public Msg checkUser(UserInfo userInfo,HttpSession session){
        String username = userInfo.getUsername();
        String password = userInfo.getPassword();
        if(userInfoService.checkUser(username,password)){
            session.setAttribute("username",username);
            Integer userType = userInfoService.getUserTypeByUsername(username);
            session.setAttribute("userType",userType);
            return Msg.success();
        }else{
            return Msg.fail().add("msg","工号或密码错误");
        }
    }




    /**
     * 分页
     * @param pn
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public Msg User(@RequestParam(value="page",defaultValue="1")Integer pn){
        //引入PageHelper分页插件
        //在查询之前只需要调用,传入页码，以及每页显示的数量
        PageHelper.startPage(pn,15);
        //startPage后面紧跟的这个查询就是一个分页查询
//        List<UserInfoReturn> list  = userInfoService.findAll();
        List<UserInfoReturn> list = userInfoService.selectUserReturn();
//        System.out.println(list.toString());
        //使用PageInfo包装查询后的结果，只需要将PageInfo交给页面就行了
        //封装了详细的页面信息，包括我们查询出来的数据,传入连续显示的页数
        PageInfo page = new PageInfo(list,1);
        return Msg.success().add("userinfo",page);
    }

    /**
     * 添加数据到用户表
     * @param userInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertUser",method = RequestMethod.POST)
    public Msg insertUser(UserInfo userInfo){
        if (userInfoService.insertUser(userInfo))
            return Msg.success().add("msg", "添加成功");
        else
            return Msg.fail().add("msg","添加失败");
    }


    /**
     * 检查用户名是否可用
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkAddUserInfoUsername")
    public Msg checkAddUserInfoUsername(@RequestParam("username")String username){
        boolean UserInfoUsername = userInfoService.checkAddUserInfo(username);
        if (UserInfoUsername)
            return Msg.success();
        else
            return Msg.fail();
    }


    /**
     * 校验添加时手机号是否可用
     * @param phone
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkAddUserInfoPhone")
    public Msg checkAddUserInfoPhone(@RequestParam("phone")String phone){
        boolean UserInfoPhone = userInfoService.checkAddUserinfoPhone(phone);
        if (UserInfoPhone)
            return Msg.success();
        else
            return Msg.fail();
    }

    /**
     * 校验修改时手机号是否重复
     * @param phone
     * @param userinfo
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkUpdateUserinfoPhone")
    public Msg checkUpdateUserinfoPhone(String phone, String userinfo){
        if (userInfoService.checkUpdatePhone(phone, userinfo))
            return Msg.success();
        return Msg.fail();
    }
    /**
     * 登录
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUsername.do")
    public Msg getUsername(HttpSession session){
        String username= (String) session.getAttribute("username");
        List<UserInfoReturn> list = userInfoService.selectUserinfoByUsernameReturn(username);
        return Msg.success().add("userInfo", list.get(0));
    }
    @ResponseBody
    @RequestMapping(value = "/findAllByExample")
    public Msg findAllByExample(@RequestParam(value="name",defaultValue = "")String name
            ,@RequestParam(value="departName",defaultValue = "")String departName
            ,@RequestParam(value = "username",defaultValue = "")String username
            ,@RequestParam(value="page",defaultValue="1")Integer pn){
        PageHelper.startPage(pn,10);
        List<UserInfoReturn> list = userInfoService.findAllByExample(username,name,departName);
        PageInfo page = new PageInfo(list,1);
        return Msg.success().add("userInfoReturn",page);
    }
    @ResponseBody
    @RequestMapping(value = "/getGuestUsername.do")
    public Msg getGuestUsername(HttpSession session){
        getGuestUser getGuestUser=new getGuestUser();
        String username=getGuestUser.getname();
        List<UserInfoReturn> list = userInfoService.selectUserinfoByUsernameReturn(username);
        return Msg.success().add("userInfo", list.get(0));
    }
    @ResponseBody
    @RequestMapping(value = "/onLogin.do")
    public Msg OnLogin(String js_code){
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wxbe64818bb84ee91a&secret=232dd0dc137c8ffe8e84749e1df0140b&js_code="+js_code;
        StringBuilder json = new StringBuilder();
        try {
            URL urlObj = new URL(url);
            URLConnection uc = urlObj.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine = null;
            while((inputLine = in.readLine()) != null){
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return Msg.fail();
        } catch (IOException e) {
            e.printStackTrace();
            return Msg.fail();
        }
        JSONObject jsonObject= JSON.parseObject(json.toString());
        System.out.println(jsonObject);
        List<UserInfo> userInfos=userInfoService.selectUserInfoExample(jsonObject.getString("openid"));
        // type 0 用户不存在
        // type 1 用户存在
        if (userInfos.size()!=0)
            return Msg.success().add("type",1).add("userId",userInfos.get(0).getId());
        else
            return Msg.success().add("type",0);
    }
    @ResponseBody
    @RequestMapping(value = "/log_out.do")
    public Msg log_out(HttpSession session){
        session.removeAttribute("username");
        session.removeAttribute("user_id");
        return Msg.success().add("msg", "退出成功");
    }

}
