package ru.cft;

import ru.cft.configurations.Configuration;
import ru.cft.service.ApplicationRunner;

public class App {
    public static void main(String[] args) {
        ApplicationRunner applicationRunner = ApplicationRunner.init(Configuration.class, args);
        applicationRunner.start();
    }
}
