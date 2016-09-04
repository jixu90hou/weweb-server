package org.weweb.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Service;
import org.weweb.mapper.TNewsMapper;
import org.weweb.model.TNews;

@Service
public class NewsService extends BaseService<TNews,TNewsMapper> {
    @Cacheable(value = "getNews",key = "#id")
    public TNews getNews(Long id){
        return super.get(id);
    }
    @CacheEvict(value = "updateNews",key = "#news.id")
    public int updateNews(TNews news){
        return update(news,true);
    }

}
