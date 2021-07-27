<template>
  <div class="manage">
    <div class="manage">
      <div class="title">
        <p></p>
        <hr>
        <h2>评论管理</h2>
        <hr>
      </div>
      <div  v-for="json in list" class="content">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <el-button style="float: right; padding: 3px 0" type="text">删除</el-button>
            <el-button style="float: right; padding: 3px 0" type="text">回复</el-button>
          </div>
          <p>{{json.user_id}}于{{json.article_id}}说：{{json.text}}</p>
          <p>{{json.create_time}}</p>
        </el-card>
      </div>
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
        axios.get('http://localhost:8081/test4',{})
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
