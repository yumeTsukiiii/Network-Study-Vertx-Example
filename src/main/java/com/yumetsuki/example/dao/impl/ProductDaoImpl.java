package com.yumetsuki.example.dao.impl;

import com.yumetsuki.example.dao.ProductDao;
import com.yumetsuki.example.entities.Product;
import com.yumetsuki.framwork.util.JsonUtil;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.asyncsql.MySQLClient;
import io.vertx.ext.sql.SQLConnection;

import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoImpl implements ProductDao {

    private AsyncSQLClient client;

    public ProductDaoImpl(Vertx vertx) {
        JsonObject config = new JsonObject();
        config.put("username", "root");
        config.put("password", "shinku520");
        config.put("database", "study");
        config.put("charset", "UTF-8");
        this.client = MySQLClient.createShared(vertx, config);
    }

    @Override
    public Future<Void> addProduct(Product product) {
        Future<Void> future = Future.future();
        client.getConnection(connectionResult -> {
            if (connectionResult.succeeded()) {
                SQLConnection connection = connectionResult.result();

                JsonArray array = new JsonArray();
                array.add(product.getName());
                array.add(product.getCategory());
                array.add(product.getDetail());

                connection.updateWithParams("insert into product (name,category,detail) values(?,?,?)", array, updateResult -> {
                    if (updateResult.succeeded()) {
                        future.complete();
                    } else {
                        future.fail(updateResult.cause());
                    }
                    connection.close();
                });

            } else {
                future.fail(connectionResult.cause());
            }
        });
        return future;
    }

    @Override
    public Future<Void> deleteProduct(int id) {
        Future<Void> future = Future.future();
        client.getConnection(connectionResult -> {
            if (connectionResult.succeeded()) {
                SQLConnection connection = connectionResult.result();

                JsonArray array = new JsonArray();
                array.add(id);

                connection.updateWithParams("delete from product where id=?", array, updateResult -> {
                    if (updateResult.succeeded()) {
                        future.complete();
                    } else {
                        future.fail(updateResult.cause());
                    }
                    connection.close();
                });

            } else {
                future.fail(connectionResult.cause());
            }
        });
        return future;
    }

    @Override
    public Future<Void> modifyProduct(Product product) {
        Future<Void> future = Future.future();
        client.getConnection(connectionResult -> {
            if (connectionResult.succeeded()) {
                SQLConnection connection = connectionResult.result();

                JsonArray array = new JsonArray();
                array.add(product.getName());
                array.add(product.getCategory());
                array.add(product.getDetail());
                array.add(product.getId());

                connection.updateWithParams("update product set name=?,category=?,detail=? where id=?", array, updateResult -> {
                    if (updateResult.succeeded()) {
                        future.complete();
                    } else {
                        future.fail(updateResult.cause());
                    }
                    connection.close();
                });

            } else {
                future.fail(connectionResult.cause());
            }
        });
        return future;
    }

    @Override
    public Future<Product> queryProduct(int id) {
        Future<Product> future = Future.future();
        client.getConnection(connectionResult -> {
            if (connectionResult.succeeded()) {
                SQLConnection connection = connectionResult.result();

                JsonArray array = new JsonArray();
                array.add(id);

                connection.queryWithParams("select id,name,category,detail from product where id=?", array, queryResult -> {
                    if (queryResult.succeeded()) {
                        List<JsonObject> list = queryResult.result().getRows();
                        if (list.size() > 0) {
                            future.complete(JsonUtil.fromJson(Product.class, list.get(0)));
                        } else {
                            future.complete(null);
                        }
                     } else {
                        future.fail(queryResult.cause());
                    }
                    connection.close();
                });

            } else {
                future.fail(connectionResult.cause());
            }
        });
        return future;
    }

    @Override
    public Future<List<Product>> allProducts() {
        Future<List<Product>> future = Future.future();
        client.getConnection(connectionResult -> {
            if (connectionResult.succeeded()) {
                SQLConnection connection = connectionResult.result();

                connection.query("select id,name,category,detail from product", queryResult -> {
                    if (queryResult.succeeded()) {
                        List<JsonObject> list = queryResult.result().getRows();
                        future.complete(list.stream()
                                .map(item -> JsonUtil.fromJson(Product.class, item))
                                .collect(Collectors.toList()));
                    } else {
                        future.fail(queryResult.cause());
                    }
                    connection.close();
                });

            } else {
                future.fail(connectionResult.cause());
            }
        });
        return future;
    }
}
