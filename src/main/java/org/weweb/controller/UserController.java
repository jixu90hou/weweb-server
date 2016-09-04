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
import org.weweb.interceptor.AuthPassport;
import org.weweb.interceptor.LogDescription;
import org.weweb.model.TUser;
import org.weweb.result.Result;
import org.weweb.result.ResultFactory;
import org.weweb.service.UserService;
import org.weweb.util.ConstantUtil;
import org.weweb.util.DozerBeanUtil;
import org.weweb.util.WeUtils;
import org.weweb.vo.user.vo.UserVo;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private static Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private UserService userService;


    @LogDescription(description = "登录操作")
    @RequestMapping("/login")
    public
    @ResponseBody
    String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");// 添加注释
        String password = request.getParameter("password");
        logger.error(username + "---->request params----->" + password);
        boolean flag = false;
        TUser user = null;
        try {
            user = userService.login(username, password);
            logger.error("user的值------>" + user);
            if (user != null) {//登录成功
                WeUser weUser = new WeUser();
                weUser.setUserid(user.getId());
                weUser.setAccount(user.getAccount());
                weUser.setMobile(user.getMobile());
                weUser.setUsername(user.getUsername());
                WeUtils.addWeUser(request, weUser);//设置session的值
                flag = true;
            }
        } catch (Exception e) {
            logger.error("user----->" + user);
            logger.error("=====登录出错了=====", e);
        }
        request.getRequestDispatcher("/welcome.jsp").forward(request, response);
        return JSON.toJSONString(flag);
    }

    @LogDescription(description = "退出操作")
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
    String update(HttpServletRequest request,String username,long id) {
        TUser user = new TUser();
        user.setId(id);
        //user=userService.get(id);
        user.setUsername(username);
        return JSON.toJSONString(userService.updateUser(user));
    }

    @RequestMapping("/delete")
    public
    @ResponseBody
    String delete(HttpServletRequest request,long id) {
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
                TUser user = new TUser();
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

    @LogDescription(description = "查询操作")
    @AuthPassport
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
        Page<TUser> usersPage = userService.selectPage();
        List<TUser> users = usersPage.getResult();
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

        TUser user = userService.get(id);
        UserVo userVo = DozerBeanUtil.map(user, UserVo.class);
        Result result = ResultFactory.generateResult(ConstantUtil.SUCCESS_CODE,
                ConstantUtil.SUCCESS_MSG, userVo);
        return JSON.toJSONString(result);
    }

    @RequestMapping("/getuser")
    public
    @ResponseBody
    String getUser(HttpServletRequest request, String account) {
        TUser user = userService.getUser(account);
        UserVo userVo = DozerBeanUtil.map(user, UserVo.class);
        Result result = ResultFactory.generateResult(ConstantUtil.SUCCESS_CODE,
                ConstantUtil.SUCCESS_MSG, userVo);
        return JSON.toJSONString(result);
    }


}
