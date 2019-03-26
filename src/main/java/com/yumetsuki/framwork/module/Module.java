package com.yumetsuki.framwork.module;

import com.yumetsuki.framwork.wrapper.RouterWrapper;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;

import java.util.HashSet;
import java.util.Set;

public abstract class Module {

    public RouterWrapper routerWrapper;

    private boolean enableCors = true;

    private boolean enableBodyHandler = true;

    public Module(Vertx vertx) {
        routerWrapper = new RouterWrapper(Router.router(vertx));
        initRouter(routerWrapper);
        enableBodyHandler();
        enableCors();
    }

    protected abstract void initRouter(RouterWrapper router);

    protected void enableCors(boolean enabled) {
        this.enableCors = enabled;
    }

    protected void enableBodyHandler(boolean enabled) {
        this.enableBodyHandler = enabled;
    }

    private void enableCors() {
        if (enableCors) {
            Set<HttpMethod> methods = new HashSet<>();
            methods.add(HttpMethod.OPTIONS);
            methods.add(HttpMethod.GET);
            methods.add(HttpMethod.POST);
            methods.add(HttpMethod.DELETE);
            methods.add(HttpMethod.PUT);
            methods.add(HttpMethod.PATCH);
            routerWrapper.route().handler(CorsHandler.create("*").allowedMethods(methods));
        }
    }

    private void enableBodyHandler() {
        if (enableBodyHandler) {
            routerWrapper.route().handler(BodyHandler.create());
        }
    }

}
