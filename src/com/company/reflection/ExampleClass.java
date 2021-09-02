package com.company.reflection;

import java.util.ArrayList;
import java.util.Arrays;

public class ExampleClass<T extends SuperB> {

    private String name;
    public T t;

    public ExampleClass(String name, T t) {
        this.name = name;
        this.t = t;
    }

    public T showT(){
        return t;
    }
    public void sayHello() {
        System.out.println("Hello!");
    }

    public void iterateT() {
        t.showAll();
    }

}

class SuperB {

    public ArrayList<Integer> integers;

    public SuperB(ArrayList<Integer> integers) {
        this.integers = integers;
    }

    public void showAll() {
        integers.forEach(System.out::println);
    }
}

class ExtenderB extends SuperB {

    public ExtenderB(ArrayList<Integer> integers) {
        super(integers);
    }

    @Override
    public void showAll() {
        System.out.println(Arrays.toString(super.integers.toArray(Integer[]::new)));
    }
}
