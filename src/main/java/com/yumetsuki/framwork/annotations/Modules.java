package com.yumetsuki.framwork.annotations;

import com.yumetsuki.framwork.module.Module;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Modules {

    Class<? extends Module> [] modules();

}
