<template>
  <div class="content">
      <el-row class="tac" gutter="50">
        <el-col :span="4">
          <el-menu
            default-active="1"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose">
            <router-link to="/info">
              <el-menu-item index="1">
                <i class="el-icon-message"></i>
                <span slot="title">个人信息</span>
              </el-menu-item>
            </router-link>
            <router-link to="/setting">
              <el-menu-item index="2">
                <i class="el-icon-setting"></i>
                <span slot="title">账号设置</span>
              </el-menu-item>
            </router-link>
            <router-link to="/privacy">
              <el-menu-item index="3">
                <i class="el-icon-setting"></i>
                <span slot="title">隐私设置</span>
              </el-menu-item>
            </router-link>
            <router-link to="/like">
              <el-menu-item index="4">
                <i class="el-icon-setting"></i>
                <span slot="title">我的收藏</span>
              </el-menu-item>
            </router-link>
            <el-submenu index="5">
              <template slot="title">
                <i class="el-icon-setting"></i>
                <span>内容管理</span>
              </template>
              <el-menu-item-group>
                <router-link to="/aManage">
                  <el-menu-item index="1-1">文章管理</el-menu-item>
                </router-link>
                <router-link to="/cManage">
                  <el-menu-item index="1-2">评论管理</el-menu-item>
                </router-link>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-col>
        <el-col :span="20">
          <router-view/>
        </el-col>
      </el-row>
  </div>
</template>
<script>
  export default {
    data(){
      return {
        message: null
      }
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>
<style scoped>
  .el-menu-item{
    height: 60px;
  }

  a {
    text-decoration: none;
  }

  .router-link-active {
    text-decoration: none;
  }
</style>
