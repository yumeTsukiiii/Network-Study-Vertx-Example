package com.yumetsuki.framwork.verticle;

import com.yumetsuki.framwork.annotations.Listen;
import com.yumetsuki.framwork.annotations.Modules;
import com.yumetsuki.framwork.module.Module;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;

import java.lang.reflect.InvocationTargetException;

public class HttpVerticle extends AbstractVerticle {

    private static final int default_port = 8080;

    @Override
    public void start() throws Exception {
        super.start();

        HttpServer server = vertx.createHttpServer();

        Module [] modules = getModules();
        if (modules != null) {
            for (Module module : modules) {
                server.requestHandler(module.routerWrapper);
            }
        }

        server.listen(getListenPort());
    }

    private Module[] getModules()
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Modules modules = this.getClass().getAnnotation(Modules.class);

        if (modules != null) {
            Module [] array = new Module[modules.modules().length];
            for (int i = 0; i < modules.modules().length; i++) {
                array[i] = modules.modules()[i].getConstructor(Vertx.class).newInstance(vertx);
            }
            return array;
        }

        return null;
    }

    private int getListenPort() {
        Listen listen = this.getClass().getAnnotation(Listen.class);

        if (listen != null) {
            return listen.port();
        } else {
            return config().getInteger("port", default_port);
        }
    }



}
