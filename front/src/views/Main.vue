<template>
  <div>

    <div class="header">
      <h1>欢迎来到Blog</h1>
      <p>创造自己的精彩</p>
    </div>

    <div>
      <el-tabs :tab-position="tabPosition" style="height: 200px;">
        <el-tab-pane label="文章">

          <div class="navbar">
            <label class="labal">文章搜索</label>
            <el-input class="input"
                      placeholder="请输入内容"
                      prefix-icon="el-icon-search"
                      v-model.trim="msg.search">
            </el-input>
            <el-button type="primary" icon="el-icon-search" @click="searchBtn">搜索</el-button>
          </div>
          <div>
            <li v-if="lists.length===0">
              <el-empty description="没有搜到有效信息"></el-empty>
            </li>
            <li v-for="(article,index) in lists" v-bind:key="index" @click="goto(index)" >
              <span>
                <span class="article-title">文章标题：{{ article.title }}</span>
                <span class="article-user">作者：{{ article.user }}</span>
                <span class="article-title">标签：{{ article.labels }}</span>
                <span class="article-time">时间：{{ article.createTime }}</span>
              </span>
            </li>
          </div>

          <div class="block">
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="msg.current"
              :page-sizes="[5, 10, 20]"
              :page-size="msg.size"
              layout="total, sizes, prev, pager, next, jumper"
              :total="msg.total">
            </el-pagination>
          </div>

        </el-tab-pane>
        <el-tab-pane label="配置管理">




        </el-tab-pane>
        <el-tab-pane label="角色管理">




        </el-tab-pane>
        <el-tab-pane label="定时任务补偿">



        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>
<script>

import utils from "../../utils/utils";

export default {
  name: 'Main',

  data()
  {
    return{
      tabPosition:'top',
      msg:{
        search:"",
        size:5,
        current:1,
        total:0
      },
      lists:[]
    }
  },
  created() {
    this.searchBtn();
  },
  methods:{
    searchBtn(){
      let success=(response)=>{
        this.lists=response.data.list;
        this.msg.total=response.code
      }
      utils.axiosMethod({
        method:"Post",
        url:"/article/search/",
        data:this.msg,
        callback:success
      })
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    }
  }
}
</script>
