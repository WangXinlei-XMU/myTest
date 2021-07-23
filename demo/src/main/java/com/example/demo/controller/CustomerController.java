package com.example.demo.controller;

import com.example.demo.MyResult;
import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @ResponseBody
    @PostMapping("/login")
    public MyResult login(@RequestBody Customer customer){
        Customer customer1=customerService.login(customer.getName(),customer.getPassword());
        MyResult myResult= new MyResult();
        if(customer1!=null){
            myResult.setCode(0);
            myResult.setMsg("登录成功！");
        }
        else {
            myResult.setCode(1);
            myResult.setMsg("登录失败！");
        }
        return myResult;
    }
    @ResponseBody
    @PostMapping("/register")
    public MyResult register(@RequestBody Customer customer){
        Customer customer1=customerService.register(customer);
        MyResult myResult= new MyResult();
        if(customer1!=null){
            myResult.setCode(0);
            myResult.setMsg("注册成功！");
        }
        else {
            myResult.setCode(1);
            myResult.setMsg("注册失败！");
        }
        return myResult;
    }
}
