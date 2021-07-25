package com.example.demo.service;

import com.example.demo.model.Article;
import com.example.demo.model.Label;
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

import java.time.LocalDateTime;
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
            if(articleSimple.getState()!=null){
                switch (articleSimple.getState()){
                    case 0:articleSimple.setStateStr("未发布"); break;
                    case 1:articleSimple.setStateStr("已发布"); break;
                    case 2:articleSimple.setStateStr("被封禁"); break;
                    case 3:articleSimple.setStateStr("已删除"); break;
                }
            }
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
        switch (article.getState()){
            case 0:article.setStateStr("未发布");break;
            case 1:article.setStateStr("已发布");break;
            case 2:article.setStateStr("已封禁");break;
            case 3:article.setStateStr("已删除");break;
        }
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

    //删除某文章（逻辑）
    public void deleteArticle(Integer id){
        articleDao.changeArticleState(3,id);
    }

    //获取所有标签
    public List<Label> getLabels(){
       return articleDao.getLabels();
    }

    //修改文章
    public void update(Article article){
        if(article.getStateStr().equals("未发布"))
            article.setState((byte)0);
        else if(article.getStateStr().equals("已发布"))
            article.setState((byte)1);
        article.setModifyTime(LocalDateTime.now());
        articleDao.updateArticle(article);
    }

    //新建文章
    public void insertArticle(Article article){
        article.newArticle();
        articleDao.insertArticle(article);
    }

    //修改文章标签
    public void updateLabel(Integer articleId,List<String> newLabels){
        List<String> oldLabels=articleDao.getLabel(articleId);
        List<String> temp=oldLabels;
        //挑选出删去的标签
        for(String newLabel:newLabels)
           oldLabels.remove(newLabel);
        //挑选出新增的标签
        for(String oldLabel:temp)
            newLabels.remove(oldLabel);
        //新增标签
        for(String newLabel:newLabels){
            Integer labelId = articleDao.getLabelByText(newLabel);
            articleDao.insertArticlLabel(articleId,labelId);
        }
        //删去标签
        for(String oldLabel:oldLabels){
            Integer labelId = articleDao.getLabelByText(oldLabel);
            articleDao.deleteArticleLabel(articleId,labelId);
        }

    }
}
