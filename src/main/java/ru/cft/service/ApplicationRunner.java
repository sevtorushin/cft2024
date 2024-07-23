package ru.cft.service;

import lombok.Getter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationRunner {

    @Getter
    private final AnnotationConfigApplicationContext springContext;
    @Getter
    private static String[] args;

    public ApplicationRunner(Class<?> configurationClass, String[] args) {
        ApplicationRunner.args = args;
        this.springContext = new AnnotationConfigApplicationContext(configurationClass);
    }

    public void run(){

    }
}
