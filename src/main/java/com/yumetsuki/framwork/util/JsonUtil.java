package com.yumetsuki.framwork.util;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

public class JsonUtil {

    public static <T> T fromJson(Class<T> tClass, String s) {
        try {
            return Json.decodeValue(s, tClass);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T fromJson(Class<T> tClass, JsonObject object) {
        try {
            return Json.decodeValue(object.encode(), tClass);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> String toJsonString(T data) {
        try {
            return Json.encode(data);
        } catch (Exception e) {
            return null;
        }
    }

}
