package com.yumetsuki.example.service.impl;

import com.yumetsuki.example.dao.ProductDao;
import com.yumetsuki.example.dao.impl.ProductDaoImpl;
import com.yumetsuki.example.domain.req.AddProductReq;
import com.yumetsuki.example.domain.req.ModifyProductReq;
import com.yumetsuki.example.entities.Product;
import com.yumetsuki.example.service.ProductService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    public ProductServiceImpl(Vertx vertx) {
        productDao = new ProductDaoImpl(vertx);
    }

    @Override
    public void addProduct(AddProductReq req, Handler<AsyncResult<Void>> handler) {
        productDao.addProduct(new Product(req.getName(), req.getCategory(), req.getDetail()))
                .setHandler(handler);
    }

    @Override
    public void deleteProduct(int id, Handler<AsyncResult<Void>> handler) {
        productDao.deleteProduct(id).setHandler(handler);
    }

    @Override
    public void modifyProduct(ModifyProductReq req, Handler<AsyncResult<Void>> handler) {
        productDao.modifyProduct(
                new Product(req.getId(), req.getName(), req.getCategory(), req.getDetail()))
                .setHandler(handler);
    }

    @Override
    public void queryProduct(int id, Handler<AsyncResult<Product>> handler) {
        productDao.queryProduct(id).setHandler(handler);
    }

    @Override
    public void allProducts(Handler<AsyncResult<List<Product>>> handler) {
        productDao.allProducts().setHandler(handler);
    }
}
