package org.weweb.service;

import org.springframework.stereotype.Service;
import org.weweb.entity.model.ProductStock;
import org.weweb.mapper.ProductStockMapper;

@Service
public class ProductStockService extends BaseService<ProductStock, ProductStockMapper> {
    public int deductStock(Long productId){
        return dao.deductStock(productId);
    }
}
