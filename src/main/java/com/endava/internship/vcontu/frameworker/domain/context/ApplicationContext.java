package com.endava.internship.vcontu.frameworker.domain.context;

public interface ApplicationContext {
    <T> T getBean(Class<T> clazz);
}
