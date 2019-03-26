package com.yumetsuki.framwork.wrapper;

import com.yumetsuki.framwork.util.JsonUtil;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 该类对RoutingContext进行包装，扩展对Body的Json序列化的支持以及其它功能
 * @author yumetsuki
 * */
public class Call implements RoutingContext {

    private RoutingContext context;

    public Call(RoutingContext context) {
        this.context = context;
    }

    /**
     * 将body转换为一个实体类
     * @param tClass 实体类的Class
     * @return 返回转换后的实体类，转化失败时返回null
     * */
    public <T> T receive(Class<T> tClass) {
        return JsonUtil.fromJson(tClass, context.getBodyAsString());
    }

    /**
     * 将实体类对象序列化为Json字符串后，响应给client
     * @param data 数据对象
     * @throws JsonParseException 当序列化失败时，抛出该异常
     * */
    public <T> void respond(T data) {
        String response = JsonUtil.toJsonString(data);
        if (response == null) throw new JsonParseException();
        context.response().end(response);
    }

    /**
     * 将Map对象序列化为Json字符串后，响应给client
     * @param map 一个map数据
     * */
    public void respond(Map<String, Object> map) {
        String response = new JsonObject(map).encode();
        context.response().end(response);
    }

    @Override
    public HttpServerRequest request() {
        return context.request();
    }

    @Override
    public HttpServerResponse response() {
        return context.response();
    }

    @Override
    public void next() {
        context.next();
    }

    @Override
    public void fail(int i) {
        context.fail(i);
    }

    @Override
    public void fail(Throwable throwable) {
        context.fail(throwable);
    }

    @Override
    public void fail(int i, Throwable throwable) {
        context.fail(i, throwable);
    }

    @Override
    public RoutingContext put(String s, Object o) {
        return context.put(s, o);
    }

    @Override
    public <T> T get(String s) {
        return context.get(s);
    }

    @Override
    public <T> T remove(String s) {
        return context.remove(s);
    }

    @Override
    public Map<String, Object> data() {
        return context.data();
    }

    @Override
    public Vertx vertx() {
        return context.vertx();
    }

    @Override
    public String mountPoint() {
        return context.mountPoint();
    }

    @Override
    public Route currentRoute() {
        return context.currentRoute();
    }

    @Override
    public String normalisedPath() {
        return context.normalisedPath();
    }

    @Override
    public Cookie getCookie(String s) {
        return context.getCookie(s);
    }

    @Override
    public RoutingContext addCookie(Cookie cookie) {
        return context.addCookie(cookie);
    }

    @Override
    public Cookie removeCookie(String s, boolean b) {
        return context.removeCookie(s, b);
    }

    @Override
    public int cookieCount() {
        return context.cookieCount();
    }

    @Override
    public Set<Cookie> cookies() {
        return context.cookies();
    }

    @Override
    public String getBodyAsString() {
        return context.getBodyAsString();
    }

    @Override
    public String getBodyAsString(String s) {
        return context.getBodyAsString(s);
    }

    @Override
    public JsonObject getBodyAsJson() {
        return context.getBodyAsJson();
    }

    @Override
    public JsonArray getBodyAsJsonArray() {
        return context.getBodyAsJsonArray();
    }

    @Override
    public Buffer getBody() {
        return context.getBody();
    }

    @Override
    public Set<FileUpload> fileUploads() {
        return context.fileUploads();
    }

    @Override
    public Session session() {
        return context.session();
    }

    @Override
    public User user() {
        return context.user();
    }

    @Override
    public Throwable failure() {
        return context.failure();
    }

    @Override
    public int statusCode() {
        return context.statusCode();
    }

    @Override
    public String getAcceptableContentType() {
        return context.getAcceptableContentType();
    }

    @Override
    public ParsedHeaderValues parsedHeaders() {
        return context.parsedHeaders();
    }

    @Override
    public int addHeadersEndHandler(Handler<Void> handler) {
        return context.addHeadersEndHandler(handler);
    }

    @Override
    public boolean removeHeadersEndHandler(int i) {
        return context.removeHeadersEndHandler(i);
    }

    @Override
    public int addBodyEndHandler(Handler<Void> handler) {
        return context.addBodyEndHandler(handler);
    }

    @Override
    public boolean removeBodyEndHandler(int i) {
        return context.removeBodyEndHandler(i);
    }

    @Override
    public boolean failed() {
        return context.failed();
    }

    @Override
    public void setBody(Buffer buffer) {
        context.setBody(buffer);
    }

    @Override
    public void setSession(Session session) {
        context.setSession(session);
    }

    @Override
    public void setUser(User user) {
        context.setUser(user);
    }

    @Override
    public void clearUser() {
        context.clearUser();
    }

    @Override
    public void setAcceptableContentType(String s) {
        context.setAcceptableContentType(s);
    }

    @Override
    public void reroute(HttpMethod httpMethod, String s) {
        context.reroute(httpMethod, s);
    }

    @Override
    public List<Locale> acceptableLocales() {
        return context.acceptableLocales();
    }

    @Override
    public Map<String, String> pathParams() {
        return context.pathParams();
    }

    @Override
    public String pathParam(String s) {
        return context.pathParam(s);
    }

    @Override
    public MultiMap queryParams() {
        return context.queryParams();
    }

    @Override
    public List<String> queryParam(String s) {
        return context.queryParam(s);
    }

    @Override
    public Cookie removeCookie(String name) {
        return context.removeCookie(name);
    }

    @Override
    public void reroute(String path) {
        context.reroute(path);
    }

    @Override
    public List<LanguageHeader> acceptableLanguages() {
        return context.acceptableLanguages();
    }

    @Override
    public Locale preferredLocale() {
        return context.preferredLocale();
    }

    @Override
    public LanguageHeader preferredLanguage() {
        return context.preferredLanguage();
    }

    class JsonParseException extends RuntimeException {
        public JsonParseException() {
            super("The data can not to Json String");
        }
    }
}
