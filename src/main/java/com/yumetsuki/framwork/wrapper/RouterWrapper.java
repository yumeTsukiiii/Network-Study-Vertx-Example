package com.yumetsuki.framwork.wrapper;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.util.List;
import java.util.stream.Collectors;

public class RouterWrapper implements Router {

    private Router router;
    
    public RouterWrapper(Router router) {
        this.router = router;
    }

    public RouterWrapper mountSubRouterWrapper(String s, RouterWrapper router) {
        return new RouterWrapper(router.mountSubRouter(s, router.router));
    }

    public List<RouteWrapper> getRouteWrappers() {
        return router.getRoutes().stream().map(RouteWrapper::new).collect(Collectors.toList());
    }

    @Override
    public RouteWrapper route() { return new RouteWrapper( router.route()); }

    @Override
    public RouteWrapper route(HttpMethod httpMethod, String s) {
        return new RouteWrapper(router.route(httpMethod, s));
    }

    @Override
    public RouteWrapper route(String s) {
        return new RouteWrapper(router.route(s));
    }

    @Override
    public RouteWrapper routeWithRegex(HttpMethod httpMethod, String s) {
        return new RouteWrapper(router.routeWithRegex(httpMethod, s));
    }

    @Override
    public RouteWrapper routeWithRegex(String s) {
        return new RouteWrapper(router.routeWithRegex(s));
    }

    @Override
    public RouteWrapper get() {
        return new RouteWrapper(router.get());
    }

    @Override
    public RouteWrapper get(String s) {
        return new RouteWrapper(router.get(s));
    }

    @Override
    public RouteWrapper getWithRegex(String s) {
        return new RouteWrapper(router.getWithRegex(s));
    }

    @Override
    public RouteWrapper head() {
        return new RouteWrapper(router.head());
    }

    @Override
    public RouteWrapper head(String s) {
        return new RouteWrapper(router.head(s));
    }

    @Override
    public RouteWrapper headWithRegex(String s) {
        return new RouteWrapper(router.headWithRegex(s));
    }

    @Override
    public RouteWrapper options() {
        return new RouteWrapper(router.options());
    }

    @Override
    public RouteWrapper options(String s) {
        return new RouteWrapper(router.options(s));
    }

    @Override
    public RouteWrapper optionsWithRegex(String s) {
        return new RouteWrapper(router.headWithRegex(s));
    }

    @Override
    public RouteWrapper put() {
        return new RouteWrapper(router.put());
    }

    @Override
    public RouteWrapper put(String s) {
        return new RouteWrapper(router.put(s));
    }

    @Override
    public RouteWrapper putWithRegex(String s) {
        return new RouteWrapper(router.putWithRegex(s));
    }

    @Override
    public RouteWrapper post() {
        return new RouteWrapper(router.post());
    }

    @Override
    public RouteWrapper post(String s) {
        return new RouteWrapper(router.post(s));
    }

    @Override
    public RouteWrapper postWithRegex(String s) {
        return new RouteWrapper(router.postWithRegex(s));
    }

    @Override
    public RouteWrapper delete() {
        return new RouteWrapper(router.delete());
    }

    @Override
    public RouteWrapper delete(String s) {
        return new RouteWrapper(router.delete(s));
    }

    @Override
    public RouteWrapper deleteWithRegex(String s) {
        return new RouteWrapper(router.deleteWithRegex(s));
    }

    @Override
    public RouteWrapper trace() {
        return new RouteWrapper(router.trace());
    }

    @Override
    public RouteWrapper trace(String s) {
        return new RouteWrapper(router.trace(s));
    }

    @Override
    public RouteWrapper traceWithRegex(String s) {
        return new RouteWrapper(router.traceWithRegex(s));
    }

    @Override
    public RouteWrapper connect() {
        return new RouteWrapper(router.connect());
    }

    @Override
    public RouteWrapper connect(String s) {
        return new RouteWrapper(router.connect(s));
    }

    @Override
    public RouteWrapper connectWithRegex(String s) {
        return new RouteWrapper(router.connectWithRegex(s));
    }

    @Override
    public RouteWrapper patch() {
        return new RouteWrapper(router.patch());
    }

    @Override
    public RouteWrapper patch(String s) {
        return new RouteWrapper(router.patch(s));
    }

    @Override
    public RouteWrapper patchWithRegex(String s) {
        return new RouteWrapper(router.patchWithRegex(s));
    }

    @Override
    public List<Route> getRoutes() {
        return router.getRoutes();
    }

    @Override
    public RouterWrapper clear() {
        return new RouterWrapper(router.clear());
    }

    @Override
    public RouterWrapper mountSubRouter(String s, Router router) {
        return new RouterWrapper(router.mountSubRouter(s, router));
    }

    @Override
    public RouterWrapper exceptionHandler(Handler<Throwable> handler) {
        return new RouterWrapper(router.exceptionHandler(handler));
    }

    @Override
    public RouterWrapper errorHandler(int i, Handler<RoutingContext> handler) {
        return new RouterWrapper(router.errorHandler(i, handler));
    }

    @Override
    public void handleContext(RoutingContext routingContext) {
        router.handleContext(routingContext);
    }

    @Override
    public void handleFailure(RoutingContext routingContext) {
        router.handleFailure(routingContext);
    }

    @Override
    public void handle(HttpServerRequest httpServerRequest) {
        router.handle(httpServerRequest);
    }

    @Override
    public void accept(HttpServerRequest request) {
        router.accept(request);
    }
}
