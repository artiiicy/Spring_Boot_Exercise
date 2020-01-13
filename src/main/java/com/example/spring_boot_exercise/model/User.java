package com.example.spring_boot_exercise.model;

// request Body를 받아올 코드부.
public class User {
    private String name;
    private int age;

    // Default Constructor는 반드시 있어야 한다.
    public User() {}

    public User(final String name, final int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int name) {
        this.age = age;
    }
}
