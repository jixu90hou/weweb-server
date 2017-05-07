package org.weweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.weweb.entity.model.News;
import org.weweb.entity.model.Product;
import org.weweb.entity.model.ProductStock;
import org.weweb.mapper.NewsMapper;
import org.weweb.mapper.ProductMapper;

import java.util.Date;

@Service
public class ProductService extends BaseService<Product, ProductMapper> {
    @Autowired
    private ProductStockService productStockService;
    @Transactional
    public void addData(int i){
        Product product=new Product();
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        product.setProductName("xiaomi"+(i+1));
        add(product,true);
        ProductStock productStock=new ProductStock();
        productStock.setProductId(product.getId());
        productStock.setUpdateTime(new Date());
        productStock.setStockNumber(100-i*5);
        productStockService.add(productStock,true);

    }
}
