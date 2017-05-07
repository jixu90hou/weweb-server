package org.weweb.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.weweb.entity.model.News;
import org.weweb.entity.result.Result;
import org.weweb.entity.result.ResultFactory;
import org.weweb.entity.vo.news.result.NewsResult;
import org.weweb.entity.vo.news.vo.NewsVo;
import org.weweb.service.NewsService;
import org.weweb.util.ConstantUtil;
import org.weweb.util.DozerBeanUtil;
import org.weweb.util.WeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {
    private static Logger logger = Logger.getLogger(NewsController.class);
    @Autowired
    private NewsService newsService;

    @RequestMapping("/addnews")
    public
    @ResponseBody
    Result addNews(HttpServletRequest request, NewsVo vo) {
        try {
            vo.setContent("zhangmingoooooo");
            vo.setTitle("主题这样");
            News news = new News();
            news.setTitle(vo.getTitle());
            news.setContent(vo.getContent());
            news.setCreateTime(new Date());
            newsService.add(news, true);
        } catch (Exception e) {
            logger.error("addNews--->", e);
            return ResultFactory.generateResult(ConstantUtil.ERROR_CODE, ConstantUtil.ERROR_MSG);
        }
        return ResultFactory.generateResult(ConstantUtil.SUCCESS_CODE, ConstantUtil.SUCCESS_MSG);

    }

    @RequestMapping("/updatenews")
    public
    @ResponseBody
    Result updateNews(HttpServletRequest request, NewsVo vo) {
        try {
            News news = new News();
            news.setId(vo.getId());
            news.setTitle(vo.getTitle());
            news.setContent(vo.getContent());
            newsService.updateNews(news);
        } catch (Exception e) {
            logger.error("updateNews--->", e);
            return ResultFactory.generateResult(ConstantUtil.ERROR_CODE, ConstantUtil.ERROR_MSG);
        }
        return ResultFactory.generateResult(ConstantUtil.SUCCESS_CODE, ConstantUtil.SUCCESS_MSG);

    }

    @RequestMapping("/querynewsbyid")
    public
    @ResponseBody
    Result queryNewsById(HttpServletRequest request, long id) {

        if (!WeUtils.isNotNull(id)) {
            return ResultFactory.generateResult(ConstantUtil.REQUEST_EMPTY_CODE, ConstantUtil.REQUEST_EMPTY_MSG);
        }
        try {
            News news = newsService.getNews(id);
            NewsResult newsResult = DozerBeanUtil.map(news, NewsResult.class);
            return ResultFactory.generateResult(ConstantUtil.SUCCESS_CODE, ConstantUtil.SUCCESS_MSG, JSON.toJSONString(newsResult));
        } catch (Exception e) {
            logger.error("querynewsbyid------>", e);
            return ResultFactory.generateResult(ConstantUtil.ERROR_CODE, ConstantUtil.ERROR_MSG);
        }

    }

    @RequestMapping("/querynews")
    public
    @ResponseBody
    Result queryNews(HttpServletRequest request) {
        try {
            List<News> newsList = newsService.selectAll();
            List<NewsResult> results = DozerBeanUtil.mapList(newsList, NewsResult.class);
            return ResultFactory.generateResult(ConstantUtil.SUCCESS_CODE, ConstantUtil.SUCCESS_MSG, JSON.toJSONString(results));
        } catch (Exception e) {
            logger.error("querynews------>", e);
            return ResultFactory.generateResult(ConstantUtil.ERROR_CODE, ConstantUtil.ERROR_MSG);

        }
    }

    @RequestMapping(value = "/listNews", method = RequestMethod.GET)
    public
    @ResponseBody
    void listNews(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<News> newsList = newsService.selectAll();
            List<NewsResult> results = DozerBeanUtil.mapList(newsList, NewsResult.class);
            request.setAttribute("newsList", results);
            request.getRequestDispatcher("/news.jsp").forward(request, response);
        } catch (Exception e) {
            logger.error("listNews------>", e);

        }
    }

    @RequestMapping(value = "/findNewsDetail", method = RequestMethod.GET)
    public
    @ResponseBody
    void findNewsDetail(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter("id");
            logger.error("id-------->:" + id);
            if (StringUtils.isNotBlank(id)) {
                News news = newsService.getNews(Long.valueOf(id));
                request.setAttribute("news", news);
                request.getRequestDispatcher("/newsDetail.jsp").forward(request, response);
            }
        } catch (Exception e) {
            logger.error("findNewsDetail------>", e);

        }
    }

    //http://cgs1999.iteye.com/blog/1547197
    @RequestMapping("/test")
    public void test() throws MyException {
        throw new MyException("新的异常");
    }
}

class MyException extends Throwable {
    public MyException(String exception) {
        System.out.println("MyException:" + exception);
    }
}
