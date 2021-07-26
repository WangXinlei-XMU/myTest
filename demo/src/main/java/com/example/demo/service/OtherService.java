package com.example.demo.service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dao.OtherDao;
import com.example.demo.model.ArticleSimple;
import com.example.demo.model.Browse;
import com.example.demo.model.Vo.Search;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class OtherService {
    @Autowired
    private OtherDao otherDao;

    @Autowired
    private ArticleDao articleDao;

    //新增一条浏览记录
    public void addBrowse(Integer artId,Integer userId){
        otherDao.addBrowse(artId,userId, LocalDateTime.now());
    }

    //查询某人的浏览记录
    public PageInfo<Browse> getBrowseByUser(Search search, Integer userId){
        PageHelper.startPage(search.getCurrent(),search.getSize(),true);

        List<Browse> list=otherDao.getBrowseByUser(userId);

        PageInfo<Browse> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    //删除浏览记录
    public void deleteBrowse(Integer userId) {
        otherDao.deleteBrowse(userId);
    }

    //查询用户喜爱
    public PageInfo<ArticleSimple> getLikeByUser(Search search, Integer userId) {
        PageHelper.startPage(search.getCurrent(),search.getSize(),true);

        List<ArticleSimple> list=otherDao.getLikeByUser(userId);
        for(ArticleSimple articleSimple:list)
            articleDao.getLabel(articleSimple.getId());

        PageInfo<ArticleSimple> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    //获取是否喜欢
    public boolean getLikeArticleUser(Integer artId,Integer userId){
        Integer id=otherDao.getLikeArticleUser(artId,userId);
        return id != null;
    }

    //改变是否喜欢文章
    public void updateLikeArticleUser(Integer artId, Integer userId) {
        Integer id=otherDao.getLikeArticleUser(artId,userId);
        //喜欢则增加
        if(id==null)
            otherDao.addLikeArticleUser(artId,userId);
        //不喜欢则删除
        else otherDao.deleteLikeArticleUser(artId,userId);
    }
}
