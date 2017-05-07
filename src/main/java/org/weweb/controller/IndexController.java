package org.weweb.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.weweb.service.TestRegistryService;
import org.weweb.util.FileUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by jackshen on 2017/3/20.
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private TestRegistryService testRegistryService;
    private static Executor executor= Executors.newSingleThreadExecutor();
    private Map<String,String> map=new ConcurrentHashMap<>();
    private List<String> urls=new ArrayList<>();
    private String suffixUrl="http://192.168.1.111:8080/weweb/index/getImageUrl";
    //private String suffixUrl="localhost:8080/index/getImageUrl";


    @RequestMapping("/hello")
    public
    @ResponseBody
    String index() {
        String name = testRegistryService.hello("zz");
        System.out.println("xx==" + name);
        return "";
    }
    @RequestMapping(value = "/loadImage",method = RequestMethod.GET)
    @ResponseBody
    public void loadImage(@PathVariable String url,HttpServletRequest request, HttpServletResponse response) throws Exception {
        //String url="http://192.168.1.111:8080/weweb/image/time.jpeg";
        String path = request.getSession().getServletContext().getRealPath("");
        String realUrl=map.get(url);
        if (realUrl!=null){

        }
        //解密url
        String fileName="/download/cod/time.jpeg";
        String localPath=path+fileName;
        request.getSession().getServletContext().getRealPath(request.getRequestURI());
        FileUtils.download(url,localPath);
        System.out.println("--->"+localPath);

        request.getRequestDispatcher(fileName).forward(request,response);
    }



    @RequestMapping(value = "/listAllImage")
    @ResponseBody
    public String listAllImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getSession().getServletContext().getRealPath("file/cod/");
        for (String url:urls){
            String[] arrays=url.split("/");
            String finalUrl=arrays[arrays.length-1];
            map.put(UUID.randomUUID().toString(),"/file/cod/"+finalUrl);
            FileUtils.download(url,path+finalUrl);
        }

       Set<String> urlSet= map.keySet().parallelStream().map(s->suffixUrl+"?url="+s).collect(Collectors.toSet());
        Map<String,Object> testMap=new HashedMap();
        testMap.put("urls",urlSet);
        testMap.put("mapData",map);
      return JSON.toJSONString(testMap);
    }
    @RequestMapping(value = "/initImageUrl",method = RequestMethod.GET)
    @ResponseBody
    public String initImageUrl(@RequestParam(value = "url") String url, HttpServletRequest request) throws Exception {
        urls.add(url);
        return "sucess";

    }
    @RequestMapping(value = "/getImageUrl",method = RequestMethod.GET)
    @ResponseBody
    public void getImageUrl(@RequestParam(value = "url") String url, HttpServletRequest request,HttpServletResponse response) throws Exception {
        request.getRequestDispatcher(map.get(url)).forward(request,response);
        map.remove(url);
    }
    private String generateImageUrl(){
        return null;
    }
}