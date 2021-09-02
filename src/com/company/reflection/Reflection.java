package com.company.reflection;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Reflection {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        ExtenderB generic = new ExtenderB(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
        ExampleClass<ExtenderB> exampleObj = new ExampleClass<>("Beautiful name", generic);

        Class<? extends ExampleClass> cls = exampleObj.getClass();
        Method[] methods = cls.getMethods();
        Constructor[] constructors = cls.getConstructors();

        System.out.println("Class: " + cls.getSimpleName());

        System.out.println("Class public constructors: ");
        Arrays.stream(constructors).forEach(x -> System.out.println("Current public constructor parameters: " +
                Arrays.toString(Arrays.stream(x.getParameters()).map(i -> i.getParameterizedType()
                        + " " + i.getName()).toArray(String[]::new))));
        System.out.println();

        System.out.println("Info about class: " + cls.toGenericString());
        Field genericField = cls.getField("t");
        System.out.println("Generic super type (field) is: " + genericField.getType());

        System.out.println("All public methods: " + Arrays.toString(methods));
        //System.out.println(cls.getMethod("showT").getReturnType());
        Method genericMethod = cls.getMethod("showT");
        Object res = genericMethod.invoke(exampleObj);
        System.out.println(res.getClass().getSimpleName());

        Field privateField = cls.getDeclaredField("name");
        privateField.setAccessible(true);
        System.out.println("Private field old value: " + privateField.get(exampleObj));
        privateField.set(exampleObj, "new name");
        System.out.println("Private field new value: " + privateField.get(exampleObj));


    }
}
