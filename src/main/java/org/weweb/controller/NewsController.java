package org.weweb.controller;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.weweb.model.TNews;
import org.weweb.result.Result;
import org.weweb.result.ResultFactory;
import org.weweb.service.NewsService;
import org.weweb.util.ConstantUtil;
import org.weweb.util.DozerBeanUtil;
import org.weweb.util.WeUtils;
import org.weweb.vo.news.result.NewsResult;
import org.weweb.vo.news.vo.NewsVo;

import javax.servlet.http.HttpServletRequest;
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
            logger.debug("addNews===============");
            TNews news = new TNews();
            news.setTitle(vo.getTitle());
            news.setContent(vo.getContent());
            logger.error("news->" + news);
            logger.error("vo--->" + vo);
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
            logger.debug("updateNews===============");
            TNews news = new TNews();
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
            TNews news = newsService.getNews(id);
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
            List<TNews> newsList = newsService.selectAll();
            List<NewsResult> results = DozerBeanUtil.mapList(newsList, NewsResult.class);
            return ResultFactory.generateResult(ConstantUtil.SUCCESS_CODE, ConstantUtil.SUCCESS_MSG, JSON.toJSONString(results));
        } catch (Exception e) {
            logger.error("querynews------>", e);
            return ResultFactory.generateResult(ConstantUtil.ERROR_CODE, ConstantUtil.ERROR_MSG);

        }
    }

}
