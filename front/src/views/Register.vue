<template>
  <div id="me">
    <h1>注册</h1>
    <div class="login-box">
      <input type="text" v-model="loginData.name" placeholder="用户名" class="input">

      <input type="password" v-model="loginData.password" placeholder="密码" class="input">

      <input type="password" v-model="loginData.password1" placeholder="确认密码" class="input">

      <input type="text" v-model="loginData.email" placeholder="邮箱" class="input">

      <input type="text" v-model="loginData.phone" placeholder="电话" class="input">

      <el-button type="primary" @click="registerBtn">注册</el-button>
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
        name:"",
        password:"",
        password1:"",
        email:"",
        phone:""
      }
    }
  },
  methods:{
    //连接按键，检查输入
    registerBtn() {
      if (!this.loginData.name) {
        this.$message.error('请输入用户名');
        return;
      }
      if (!this.loginData.password) {
        this.$message.error('请输入密码');
        return;
      }
      if (!this.loginData.password1) {
        this.$message.error('请确认密码');
        return;
      }
      if (this.loginData.password!== this.loginData.password1) {
        this.$message.error('密码不一致');
        return;
      }
      if(!this.loginData.email.match("[A-Za-z0-9]+@[A-Za-z0-9]+.com")){
        this.$message.error('邮箱输入有误');
        return;
      }
      if(this.loginData.phone.length!==11||!this.loginData.phone.match("[0-9]+")){
        this.$message.error('电话输入有误');
        return;
      }
      this.doRegister();
    },
    //连接后端
    doRegister(){
      let success=(response)=>{
        this.$message({
          type: 'success',
          message: response.data.msg});
      }
        utils.axiosMethod({
        method:"POST",
        url:"/user/register/",
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
