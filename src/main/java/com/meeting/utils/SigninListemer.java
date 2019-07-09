//package com.meeting.utils;
//
//import com.meeting.bean.UserInfoReturn;
//import com.meeting.service.impl.UserInfoService;
//import org.springframework.context.ApplicationContext;
//import org.springframework.web.context.WebApplicationContext;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.util.*;
//
///***
// * 监听用户签到
// * ***/
//public class SigninListemer implements ServletRequestListener, ServletContextListener {
//    UserInfoService userInfoService;
//    @Override
//    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
//
//    }
//
//    @Override
//    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
//        HttpServletRequest servletRequest=(HttpServletRequest)servletRequestEvent.getServletRequest();
//        if (servletRequest.getRequestURI().equals("/qrcode_success")){
//            getGuestUser getGuestUser=new getGuestUser();
//            String guestname=getGuestUser.getname();
//            List<UserInfoReturn> list = userInfoService.selectUserinfoByUsernameReturn(guestname);
//            guestname=list.get(0).getName();
//            String meeting_id=servletRequest.getQueryString().split("=")[1].split("&")[0];
//            Long timestamp=Long.valueOf(servletRequest.getQueryString().split("=")[2].split("&")[0]);
//            if (timestamp+65000>new Date().getTime()){
//                if (servletRequest.getSession().getServletContext().getAttribute("guestList")==null){
//                    Map guestList = new HashMap();
//                    ArrayList<String> lis = new ArrayList<String>();
//                    lis.add(guestname);
//                    guestList.put(meeting_id,lis);
//                    servletRequest.getSession().getServletContext().setAttribute("guestList",guestList);
//                }else {
//                    Map guestList= (Map) servletRequest.getSession().getServletContext().getAttribute("guestList");
//                    ArrayList<String> lis = (ArrayList<String>) guestList.get(meeting_id);
//                    lis.add(guestname);
//                    guestList.put(meeting_id,lis);
//                    servletRequest.getSession().getServletContext().setAttribute("guestList",guestList);
//                }
//            }
//
//
//        }
////        String serverName=servletRequestEvent.getServletRequest().getServerName();
////         System.out.println("serverName:"+serverName);
//        //serverPort
////        int serverPort=servletRequestEvent.getServletRequest().getServerPort();
////        System.out.println("serverPort:"+serverPort);
//         //requestURI
////        String requestURI=servletRequest.getRequestURI();
////        System.out.println("requestURI:"+requestURI);
//         //requestURL
////        String requestURL=servletRequest.getRequestURL().toString();
////        System.out.println("requestURL:"+requestURL);
////         //servletPath
////        String servletPath=servletRequest.getServletPath();
////         System.out.println("servletPath:"+servletPath);
////         //queryString
////         String queryString=servletRequest.getQueryString();
////         System.out.println("queryString:"+queryString);
//
//    }
///***
// * 在监听器李调用service
// * ***/
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        ServletContext servletContext = sce.getServletContext();
//        ApplicationContext context = (ApplicationContext) servletContext.getAttribute(
//                WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
//        //initialize service when spring context initialized,
//        //建立对应的service当spring上下文初始化之后
//        //使用spring框架中已经初始化的memberService
//        userInfoService = (UserInfoService)context.getBean(UserInfoService.class);
//
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent servletContextEvent) {
//
//    }
//}
