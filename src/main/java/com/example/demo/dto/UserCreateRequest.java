package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class UserCreateRequest {

    @NotBlank
    private String name;
    @Min(0)
    private int age;
    @NotBlank
    @Email
    private String email;

    public String getName(){
        return this.name;
    }
    public String getEmail(){return  this.email;}
    public int getAge(){
        return this.age;
    }

    public void setName(String name){
        this.name = name;
    }
    public  void setAge(int age){
        this.age = age;
    }
    public void  setEmail(String email){this.email = email;}

    public UserCreateRequest(){}
    public UserCreateRequest(String name,int age,String email){
        this.age = age;
        this.name = name;
        this.email = email;
    }
}
