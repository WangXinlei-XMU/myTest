package com.example.demo;

import com.example.demo.model.Customer;
import com.example.demo.service.ArticleService;
import com.example.demo.service.CustomerService;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	CustomerService service;
	@Autowired
	ArticleService articleService;

	@Test
	public void test1(){
		System.out.println(service.getAll(1,2).getList());
	}
	@Test
	public void test2(){
		System.out.println(service.getById(1));
	}

	@Test
	public void test3(){
		Customer customer= service.getById(1);
		customer.setEmail("efaw@fa.ewfa");
		System.out.println(service.updateByCustomer(customer));
	}


	@Test
	public void test31(){
		Customer customer= new Customer();
		customer.setName("name1");
		System.out.println(service.selectCustomer(customer));
	}

	@Test
	public void test4(){
		System.out.println(service.login("name1","123456"));
	}

	@Test
	public void test5(){
		System.out.println(articleService.getArticleByUser(1));
	}
	@Test
	public void test6(){
//		System.out.println(articleService.getArticleByKey());
	}
	@Test
	public void test7(){
		Customer customer=new Customer();
		customer.setName("name");
		customer.setPassword("ef");
		System.out.println(service.register(customer));
	}
}
