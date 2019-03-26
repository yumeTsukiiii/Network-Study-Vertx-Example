package com.yumetsuki.framwork.wrapper;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.RoutingContext;

import java.util.List;

public class RouteWrapper implements Route {

    private Route route;

    public RouteWrapper(Route route) {
        this.route = route;
    }

    public Route requestHandler(Handler<Call> handler) {
        return route.handler(routingContext -> handler.handle(new Call(routingContext)));
    }

    public Route blockingRequestHandler(Handler<RoutingContext> handler) {
        return route.blockingHandler(routingContext -> handler.handle(new Call(routingContext)));
    }

    public Route blockingRequestHandler(Handler<RoutingContext> handler, boolean b) {
        return route.blockingHandler(routingContext -> handler.handle(new Call(routingContext)), b);
    }

    public Route failureRequestHandler(Handler<RoutingContext> handler) {
        return route.failureHandler(routingContext -> handler.handle(new Call(routingContext)));
    }

    @Override
    public Route method(HttpMethod httpMethod) {
        return route.method(httpMethod);
    }

    @Override
    public Route path(String s) {
        return route.path(s);
    }

    @Override
    public Route pathRegex(String s) {
        return route.pathRegex(s);
    }

    @Override
    public Route produces(String s) {
        return route.pathRegex(s);
    }

    @Override
    public Route consumes(String s) {
        return route.consumes(s);
    }

    @Override
    public Route order(int i) {
        return route.order(i);
    }

    @Override
    public Route last() {
        return route.last();
    }

    @Override
    public Route handler(Handler<RoutingContext> handler) {
        return route.handler(handler);
    }

    @Override
    public Route blockingHandler(Handler<RoutingContext> handler) {
        return route.blockingHandler(handler);
    }

    @Override
    public Route blockingHandler(Handler<RoutingContext> handler, boolean b) {
        return route.blockingHandler(handler, b);
    }

    @Override
    public Route failureHandler(Handler<RoutingContext> handler) {
        return route.failureHandler(handler);
    }

    @Override
    public Route remove() {
        return route.remove();
    }

    @Override
    public Route disable() {
        return route.disable();
    }

    @Override
    public Route enable() {
        return route.enable();
    }

    @Override
    public Route useNormalisedPath(boolean b) {
        return route.useNormalisedPath(b);
    }

    @Override
    public String getPath() {
        return route.getPath();
    }

    @Override
    public Route setRegexGroupsNames(List<String> list) {
        return route.setRegexGroupsNames(list);
    }
}
