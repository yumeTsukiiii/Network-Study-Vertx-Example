package com.yumetsuki.example.exception;

public class Exceptions {

    public static class RequestParamInvaidException extends RuntimeException{
        public RequestParamInvaidException() {
            super("The request params are error");
        }
    }
}
