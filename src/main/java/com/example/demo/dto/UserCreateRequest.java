package com.example.demo.dto;

public class UserCreateRequest {

    private String name;
    private int age;

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    public void setName(String name){
        this.name = name;
    }

    public  void setAge(int age){
        this.age = age;
    }

    public UserCreateRequest(){}

    public UserCreateRequest(String name,int age){
        this.age = age;
        this.name = name;
    }
}
