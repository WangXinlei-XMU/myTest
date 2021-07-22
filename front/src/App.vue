<template>
  <div id="login">
    <form>
      <label for="username">用户名</label><input type="text" v-model.trim="loginData.username" name="username">
      <label for="password">用户名</label><input type="password" v-model.trim="loginData.password" name="password">
      <input type="button" value="登录" @click="doLogin">
    </form>

    <div id="nav">
      <el-row>
        <el-col :span="8"><div class="grid-content bg-purple"><router-link to="/">Home</router-link></div></el-col>
        <el-col :span="8"><div class="grid-content bg-purple-light"><router-link to="/about">About</router-link></div></el-col>
        <el-col :span="8"><div class="grid-content bg-purple"><router-link to="/me">Me</router-link></div></el-col>
      </el-row>
    </div>

    <router-view/>
  </div>
</template>

<script>
import utils from "../utils/utils";
export default {
  name:'login',
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

h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
