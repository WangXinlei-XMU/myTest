<template>
  <div class="manage">
    <div class="title">
      <p></p>
      <hr>
      <h2>文章管理</h2>
      <hr>
    </div>
    <div  v-for="json in list" class="content">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>{{json.title}}</span>
          <el-button style="float: right; padding: 3px 0" type="text">编辑</el-button>
          <el-button style="float: right; padding: 3px 0" type="text">删除</el-button>
          <el-button style="float: right; padding: 3px 0" type="text">设为私密</el-button>
        </div>
        <p>{{json.summary}}</p>
        <p>{{json.user_id}}{{json.create_time}}</p>
      </el-card>
    </div>
  </div>
</template>
<script scoped>
  import axios from 'axios'
  export default {
    data()
    {
      return{
        list:[]
      }
    },
    methods:{
      getList(){
        axios.get('http://localhost:8081/test3',{})
          .then(res=>{
            console.log(res.data)
            var jObject=res.data.valueOf()
            this.list=res.data
            console.log(this.list)
          }).catch(err=>{
          console.log(err);
        })
        console.log("你好");
      }
    },
    created() {
      this.getList();
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
