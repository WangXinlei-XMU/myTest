<template>
  <div id="ii">
    <form>
      <label for="username">用户名</label><input type="text" v-model.trim="loginData.username" name="username">
      <label for="password">用户名</label><input type="password" v-model.trim="loginData.password" name="password">
      <input type="button" value="登录" @click="doLogin">
    </form>
  </div>
</template>
