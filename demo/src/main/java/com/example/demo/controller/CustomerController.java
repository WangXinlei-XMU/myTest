package com.example.demo.controller;

import com.example.demo.model.ArticleSimple;
import com.example.demo.model.Comment;
import com.example.demo.model.CustomerSimple;
import com.example.demo.model.Vo.ArticleLimitVo;
import com.example.demo.model.Vo.MyResult;
import com.example.demo.model.Customer;
import com.example.demo.model.Vo.Search;
import com.example.demo.service.CustomerService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.http.HttpRequest;

@Controller
@RequestMapping("/user")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    //登录
    @ResponseBody
    @PostMapping("/login")
    public MyResult login(@RequestBody Customer customer){
        Customer customer1=customerService.login(customer.getName(),customer.getPassword());
        MyResult myResult= new MyResult();
        if(customer1!=null){
            myResult.setCode(0);
            myResult.setMsg("登录成功！");
            myResult.setObj(customer1);
        }
        else {
            myResult.setCode(1);
            myResult.setMsg("登录失败！");
        }
        return myResult;
    }

    //注册
    @ResponseBody
    @PostMapping("/register")
    public MyResult register(@RequestBody Customer customer){
        Customer customer1=customerService.register(customer);
//        System.out.println(customer1);
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

    //某一用户
    @ResponseBody
    @GetMapping("/{userId}")
    public MyResult user(@PathVariable("userId") Integer id){
        Customer customer=customerService.getById(id);
        MyResult myResult= new MyResult();
//        System.out.println(id);
//        System.out.println(customer);
        myResult.setObj(customer);
        return myResult;
    }

    //查询用户
    @ResponseBody
    @PostMapping("/search")
    public MyResult getAll(@RequestBody Search search){
        PageInfo<CustomerSimple> customerSimplePageInfo=customerService.getAll(search);
        MyResult myResult= new MyResult();
        myResult.setList(customerSimplePageInfo.getList());
        myResult.setCode((int)customerSimplePageInfo.getTotal());
        return myResult;
    }

    //上传头像
    @ResponseBody
    @PostMapping("/posts")
    public MyResult putImg(MultipartFile file){
        String path="C:\\Users\\Administrator\\Desktop\\test\\front\\src\\assets\\";
        String filename =  file.getOriginalFilename();
//        System.out.println(file.getOriginalFilename());
        File newFile = new File(path + filename);

        MyResult myResult= new MyResult();
        // 4 写入
        try {
            file.transferTo(newFile);
            myResult.setCode(0);
            myResult.setMsg(path + filename);
        } catch (IOException e) {
            e.printStackTrace();
            myResult.setCode(1);
            myResult.setMsg("error");
        }
        return myResult;
    }

    //修改信息
    @ResponseBody
    @PostMapping("/update")
    public MyResult update(@RequestBody Customer customer){
        MyResult myResult= new MyResult();
        Customer customer1=customerService.updateByCustomer(customer);
        myResult.setCode(0);
        myResult.setMsg("修改成功");
        myResult.setObj(customer1);
        return myResult;
    }

    //管理员相关
    @ResponseBody
    @PostMapping("/admin/customer")
    public MyResult adminGetCustomer(@RequestBody Search search) {
        PageInfo<Customer> customerList= customerService.adminGetCustomer(search);
        MyResult myResult=new MyResult();
        myResult.setCode((int) customerList.getTotal());
        myResult.setList(customerList.getList());
        return myResult;
    }

    @ResponseBody
    @PutMapping("/admin/banCustomer/{userId}")
    public MyResult banCustomer(@PathVariable("userId")Integer userId) {
        customerService.banCustomer(userId);
        MyResult myResult=new MyResult();
        myResult.setCode(0);
        return myResult;
    }

    @ResponseBody
    @PutMapping("/admin/unBanCustomer/{userId}")
    public MyResult unBanCustomer(@PathVariable("userId")Integer userId) {
        customerService.unBanCustomer(userId);
        MyResult myResult=new MyResult();
        myResult.setCode(0);
        return myResult;
    }
}
