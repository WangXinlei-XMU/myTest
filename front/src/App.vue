<template>
  <div id="login">
    <div class="navbar">
      <router-link to="/" class="rout">主页</router-link>
      <router-link v-if="token===null" to="/register" class="rout-right">注册</router-link>
      <router-link v-if="token===null" to="/login" class="rout-right">登录</router-link>
      <label v-if="token!==null" class="rout-right" @click="logout">退出</label>
    </div>
    <router-view/>
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
      },
      token:'-'
    }
  },
  created() {
    this.token=localStorage.getItem("token");
  },
  methods:{
    logout(){
      //清除token
      window.localStorage.clear();
      //刷新页面
      window.location.reload()
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
.navbar .rout {
  float: left;
  display: block;
  color: white;
  text-align: center;
  padding: 14px 20px;
  text-decoration: none;
}

.navbar .rout-right {
   float: right;
   display: block;
   color: white;
   text-align: center;
   padding: 14px 20px;
   text-decoration: none;
 }

/* 鼠标移动到链接的颜色 */
.navbar a:hover {
  background-color: #ddd;
  color: black;
}

.navbar .input{
  text-align: left;
  width: 60%;
  height: 10px;
}
.navbar .labal{
  color: #dddddd;
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
