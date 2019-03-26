package com.yumetsuki.example.dao;

import com.yumetsuki.example.entities.Product;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;

import java.util.List;

public interface ProductDao {

    Future<Void> addProduct(Product product);

    Future<Void> deleteProduct(int id);

    Future<Void> modifyProduct(Product product);

    Future<Product> queryProduct(int id);

    Future<List<Product>> allProducts();

}
