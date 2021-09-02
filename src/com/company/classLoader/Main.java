package com.company.classLoader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {

    public static void main(String[] args) {
        // При загрузці Singleton-а різними класлоадерами, його інстанси будуть різними
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            Class<Singleton> singletonClass1 = (Class<Singleton>) classLoader.loadClass("com.company.classLoader.Singleton");
            final Method getInstance = singletonClass1.getMethod("getInstance");
            Object singleton1 = getInstance.invoke(singletonClass1);
            System.out.println(singleton1);
            URL url = new URL("file:///jars\\Singleton.jar");
            //.getParent()
            Class<Singleton> singletonClass2 = (Class<Singleton>) new URLClassLoader(new URL[]{url}, classLoader).loadClass("D:\\Infopulse\\Study\\2_task\\src\\com\\company\\classLoader\\Singleton.java"); //Thread.currentThread().getContextClassLoader().loadClass("com.company.classLoader.Singleton");
            final Method getInstance2 = singletonClass2.getMethod("getInstance");
            Object singleton2 = getInstance2.invoke(singletonClass2);

            System.out.println(singleton2);

            System.out.println(singleton1 == singleton2);
        } catch (MalformedURLException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
