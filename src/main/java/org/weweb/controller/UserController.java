package org.weweb.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.weweb.common.WeUser;
import org.weweb.entity.model.User;
import org.weweb.entity.po.UserPo;
import org.weweb.entity.result.Result;
import org.weweb.entity.result.ResultFactory;
import org.weweb.entity.vo.user.vo.UserVo;
import org.weweb.interceptor.LogDescription;
import org.weweb.interceptor.LogType;
import org.weweb.interceptor.TokenValidate;
import org.weweb.service.UserService;
import org.weweb.util.ConstantUtil;
import org.weweb.util.DozerBeanUtil;
import org.weweb.util.SendmailUtil;
import org.weweb.util.WeUtils;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Controller
@RequestMapping("/user")
public class UserController {
    private static Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    private final Executor executor = Executors.newSingleThreadExecutor();


    // @LogDescription(description = "登录操作")
   /* @RequestMapping("/login")
    public
    @ResponseBody
    String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");// 添加注释
        String password = request.getParameter("password");
        logger.error(username + "---->request params----->" + password);
        boolean flag = false;
        User user = null;
        try {
            user = userService.login(username, password);
            logger.error("user的值------>" + user);
            if (user != null) {//登录成功
                WeUser weUser = new WeUser();
                weUser.setUserid(user.getId());
                weUser.setAccount(user.getAccount());
                weUser.setMobile(user.getMobile());
                weUser.setUsername(user.getUsername());
                //weUser.setEmail(user());
                WeUtils.addWeUser(request, weUser);//设置session的值
                flag = true;
            }
        } catch (Exception e) {
            logger.error("user----->" + user);
            logger.error("=====登录出错了=====", e);
        }
        request.getRequestDispatcher("/welcome.jsp").forward(request, response);
        return JSON.toJSONString(flag);
    }*/
    @LogDescription(type = LogType.LOGIN)
    @RequestMapping("/login")
    public
    @ResponseBody
    String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");// 添加注释
        String password = request.getParameter("password");
        logger.error(account + "---->request params----->" + password);
        UserPo userPo;
        try {
            userPo= userService.login(account, password);
            if (userPo.getUser() == null)
                return JSON.toJSONString(ResultFactory.generateResult(ConstantUtil.LOGIN_FAIL_CODE, ConstantUtil.LOGIN_FAIL_MSG));
            logger.info("user的值------>" + JSON.toJSONString(userPo));
        } catch (Exception e) {
            logger.error("=====登录出错了=====", e);
            return JSON.toJSONString(ResultFactory.generateResult(ConstantUtil.ERROR_CODE, ConstantUtil.ERROR_MSG));

        }
        request.setAttribute("userId",userPo.getUser().getId());
        // request.getRequestDispatcher("/welcome.jsp").forward(request, response);
        return JSON.toJSONString(ResultFactory.generateResult(ConstantUtil.SUCCESS_CODE, ConstantUtil.SUCCESS_MSG, userPo));

    }

    // @LogDescription(description = "退出操作")
    @RequestMapping("/loginout")
    public
    @ResponseBody
    String loginout(HttpServletRequest request) {
        try {
            WeUtils.removeWeUser(request);
        } catch (Exception e) {
            logger.error("=====退出出错了=====", e);
        }
        return JSON.toJSONString(true);
    }


    @RequestMapping("/update")
    public
    @ResponseBody
    String update(HttpServletRequest request, String username, long id) {
        User user = new User();
        user.setId(id);
        //user=userService.get(id);
        user.setUsername(username);
        return JSON.toJSONString(userService.updateUser(user));
    }

    @RequestMapping("/delete")
    public
    @ResponseBody
    String delete(HttpServletRequest request, long id) {
        return JSON.toJSONString(userService.deleteUser(id));
    }

    /**
     * 批量更新操作
     *
     * @param request
     * @return
     */
    @RequestMapping("/batchInsert")
    public
    @ResponseBody
    String batchInsert(HttpServletRequest request) {
        try {
            for (int i = 0; i < 10; i++) {
                User user = new User();
                user.setAge(100);
                user.setAccount("feng" + i);
                user.setPassword("feng" + i);
                user.setAge(10 + i);
                user.setMobile("1804680111" + i);
                user.setUsername("feng" + i);
                userService.add(user, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString(ResultFactory.generateResult(
                    ConstantUtil.ERROR_CODE, ConstantUtil.ERROR_MSG));
        }
        return JSON.toJSONString(ResultFactory.generateResult(
                ConstantUtil.SUCCESS_CODE, ConstantUtil.SUCCESS_MSG));

    }

    @RequestMapping("/handleOrder")
    public
    @ResponseBody
    String handleOrder(HttpServletRequest request) {
        try {
            WeUser weUser = WeUtils.getWeUser(request);
            //修改订单状态
            Thread.sleep(100);
            handleStock();
            executor.execute(() -> {
                sendEmail(weUser);
            });
        } catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString(ResultFactory.generateResult(
                    ConstantUtil.ERROR_CODE, ConstantUtil.ERROR_MSG));
        }
        return JSON.toJSONString(ResultFactory.generateResult(
                ConstantUtil.SUCCESS_CODE, ConstantUtil.SUCCESS_MSG));

    }

    //处理库存
    private void handleStock() {
        try {
            Thread.sleep(100);//模拟业务
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //发送邮件
    private void sendEmail(WeUser weUser) {
        List<String> accounts = Arrays.asList("1932365604@qq.com", "736201041@qq.com");

        for (String account : accounts) {
            SendmailUtil.doSendHtmlEmail("订单信息", account + "你的订单已经发货完成，正在发货，如有疑问请联系XXXX" + (new Date().toString()), account);
        }

    }
    @TokenValidate(userToken=true,guestToken = true)
    @LogDescription(type = LogType.QUERYUSER)
    @RequestMapping("/query")
    public
    @ResponseBody
    String query(HttpServletRequest request) {
        String start = request.getParameter("start");
        String fetch = request.getParameter("fetch");
        if (StringUtil.isEmpty(start))
            start = ConstantUtil.startPage;
        if (StringUtil.isEmpty(fetch))
            fetch = ConstantUtil.endPage;
        PageHelper.startPage(Integer.valueOf(start), Integer.valueOf(fetch));
        Page<User> usersPage = userService.selectPage();
        List<User> users = usersPage.getResult();
        Long count = usersPage.getTotal();
        List<UserVo> userVos = DozerBeanUtil.mapList(users, UserVo.class);
        Result result = ResultFactory.generateResult(ConstantUtil.SUCCESS_CODE,
                String.valueOf(count), userVos);
        logger.debug(JSON.toJSONString(result));
        return JSON.toJSONString(result);
    }

    @RequestMapping("/queryById")
    public
    @ResponseBody
    String queryById(HttpServletRequest request, Long id) {

        User user = userService.get(id);
        UserVo userVo = DozerBeanUtil.map(user, UserVo.class);
        Result result = ResultFactory.generateResult(ConstantUtil.SUCCESS_CODE,
                ConstantUtil.SUCCESS_MSG, userVo);
        return JSON.toJSONString(result);
    }

    @RequestMapping("/geUser")
    public
    @ResponseBody
    String geUser(HttpServletRequest request, String account) {
        User user = userService.geUser(account);
        UserVo userVo = DozerBeanUtil.map(user, UserVo.class);
        Result result = ResultFactory.generateResult(ConstantUtil.SUCCESS_CODE,
                ConstantUtil.SUCCESS_MSG, userVo);
        return JSON.toJSONString(result);
    }
    @RequestMapping("/getJson")
    public
    @ResponseBody
    String getJson(HttpServletRequest request, String account) {
        Map<String,Object> map=new HashMap<>();
        map.put("code",200);
        map.put("message","success");
        return JSON.toJSONString(map);
    }

}
