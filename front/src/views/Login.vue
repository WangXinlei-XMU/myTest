<template>
  <div id="me">
    <h1>登录</h1>
    <div class="login-box">
      <input type="text" v-model.trim="loginData.username" placeholder="用户名" class="input">

      <input type="password" v-model.trim="loginData.password" placeholder="密码" class="input">

      <el-button type="primary" @click="loginBtn">登录</el-button>
    </div>

  </div>
</template>


<script>

import utils from "../../utils/utils";

export default {
  name: 'Me',

  data()
  {
    return{
      loginData:{
        username:"",
        password:""
      }
    }
  },
  methods:{
    loginBtn(){
      if (!this.loginData.username) {
      this.$message.error('请输入用户名');
      return;
    }
      if (!this.loginData.password) {
        this.$message.error('请输入密码');
        return;
      }
      this.doLogin()
    },
    doLogin(){
      let success=(response)=>{
        if(response.data.code===0){
          this.$message({
            type: 'success',
            message: response.data.msg});
          localStorage.setItem("token", response.data.obj.id);
          localStorage.setItem("type", response.data.obj.type);
          if(response.data.obj.type===1)
            this.$router.push('/admin?admin='+response.data.obj.id);
          window.location.reload();
          // alert(response.data.obj.id);
        }
        else {
          this.$message.error(response.data.msg);
        }
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

<style scoped>
#me {
}

#me .h1{
  margin: 0 auto;
}
#me .login-box {
  padding-top: 30px;
  width: 60%;
  margin: 0 auto;
}

#me .login-box .input {
  display: block;
  width: 98%;
  height: 28px;
  margin: 15px 0;
}

#me .login-box .el-button {
  display: block;
  width: 98%;
  height: 35px;
  line-height: 29px;
  color: #fff;
  border-radius: 5px;
}
</style>
