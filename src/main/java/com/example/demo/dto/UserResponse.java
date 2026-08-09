package com.example.demo.dto;

public class UserResponse {
    private Long id;
    private String name;
    private int age;


    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    public Long getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    public  void setAge(int age){
        this.age = age;
    }

    public UserResponse(){}

    public UserResponse(Long id,String name,int age){
        this.id = id;
        this.age = age;
        this.name = name;
    }
}
