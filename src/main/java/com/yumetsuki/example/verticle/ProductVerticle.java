package com.yumetsuki.example.verticle;

import com.yumetsuki.example.domain.req.AddProductReq;
import com.yumetsuki.example.domain.req.DeleteProductReq;
import com.yumetsuki.example.domain.req.ModifyProductReq;
import com.yumetsuki.example.domain.req.QueryProductReq;
import com.yumetsuki.example.service.ProductService;
import com.yumetsuki.example.service.impl.ProductServiceImpl;
import com.yumetsuki.framwork.util.JsonUtil;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;

import java.util.HashMap;
import java.util.Map;


public class ProductVerticle extends AbstractVerticle {

    private ProductService productService;

    @Override
    public void start() throws Exception {
        super.start();
        productService = new ProductServiceImpl(vertx);

        Router router = Router.router(vertx);


        router.route().handler(CorsHandler.create("*")
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.OPTIONS));

        router.route().handler(BodyHandler.create());
        router.post("/product/add").handler(this::addProduct);
        router.post("/product/delete").handler(this::deleteProduct);
        router.post("/product/modify").handler(this::modifyProduct);
        router.post("/product/query").handler(this::queryProduct);
        router.post("/product/all").handler(this::allProducts);

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(config().getInteger("port", 8080));
    }

    private void addProduct(RoutingContext context) {
        AddProductReq req = JsonUtil.fromJson(AddProductReq.class, context.getBodyAsJson());
        if (req != null) {
            productService.addProduct(req, result -> {
                JsonObject resp = new JsonObject();
                HttpServerResponse response = context.response();
                response.putHeader("Content-type", "application/json");
                if (result.succeeded()) {
                    resp.put("code", 0);
                    resp.put("data", "insert success");
                } else {
                    resp.put("code", -1);
                    resp.put("data", "db exec error");
                    response.setStatusCode(500);
                }
                response.end(Json.encode(resp));
            });
        } else {
            JsonObject resp = new JsonObject();
            resp.put("code", -2);
            resp.put("data", "json invaild");
            context.response().setStatusCode(400).end(Json.encode(resp));
        }
    }

    private void deleteProduct(RoutingContext context) {
        DeleteProductReq req = JsonUtil.fromJson(DeleteProductReq.class, context.getBodyAsJson());
        if (req != null) {
            productService.deleteProduct(req.getId(), result -> {
                JsonObject resp = new JsonObject();
                HttpServerResponse response = context.response();
                response.putHeader("Content-type", "application/json");
                if (result.succeeded()) {
                    resp.put("code", 0);
                    resp.put("data", "delete success");
                } else {
                    resp.put("code", -1);
                    resp.put("data", "db exec error");
                    response.setStatusCode(500);
                }
                response.end(Json.encode(resp));
            });
        } else {
            JsonObject resp = new JsonObject();
            resp.put("code", -2);
            resp.put("data", "json invaild");
            context.response().setStatusCode(400).end(Json.encode(resp));
        }
    }

    private void modifyProduct(RoutingContext context) {
        ModifyProductReq req = JsonUtil.fromJson(ModifyProductReq.class, context.getBodyAsJson());
        if (req != null) {
            productService.modifyProduct(req, result -> {
                JsonObject resp = new JsonObject();
                HttpServerResponse response = context.response();
                response.putHeader("Content-type", "application/json");
                if (result.succeeded()) {
                    resp.put("code", 0);
                    resp.put("data", "modify success");
                } else {
                    resp.put("code", -1);
                    resp.put("data", "db exec error");
                    response.setStatusCode(500);
                }
                response.end(Json.encode(resp));
            });
        } else {
            JsonObject resp = new JsonObject();
            resp.put("code", -2);
            resp.put("data", "json invaild");
            context.response().setStatusCode(400).end(Json.encode(resp));
        }
    }

    private void queryProduct(RoutingContext context) {
        QueryProductReq req = JsonUtil.fromJson(QueryProductReq.class, context.getBodyAsJson());
        if (req != null) {
            productService.queryProduct(req.getId(), result -> {
                Map<String ,Object> resp = new HashMap<>();
                HttpServerResponse response = context.response();
                response.putHeader("Content-type", "application/json");
                if (result.succeeded()) {
                    resp.put("code", 0);
                    resp.put("data", result.result());
                } else {
                    resp.put("code", -1);
                    resp.put("data", "db exec error");
                    response.setStatusCode(500);
                }
                response.end(Json.encode(resp));
            });
        } else {
            JsonObject resp = new JsonObject();
            resp.put("code", -2);
            resp.put("data", "json invaild");
            context.response().setStatusCode(400).end(resp.encode());
        }
    }

    private void allProducts(RoutingContext context) {
        productService.allProducts(result -> {
            JsonObject resp = new JsonObject();
            HttpServerResponse response = context.response();
            response.putHeader("Content-type", "application/json");
            if (result.succeeded()) {
                resp.put("code", 0);
                resp.put("data", result.result());
            } else {
                resp.put("code", -1);
                resp.put("data", "db exec error");
                response.setStatusCode(500);
            }
            response.end(Json.encode(resp));
        });

    }

}
