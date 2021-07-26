<template>
  <div class="manage">
    <div class="title">
      <p></p>
      <hr>
      <h2>评论管理</h2>
      <hr>
    </div>
    <div v-for="o in 4" :key="o" class="content">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <el-button style="float: right; padding: 3px 0" type="text">删除</el-button>
          <el-button style="float: right; padding: 3px 0" type="text">回复</el-button>
        </div>
        <p>于《{{title}}》 {{author}}：{{summary}}</p>
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
        title:"评论的文章",
        summary:"这是一条评论",
        author:"丁心语",
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
