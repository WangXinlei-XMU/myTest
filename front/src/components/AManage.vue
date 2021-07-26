<template>
  <div class="manage">
    <div class="title">
      <p></p>
      <hr>
      <h2>文章管理</h2>
      <hr>
    </div>
    <div v-for="o in 4" :key="o" class="content">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>{{title}}</span>
          <el-button style="float: right; padding: 3px 0" type="text">编辑</el-button>
          <el-button style="float: right; padding: 3px 0" type="text">删除</el-button>
          <el-button style="float: right; padding: 3px 0" type="text">设为私密</el-button>
        </div>
        <p>{{summary}}</p>
        <p>{{time}}</p>
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
