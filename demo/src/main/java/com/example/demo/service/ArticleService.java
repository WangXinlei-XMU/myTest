package com.example.demo.service;

import com.example.demo.model.Article;
import com.example.demo.model.Vo.ArticleLimitVo;
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
    public PageInfo<ArticleSimple> getArticleByUser(Search search,Integer userId){
        PageHelper.startPage(search.getCurrent(),search.getSize(),true);

        List<ArticleSimple> list= articleDao.getArticleByUser(userId);
        for(ArticleSimple articleSimple:list){
            articleSimple.setLabels(articleDao.getLabel(articleSimple.getId()));
        }
        PageInfo<ArticleSimple> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    //获取文章标签
    public List<String> getLabel(Integer articleId){
        return articleDao.getLabel(articleId);
    }


    //通过关键词查询
    public PageInfo<ArticleSimple> getArticleByKey(Search search) {
        // 1 设置分页页码和显示条数
//        System.out.println(pageSize);
        PageHelper.startPage(search.getCurrent(),search.getSize(),true);

        List<ArticleSimple> list=articleDao.getArticleByKey(search.getSearch());

        for(ArticleSimple articleSimple:list){
            articleSimple.setLabels(articleDao.getLabel(articleSimple.getId()));
        }
        // 2 集合封装到PageInfo类中
        PageInfo<ArticleSimple> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    //获取一篇文章
    public Article getArticleById(Integer id){
        Article article=articleDao.getArticleById(id);
        List<String> list=articleDao.getLabel(id);
        article.setLabels(list);
        return article;
    }

    //通过搜索限制
    public PageInfo<ArticleSimple> getArticleByLimit(ArticleLimitVo vo) {
        // 1 设置分页页码和显示条数
//        System.out.println(pageSize);
        PageHelper.startPage(vo.getCurrent(),vo.getSize(),true);

        List<ArticleSimple> list=articleDao.getArticleByLimit(vo);

        for(ArticleSimple articleSimple:list){
            articleSimple.setLabels(articleDao.getLabel(articleSimple.getId()));
        }
        // 2 集合封装到PageInfo类中
        PageInfo<ArticleSimple> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

}
