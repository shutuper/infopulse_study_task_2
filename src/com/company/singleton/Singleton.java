package com.company.singleton;

public class Singleton {


    private Singleton() {
    }

    private static class SingletonInstanceBuilder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonInstanceBuilder.INSTANCE;
    }


}
