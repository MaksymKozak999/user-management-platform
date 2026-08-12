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

    @NotBlank
    private String password;

    public String getName(){
        return name;
    }
    public String getEmail(){return email;}
    public int getAge(){
        return age;
    }
    public  String getPassword(){return password;}

    public void setName(String name){
        this.name = name;
    }
    public  void setAge(int age){
        this.age = age;
    }
    public void  setEmail(String email){this.email = email;}
    public void setPassword(String password){this.password = password;}

    public UserCreateRequest(){}
    public UserCreateRequest(String name,int age,String email,String password){
        this.age = age;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
