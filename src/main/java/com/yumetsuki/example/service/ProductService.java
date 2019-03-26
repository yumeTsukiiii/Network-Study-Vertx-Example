package com.yumetsuki.example.service;

import com.yumetsuki.example.domain.req.AddProductReq;
import com.yumetsuki.example.domain.req.ModifyProductReq;
import com.yumetsuki.example.entities.Product;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

import java.util.List;

public interface ProductService {

    void addProduct(AddProductReq req, Handler<AsyncResult<Void>> handler);

    void deleteProduct(int id, Handler<AsyncResult<Void>> handler);

    void modifyProduct(ModifyProductReq req, Handler<AsyncResult<Void>> handler);

    void queryProduct(int id, Handler<AsyncResult<Product>> handler);

    void allProducts(Handler<AsyncResult<List<Product>>> handler);
}
