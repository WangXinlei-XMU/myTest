package com.example.demo.service;

import com.example.demo.model.Vo.Search;
import com.example.demo.dao.ArticleDao;
import com.example.demo.dao.CustomerDao;
import com.example.demo.model.ArticleSimple;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
            article.setLabels(articleDao.getLabel(article.getId()));
        }
        return list;
    }
    //获取文章标签
    public List<String> getLabel(Integer articleId){
        return articleDao.getLabel(articleId);
    }

    //通过限制条件查询
    public PageInfo<ArticleSimple> getArticleByKey(Search search) {
        PageInfo<ArticleSimple> pageInfo=page( articleDao.getArticleByKey
                (search.getSearch()),search.getCurrent(),search.getSize());
        return pageInfo;
    }
    //分页
    public PageInfo<ArticleSimple> page(List<ArticleSimple> list,Integer pageNum,Integer pageSize){
        // 1 设置分页页码和显示条数
        PageHelper.startPage(pageNum, pageSize,true);
        // 2 集合封装到PageInfo类中
        PageInfo<ArticleSimple> pages = new PageInfo<>(list);
        return pages;
    }
}
