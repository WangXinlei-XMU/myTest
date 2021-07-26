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
import java.util.ArrayList;
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
            articleSimple.setStateStr(getState(articleSimple.getState()));
        }
        PageInfo<ArticleSimple> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    //获得状态标识
    private String getState(Byte state){
        String str="";
        if(state!=null){
            switch (state){
                case 0:str="未发布"; break;
                case 1:str="已发布"; break;
                case 2:str="被封禁"; break;
                case 3:str="已删除"; break;
            }
        }
        return str;
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
        article.setStateStr(getState(article.getState()));
        article.setLabels(list);
        return article;
    }

    //通过搜索限制
    public PageInfo<ArticleSimple> getArticleByLimit(ArticleLimitVo vo) {
        //改变状态
        // 1 设置分页页码和显示条数
//        System.out.println(pageSize);
        PageHelper.startPage(vo.getCurrent(),vo.getSize(),true);

        //改变状态
        this.getState(vo.getStateStr());
        System.out.println(vo);
        List<ArticleSimple> list=articleDao.getArticleByLimit(vo);

        for(ArticleSimple articleSimple:list){
            articleSimple.setLabels(articleDao.getLabel(articleSimple.getId()));
            articleSimple.setStateStr(this.getState(articleSimple.getState()));
        }
        // 2 集合封装到PageInfo类中
        PageInfo<ArticleSimple> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    //获得状态
    private Byte getState(String stateStr){
        Byte state=-1;
        switch (stateStr){
            case "未发布":state=(byte)0;break;
            case "已发布":state=(byte)1;break;
            case "被封禁":state=(byte)2;break;
            case "已删除":state=(byte)3;break;
        }
        return state;
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
//        System.out.println(article);
        article.setModifyTime(LocalDateTime.now());
        this.updateLabel(article.getId(),article.getLabels());
        articleDao.updateArticle(article);
    }

    //新建文章
    public void insertArticle(Article article){
        article.newArticle();
        article.setState(this.getState(article.getStateStr()));
        articleDao.insertArticle(article);
    }

    //修改文章标签
    public void updateLabel(Integer articleId,List<String> newLabels){
        List<String> oldLabels=articleDao.getLabel(articleId);
        List<String> temp=new ArrayList<>();
        for(String olaLabel:oldLabels)
            temp.add(olaLabel);
        //挑选出删去的标签
        for(String newLabel:newLabels)
           oldLabels.remove(newLabel);
        //挑选出新增的标签
        for(String oldLabel:temp)
            newLabels.remove(oldLabel);
//        System.out.println("oldLabel:"+oldLabels);
//        System.out.println("newLabel:"+newLabels);
        //新增标签
        for(String newLabel:newLabels){
            Integer labelId = articleDao.getLabelByText(newLabel);
//            System.out.println("新增标签："+newLabel);
            articleDao.insertArticlLabel(articleId,labelId);
        }
        //删去标签
        for(String oldLabel:oldLabels){
            Integer labelId = articleDao.getLabelByText(oldLabel);
//            System.out.println("删去标签："+oldLabel);
            articleDao.deleteArticleLabel(articleId,labelId);
        }

    }


    //管理员相关

    //查询全部文章
    public PageInfo<ArticleSimple> getArticle(Search search){
        // 1 设置分页页码和显示条数
//        System.out.println(pageSize);
        PageHelper.startPage(search.getCurrent(),search.getSize(),true);

        List<ArticleSimple> list=articleDao.getArticle();

        for(ArticleSimple articleSimple:list){
            articleSimple.setLabels(articleDao.getLabel(articleSimple.getId()));
            articleSimple.setStateStr(getState(articleSimple.getState()));
        }
        // 2 集合封装到PageInfo类中
        PageInfo<ArticleSimple> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public void banArticleState(Integer id){
        articleDao.changeArticleState(2,id);
    }


    public void unBanArticleState(Integer id){
        articleDao.changeArticleState(0,id);
    }
}
