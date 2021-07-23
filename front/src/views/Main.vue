<template>
  <div>

    <div class="header">
      <h1>欢迎来到Blog</h1>
      <p>创造自己的精彩</p>
    </div>

    <div class="navbar">
      <label class="labal">文章搜索</label>
      <el-input class="input"
                placeholder="请输入内容"
                prefix-icon="el-icon-search"
                v-model="search">
      </el-input>
      <el-button type="primary" icon="el-icon-search" @click="searchBtn">搜索</el-button>
    </div>
    <div>
      <li v-for="(article,index) in lists" v-bind:key="index" @click="goto(index)" >
        <span>
      <span class="article-user">{{ article.user }}</span>
      <span class="article-title">{{ article.title }}</span>
      <span class="article-time">{{ article.createTime }}</span>
      </span>
      </li>
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
      search:'',
      lists:[]
    }
  },
  created() {
    this.searchBtn();
  },
  methods:{
    searchBtn(){
      let success=(response)=>{
         this.lists=response.data.list
      }
      utils.axiosMethod({
        method:"GET",
        url:"/article/search/",
        data:this.search,
        callback:success
      })
    }
  }
}
</script>
