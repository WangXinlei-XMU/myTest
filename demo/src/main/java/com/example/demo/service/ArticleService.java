package com.example.demo.service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dao.CustomerDao;
import com.example.demo.model.Article;
import com.example.demo.model.ArticleSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ArticleService {
    @Autowired
    ArticleDao articleDao;

    @Autowired
    CustomerDao customerDao;

    //获取某人的文章
    public List<ArticleSimple> getArticleByUser(Integer userId){
        String name=customerDao.getCustomerById(userId).getName();
        List<ArticleSimple> list= articleDao.getArticleByUser(userId);
        for(ArticleSimple article:list){
            article.setUser(name);
        }
        return list;
    }
    //获取文章标签
    public List<String> getLabel(Integer articleId){
        return articleDao.getLabel(articleId);
    }

    //通过限制条件查询
    public List<ArticleSimple> getArticle() {
        return null;
    }
}
