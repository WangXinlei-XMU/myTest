<template>
  <div id="login">

    <div class="navbar">
      <a href="#" class="right">登录</a>
    </div>

    <div class="header">
      <h1>欢迎来到Blog</h1>
      <p>创造自己的精彩</p>
    </div>

    <div class="navbar">
      <a href="#">链接</a>
      <a href="#">链接</a>
      <a href="#">链接</a>
      <a href="#" class="right">链接</a>
    </div>

    <div class="row">
      <div class="side">
        <el-row>
          <el-col :span="8"><div class="grid-content bg-purple"><router-link to="/">Home</router-link></div></el-col>
          <el-col :span="8"><div class="grid-content bg-purple-light"><router-link to="/about">About</router-link></div></el-col>
          <el-col :span="8"><div class="grid-content bg-purple"><router-link to="/me">Me</router-link></div></el-col>
        </el-row>
      </div>
      <div class="main">
        <router-view></router-view>      </div>
    </div>

    <div class="footer">
      <h2>底部内容</h2>
    </div>
  </div>
</template>

<script>
import utils from "../utils/utils";
export default {
  name:'App',
  data(){
    return {
      loginData:{
        username:"",
        password:""
      }
    }
  },
  methods:{
    doLogin(){
      let success=(response)=>{
        alert(response.data.msg);
      }
      utils.axiosMethod({
        method:"POST",
        url:"/user/login/",
        data:this.loginData,
        callback:success
      })
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
* {
  box-sizing: border-box;
}

/* body 样式 */
body {
  font-family: Arial;
  margin: 0;
}

/* 标题 */
.header {
  padding: 80px;
  text-align: center;
  background: #1abc9c;
  color: white;
}

/* 标题字体加大 */
.header h1 {
  font-size: 40px;
}

/* 导航 */
.navbar {
  overflow: hidden;
  background-color: #1a1a1a;
}

/* 导航栏样式 */
.navbar a {
  float: left;
  display: block;
  color: white;
  text-align: center;
  padding: 14px 20px;
  text-decoration: none;
}

/* 右侧链接*/
.navbar a.right {
  float: right;
}

/* 鼠标移动到链接的颜色 */
.navbar a:hover {
  background-color: #ddd;
  color: black;
}

/* 列容器 */
.row {
  display: -ms-flexbox; /* IE10 */
  display: flex;
  -ms-flex-wrap: wrap; /* IE10 */
  flex-wrap: wrap;
}

/* 创建两个列 */
/* 边栏 */
.side {
  -ms-flex: 30%; /* IE10 */
  flex: 30%;
  background-color: #f1f1f1;
  padding: 20px;
}

/* 主要的内容区域 */
.main {
  -ms-flex: 70%; /* IE10 */
  flex: 70%;
  background-color: white;
  padding: 20px;
}

/* 测试图片 */
.fakeimg {
  background-color: #aaa;
  width: 100%;
  padding: 20px;
}

/* 底部 */
.footer {
  padding: 20px;
  text-align: center;
  background: #ddd;
}

/* 响应式布局 - 在屏幕设备宽度尺寸小于 700px 时, 让两栏上下堆叠显示 */
@media screen and (max-width: 700px) {
  .row {
    flex-direction: column;
  }
}

/* 响应式布局 - 在屏幕设备宽度尺寸小于 400px 时, 让导航栏目上下堆叠显示 */
@media screen and (max-width: 400px) {
  .navbar a {
    float: none;
    width: 100%;
  }
}
</style>
