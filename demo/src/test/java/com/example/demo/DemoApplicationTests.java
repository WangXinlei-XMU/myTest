package com.example.demo;

import com.example.demo.model.ArticleSimple;
import com.example.demo.model.Customer;
import com.example.demo.model.Vo.ArticleLimitVo;
import com.example.demo.model.Vo.Search;
import com.example.demo.service.ArticleService;
import com.example.demo.service.CommentService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OtherService;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	CustomerService service;
	@Autowired
	ArticleService articleService;
	@Autowired
	CommentService commentService;
	@Autowired
	OtherService otherService;

	@Test
	public void test1(){
//		System.out.println(service.getAll(1,2).getList());
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

//	@Test
//	public void test5(){
//		System.out.println(articleService.getArticleByUser(1));
//	}
	@Test
	public void test6(){
		Search search=new Search();
		System.out.println(articleService.getArticleByKey(search));
	}
	@Test
	public void test7(){
		Customer customer=new Customer();
		customer.setName("name");
		customer.setPassword("ef");
		System.out.println(service.register(customer));
	}
	@Test
	public void test8(){
		LocalDateTime date1=articleService.getArticleById(2).getCreateTime();
		ArticleLimitVo vo=new ArticleLimitVo();
		vo.setDate1(date1);
		PageInfo<ArticleSimple> articleSimples=articleService.getArticleByLimit(vo);
		List<ArticleSimple> list=articleSimples.getList();
		System.out.println(list);
	}
	@Test
	public void test9(){
		Search search=new Search();
		System.out.println(articleService.getArticleByUser(search,1));
	}
	@Test
	public void test10(){
		Search search=new Search();
		System.out.println(commentService.getCommentByArticle(search,1));
	}
	@Test
	public void test11(){
		System.out.println(articleService.getLabels());
	}
	@Test
	public void test12(){
		Search search=new Search();
		System.out.println(otherService.getLikeByUser(search,1));
	}

}
