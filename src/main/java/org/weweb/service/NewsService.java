package org.weweb.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.weweb.entity.model.News;
import org.weweb.mapper.NewsMapper;

@Service
public class NewsService extends BaseService<News, NewsMapper> {
    //@Cacheable(value = "getNews", key = "#id")
    public News getNews(Long id) {
        return super.get(id);
    }

    @CacheEvict(value = "updateNews", key = "#news.id")
    public int updateNews(News news) {
        return update(news, true);
    }

}
