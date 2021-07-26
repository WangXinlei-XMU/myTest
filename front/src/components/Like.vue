<template>
  <div class="like">
    <div class="title">
      <p></p>
      <hr>
      <h2>收藏夹</h2>
      <hr>
    </div>
    <div v-for="o in 4" :key="o" class="content">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>{{title}}</span>
          <el-button style="float: right; padding: 3px 0" type="text">取消收藏</el-button>
        </div>
        <p>{{summary}}</p>
        <p>{{author}}{{time}}</p>
      </el-card>
    </div>
  </div>
</template>
<script scoped>
  export default {
    data()
    {
      return{
        title:"博客标题",
        summary:"这是一篇博客文章的简介",
        author:"作者（url跳转）",
        time:"2021-7-25 9：31"
      }
    }
  }
</script>

<style>
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .title{
    width: 90%;
    text-align: center;
  }
  .box-card {
    width: 90%;
  }
</style>
