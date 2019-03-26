package com.yumetsuki.example;


import com.yumetsuki.example.verticle.ProductVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class Main {
    public static void main(String[] args) {
        //创建一个配置，通常是从配置文件中读取
        JsonObject config = new JsonObject();
        config.put("port", 8083);
        //将config set到DeploymentOptions中
        DeploymentOptions options = new DeploymentOptions();
        options.setConfig(config);
        //部署ProductVerticle，并传入部署时的配置选项
        Vertx.vertx().deployVerticle(ProductVerticle.class.getName(), options);
    }
}
