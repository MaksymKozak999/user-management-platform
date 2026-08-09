package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class UserCreateRequest {

    @NotBlank
    private String name;
    @Min(0)
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
